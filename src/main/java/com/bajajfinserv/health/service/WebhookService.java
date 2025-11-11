package com.bajajfinserv.health.service;

import com.bajajfinserv.health.model.SolutionRequest;
import com.bajajfinserv.health.model.WebhookRequest;
import com.bajajfinserv.health.model.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);

    private final RestTemplate restTemplate;
    private final SqlQueryService sqlQueryService;

    @Value("${api.generate.webhook.url}")
    private String generateWebhookUrl;

    @Value("${api.submit.solution.url}")
    private String submitSolutionUrl;

    @Value("${user.name}")
    private String userName;

    @Value("${user.regNo}")
    private String userRegNo;

    @Value("${user.email}")
    private String userEmail;

    public WebhookService(SqlQueryService sqlQueryService) {
        this.restTemplate = new RestTemplate();
        this.sqlQueryService = sqlQueryService;
    }

    /**
     * Step 1: Generate webhook by sending POST request with user details
     */
    public WebhookResponse generateWebhook() {
        logger.info("=".repeat(60));
        logger.info("STEP 1: Generating Webhook");
        logger.info("=".repeat(60));
        
        WebhookRequest request = new WebhookRequest(userName, userRegNo, userEmail);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
        
        logger.info("Sending POST request to: {}", generateWebhookUrl);
        logger.info("Request Body: Name={}, RegNo={}, Email={}", userName, userRegNo, userEmail);
        
        try {
            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                generateWebhookUrl,
                HttpMethod.POST,
                entity,
                WebhookResponse.class
            );
            
            WebhookResponse webhookResponse = response.getBody();
            
            if (webhookResponse != null) {
                logger.info("✓ Webhook generated successfully!");
                logger.info("Webhook URL: {}", webhookResponse.getWebhook());
                logger.info("Access Token received: {}...", 
                    webhookResponse.getAccessToken().substring(0, Math.min(20, webhookResponse.getAccessToken().length())));
            }
            
            return webhookResponse;
            
        } catch (Exception e) {
            logger.error("✗ Failed to generate webhook: {}", e.getMessage());
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }

    /**
     * Step 2: Get SQL query based on registration number
     */
    public String getSqlQuery() {
        logger.info("\n" + "=".repeat(60));
        logger.info("STEP 2: Determining SQL Query");
        logger.info("=".repeat(60));
        
        String query = sqlQueryService.getSqlQuery(userRegNo);
        
        int lastTwoDigits = extractLastTwoDigits(userRegNo);
        String questionType = (lastTwoDigits % 2 == 0) ? "EVEN - Question 2" : "ODD - Question 1";
        
        logger.info("Registration Number: {}", userRegNo);
        logger.info("Last Two Digits: {}", lastTwoDigits);
        logger.info("Question Type: {}", questionType);
        logger.info("\nGenerated SQL Query:");
        logger.info("-".repeat(60));
        logger.info("{}", query);
        logger.info("-".repeat(60));
        
        return query;
    }

    /**
     * Step 3: Submit solution to webhook URL with JWT token
     */
    public void submitSolution(String accessToken, String sqlQuery) {
        logger.info("\n" + "=".repeat(60));
        logger.info("STEP 3: Submitting Solution");
        logger.info("=".repeat(60));
        
        SolutionRequest request = new SolutionRequest(sqlQuery);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);
        
        logger.info("Sending POST request to: {}", submitSolutionUrl);
        logger.info("Authorization Token: {}...", accessToken.substring(0, Math.min(20, accessToken.length())));
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                submitSolutionUrl,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("✓ Solution submitted successfully!");
            logger.info("Response Status: {}", response.getStatusCode());
            logger.info("Response Body: {}", response.getBody());
            logger.info("\n" + "=".repeat(60));
            logger.info("ASSIGNMENT COMPLETED SUCCESSFULLY!");
            logger.info("=".repeat(60));
            
        } catch (Exception e) {
            logger.error("✗ Failed to submit solution: {}", e.getMessage());
            throw new RuntimeException("Failed to submit solution", e);
        }
    }

    private int extractLastTwoDigits(String regNo) {
        String numericPart = regNo.replaceAll("[^0-9]", "");
        if (numericPart.length() >= 2) {
            return Integer.parseInt(numericPart.substring(numericPart.length() - 2));
        }
        return Integer.parseInt(numericPart);
    }
}
