# Bajaj Finserv Health - Qualifier 1 Assignment (JAVA)

## 📋 Project Overview

This Spring Boot application automatically:
1. Generates a webhook by sending a POST request on startup
2. Determines and solves the assigned SQL problem based on registration number
3. Submits the SQL solution to the webhook URL using JWT authentication

---

## 🎯 Assignment Details

- **Duration**: 1 Hour
- **Technology**: Spring Boot 3.2.0 + Java 17
- **Question Assignment**: Based on last two digits of registration number
  - **ODD** → Question 1: Find highest salary not paid on 1st day of month
  - **EVEN** → Question 2: Count younger employees in same department

---

## 📁 Project Structure

```
Bajaj_Finserv_Health_Assignment_1/
├── src/
│ ├── main/
│ │ ├── java/com/bajajfinserv/health/
│ │ │ ├── Application.java # Main Spring Boot application
│ │ │ ├── model/
│ │ │ │ ├── WebhookRequest.java # Request model for webhook generation
│ │ │ │ ├── WebhookResponse.java # Response model from webhook API
│ │ │ │ └── SolutionRequest.java # Request model for solution submission
│ │ │ ├── service/
│ │ │ │ ├── SqlQueryService.java # SQL query generation logic
│ │ │ │ └── WebhookService.java # API communication service
│ │ │ └── runner/
│ │ │ └── AssignmentRunner.java # CommandLineRunner for startup execution
│ │ └── resources/
│ │ └── application.properties # Configuration file
├── pom.xml # Maven dependencies
└── README.md # This file contains project overview and instructions
```

## ⚙️ Prerequisites

- **Java 17** or higher
- **Maven** (Maven Wrapper included in project)
- Internet connection for API calls

🎯 Step-by-Step Execution Guide



## 🔽 Clone the Repository

Clone the project and change into the project directory:

```bash
git clone https://github.com/poovarasansivakumar2003/Bajaj_Finserv_Health_Assignment_1.git
cd Bajaj_Finserv_Health_Assignment_1
```

Step 1: Update Configuration
Open application.properties
Replace user.name, user.regNo, and user.email with your actual details

user.name=your_name
user.regNo=your_reg_no
user.email=your_email@example.com


## 🚀 How to Run

### Option 1: Using Maven Wrapper (Recommended - No Maven installation required)

#### On Windows:

```bash

# Run the application
.\mvnw.cmd spring-boot:run
```

#### On macOS/Linux:

```bash
# Make the wrapper executable
chmod +x mvnw

# Run the application
./mvnw spring-boot:run
```

### Option 2: Build and Run JAR
On Windows:
```bash
# Build the JAR
.\mvnw.cmd clean package

# Run the JAR
java -jar target\bajaj-finserv-health-assignment.jar
```
On macOS/Linux:
```bash
# Build the JAR
./mvnw clean package

# Run the JAR
java -jar target/bajaj-finserv-health-assignment.jar
```

### Check Output
The application will automatically:

✅ Generate webhook
✅ Determine your SQL question (based on reg number)
✅ Submit the solution
✅ Display all steps in console

Output Example

```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

2025-11-11 16:01:50 - Starting Application using Java 24.0.1 with PID 1932 (C:\Users\poova\Desktop\Bajaj_Finserv_Health_Assignment_1\target\classes started by Poovarasan's Laptop in C:\Users\poova\Desktop\Bajaj_Finserv_Health_Assignment_1)
2025-11-11 16:01:50 - No active profile set, falling back to 1 default profile: "default"
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by org.apache.tomcat.jni.Library in an unnamed module (file:/C:/Users/poova/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.16/tomcat-embed-core-10.1.16.jar)
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled

2025-11-11 16:01:52 - Tomcat initialized with port 8080 (http)
2025-11-11 16:01:52 - Starting service [Tomcat]
2025-11-11 16:01:52 - Starting Servlet engine: [Apache Tomcat/10.1.16]
2025-11-11 16:01:52 - Initializing Spring embedded WebApplicationContext
2025-11-11 16:01:52 - Root WebApplicationContext: initialization completed in 1638 ms
2025-11-11 16:01:52 - Tomcat started on port 8080 (http) with context path ''
2025-11-11 16:01:52 - Started Application in 3.161 seconds (process running for 3.751)
2025-11-11 16:01:52 -


2025-11-11 16:01:52 - ************************************************************
2025-11-11 16:01:52 -    BAJAJ FINSERV HEALTH - QUALIFIER 1 ASSIGNMENT
2025-11-11 16:01:52 - ************************************************************
2025-11-11 16:01:52 -

2025-11-11 16:01:52 - ============================================================
2025-11-11 16:01:52 - STEP 1: Generating Webhook
2025-11-11 16:01:52 - ============================================================
2025-11-11 16:01:52 - Sending POST request to: https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA
2025-11-11 16:01:52 - Request Body: Name=Poovarasan's Laptop, RegNo=U25UV22T064042, Email=poovarasan.s@campusuvce.in
2025-11-11 16:01:54 - Γ£ô Webhook generated successfully!
2025-11-11 16:01:54 - Webhook URL: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
2025-11-11 16:01:54 - Access Token received: eyJhbGciOiJIUzI1NiJ9...
2025-11-11 16:01:54 -
============================================================
2025-11-11 16:01:54 - STEP 2: Determining SQL Query
2025-11-11 16:01:54 - ============================================================
2025-11-11 16:01:54 - Registration Number: U25UV22T064042
2025-11-11 16:01:54 - Last Two Digits: 42
2025-11-11 16:01:54 - Question Type: EVEN - Question 2
2025-11-11 16:01:54 -
Generated SQL Query:
2025-11-11 16:01:54 - ------------------------------------------------------------
2025-11-11 16:01:54 - SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT FROM EMPLOYEE e1 JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME ORDER BY e1.EMP_ID DESC
2025-11-11 16:01:54 - ------------------------------------------------------------
2025-11-11 16:01:54 -
============================================================
2025-11-11 16:01:54 - STEP 3: Submitting Solution
2025-11-11 16:01:54 - ============================================================
2025-11-11 16:01:54 - Sending POST request to: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
2025-11-11 16:01:54 - Authorization Token: eyJhbGciOiJIUzI1NiJ9...
2025-11-11 16:01:54 - Γ£ô Solution submitted successfully!
2025-11-11 16:01:54 - Response Status: 200 OK
2025-11-11 16:01:54 - Response Body: {"success":true,"message":"Webhook processed successfully"}
2025-11-11 16:01:54 -
============================================================
2025-11-11 16:01:54 - ASSIGNMENT COMPLETED SUCCESSFULLY!
2025-11-11 16:01:54 - ============================================================
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  03:23 min
[INFO] Finished at: 2025-11-11T16:05:06+05:30
[INFO] ------------------------------------------------------------------------
Terminate batch job (Y/N)? y
```

📝 SQL Queries Explained
Question 1 (ODD Registration Numbers):
Find the highest salary not paid on the 1st day of any month with employee details.

Output Columns: SALARY, NAME, AGE, DEPARTMENT_NAME

Question 2 (EVEN Registration Numbers):
Count younger employees in the same department for each employee.

Output Columns: EMP_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_NAME, YOUNGER_EMPLOYEES_COUNT



🔍 Troubleshooting

### Issue: "mvnw is not recognized"
Solution: Use mvnw.cmd on Windows or mvnw on Linux/Mac

### Issue: "Java version error"
**Solution**: Ensure Java 17 or higher is installed. Check with java -version

### Issue: "Connection refused"
**Solution**: Check internet connection and API endpoint availability

### Issue: "Port 8080 already in use"
**Solution**: Add to application.properties: server.port=8081

👨‍💻 Author


📄 License
This project is created for Bajaj Finserv Health Qualifier 1 Assignment.

Note: Make sure to update application.properties with your actual details before running the application!