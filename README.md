# Dmoney-API-Automation-Rest-Assured
## Automation steps:
- Call login API
- Create a new customer and an agent
- Search by the customer phone number
- Deposit 5000 tk to the Agent from system
- Deposit 2000 tk by agent to customer
- Check balance of customer
- Check statement by trnxId
- Withdraw 1000 tk by customer and assert expected balance
- Send 500 tk to another customer and assert expected balance
- Check customer statement
## Technology and Tool Used:
- Rest-assured
- TestNG
- Java
- Gradle
- intellij idea
- jackson-databind
- Allure
- lombok
## Prerequisite:
- JDK 8 or LTD
- java IDE
- configure JAVA_HOME and GRADLE_HOME
## How to run this project:
- Clone this project
- Hit the following command: gradle clean test
- Command for Allure Report
- After run the project give the following command for generate Allure Report ` -- allure generate allure-results --clean -o allure-report -- allure serve allure-results
