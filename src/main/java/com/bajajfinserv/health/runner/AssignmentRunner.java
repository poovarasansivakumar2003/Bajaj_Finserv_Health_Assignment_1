package com.bajajfinserv.health.runner;

import com.bajajfinserv.health.model.WebhookResponse;
import com.bajajfinserv.health.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AssignmentRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentRunner.class);
    
    private final WebhookService webhookService;

    public AssignmentRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(String... args) {
        logger.info("\n\n");
        logger.info("*".repeat(60));
        logger.info("   BAJAJ FINSERV HEALTH - QUALIFIER 1 ASSIGNMENT");
        logger.info("*".repeat(60));
        logger.info("\n");
        
        try {
            // Step 1: Generate webhook
            WebhookResponse webhookResponse = webhookService.generateWebhook();
            
            if (webhookResponse == null) {
                logger.error("Failed to get webhook response. Exiting...");
                return;
            }
            
            // Step 2: Get SQL query based on registration number
            String sqlQuery = webhookService.getSqlQuery();
            
            // Step 3: Submit solution
            webhookService.submitSolution(webhookResponse.getAccessToken(), sqlQuery);
            
        } catch (Exception e) {
            logger.error("\n" + "!".repeat(60));
            logger.error("ERROR: {}", e.getMessage());
            logger.error("!".repeat(60));
            e.printStackTrace();
        }
    }
}
