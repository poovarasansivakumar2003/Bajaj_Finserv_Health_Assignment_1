package com.bajajfinserv.health.service;

import org.springframework.stereotype.Service;

@Service
public class SqlQueryService {

    /**
     * Determines which SQL query to use based on registration number
     * Odd last two digits -> Question 1 (Highest Salary)
     * Even last two digits -> Question 2 (Younger Employees Count)
     */
    public String getSqlQuery(String regNo) {
        int lastTwoDigits = extractLastTwoDigits(regNo);
        
        if (lastTwoDigits % 2 == 0) {
            return getQuestion2Query();
        } else {
            return getQuestion1Query();
        }
    }

    private int extractLastTwoDigits(String regNo) {
        // Extract numeric part from regNo
        String numericPart = regNo.replaceAll("[^0-9]", "");
        if (numericPart.length() >= 2) {
            return Integer.parseInt(numericPart.substring(numericPart.length() - 2));
        }
        return Integer.parseInt(numericPart);
    }

    /**
     * Question 1 (ODD): Find highest salary not paid on 1st day of month
     * Output: SALARY, NAME, AGE, DEPARTMENT_NAME
     */
    private String getQuestion1Query() {
        return "SELECT " +
               "p.AMOUNT AS SALARY, " +
               "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
               "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, " +
               "d.DEPARTMENT_NAME " +
               "FROM PAYMENTS p " +
               "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
               "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
               "WHERE DAY(p.PAYMENT_TIME) != 1 " +
               "ORDER BY p.AMOUNT DESC " +
               "LIMIT 1";
    }

    /**
     * Question 2 (EVEN): Count younger employees in same department
     * Output: EMP_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_NAME, YOUNGER_EMPLOYEES_COUNT
     */
    private String getQuestion2Query() {
        return "SELECT " +
               "e1.EMP_ID, " +
               "e1.FIRST_NAME, " +
               "e1.LAST_NAME, " +
               "d.DEPARTMENT_NAME, " +
               "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
               "FROM EMPLOYEE e1 " +
               "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
               "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT " +
               "AND e2.DOB > e1.DOB " +
               "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
               "ORDER BY e1.EMP_ID DESC";
    }
}
