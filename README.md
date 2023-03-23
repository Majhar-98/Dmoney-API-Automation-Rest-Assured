# Dmoney-API-Automation-Rest-Assured
## Rest Assured Automation:
Rest-Assured is an open-source Java-based library used for automating RESTful web services testing. It simplifies the process of sending HTTP requests and receiving responses from RESTful services, allowing testers to easily validate the functionality of their API endpoints.

Rest-Assured provides a domain-specific language (DSL) that enables testers to write readable and concise test scripts. The library supports various HTTP methods like GET, POST, PUT, DELETE, and more. It also supports common authentication methods like OAuth, Basic Authentication, and API Keys.

## Automation steps:
- Call login API
- Create a new customer and an agent
- Search by the customer phone number
- Deposit money to the Agent from system
- Deposit money by agent to customer
- Check balance of customer
- Check statement by trnxId
- Withdraw money by customer and assert expected balance
- Send money to another customer and assert expected balance
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
- After run the project give the following command for generate Allure Report: `allure generate allure-results --clean -o allure-report and allure serve allure-results
## Test cases:
- [Drive link](https://drive.google.com/drive/u/2/folders/1HAChtAi8edd88Be4DNIUyggsMWVw0N3f)

## Allure Report:
![Azure-1](https://user-images.githubusercontent.com/123467715/227119774-a33a2fd8-97d6-4a9c-b3a7-1786c1373d41.PNG)

![Azure-2](https://user-images.githubusercontent.com/123467715/227119819-5add285c-7a86-417d-8d3f-00d0391acbff.PNG)

![Azure-3](https://user-images.githubusercontent.com/123467715/227119844-e2811491-8255-4f3c-b710-2630338a8129.PNG)

![Azure-4](https://user-images.githubusercontent.com/123467715/227119720-ba3823cb-84c8-4b31-a2b6-8b70610ead8f.PNG)
