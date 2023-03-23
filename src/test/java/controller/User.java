package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;
import setup.Setup;
import transactionmodel.TransactionModel;
import usermodel.UserModel;
import utils.Utils;

import java.io.IOException;
import static io.restassured.RestAssured.given;

public class User extends Setup {
    public User() throws IOException {
        initConfig();
    }
    public void callingLoginAPI() throws ConfigurationException {
        UserModel loginModel = new UserModel("salman@roadtocareer.net", "1234", "Agent");

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .body(loginModel)
                        .when( )
                        .post("/user/login")
                        .then( )
                        .assertThat( ).statusCode(200).extract( ).response( );

        JsonPath jsonpath = res.jsonPath( );
        String token = jsonpath.get("token");
        String message = jsonpath.get("message");

        Utils.setEnvVariable("token", token);

        System.out.println(token);
        System.out.println(message);

    }
    public void loginWithIncorrectEmail() throws ConfigurationException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"email\":\"towsif.6@roadtocareer.net\",\n" +
                                "    \"password\":\"1234\"\n" +
                                "}")
                        .when( )
                        .post("/user/login")
                        .then( )
                        .assertThat( ).statusCode(404).extract( ).response( );

        JsonPath jsonpath = res.jsonPath( );
        String token = jsonpath.get("token");
        String message = jsonpath.get("message");

        Utils.setEnvVariable("token", token);

        System.out.println(token);
        System.out.println(message);

    }

    public void loginWithIncorrectPassword() throws ConfigurationException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"email\":\"salman@roadtocareer.net\",\n" +
                                "    \"password\":\"1846\"\n" +
                                "}")
                        .when( )
                        .post("/user/login")
                        .then( )
                        .assertThat( ).statusCode(401).extract( ).response( );

        JsonPath jsonpath = res.jsonPath( );
        String token = jsonpath.get("token");
        String message = jsonpath.get("message");

        Utils.setEnvVariable("token", token);

        System.out.println(token);
        System.out.println(message);


    }
    public void blankPassword() throws ConfigurationException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"email\":\"salman@roadtocareer.net\",\n" +
                                "    \"password\":\"\"\n" +
                                "}")
                        .when( )
                        .post("/user/login")
                        .then( )
                        .assertThat( ).statusCode(401).extract( ).response( );

        JsonPath jsonpath = res.jsonPath( );
        String token = jsonpath.get("token");
        String message = jsonpath.get("message");

        Utils.setEnvVariable("token", token);

        System.out.println(token);
        System.out.println(message);

    }

    public void getUserList() throws IOException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .when( )
                        .get("/user/list")
                        .then( )
                        .assertThat( ).statusCode(200).extract( ).response( );

        JsonPath response = res.jsonPath( );
        System.out.println(response.get( ).toString( ));
        //System.out.println(res.asString());
        //System.out.println(response.get("users").toString( ));

    }

    public void searchUserByValidId() throws IOException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when( )
                        .get("/user/search/id/1233")
                        .then( )
                        .assertThat( ).statusCode(200).extract( ).response( );

        JsonPath response = res.jsonPath( );
        System.out.println(response.get().toString());
        //System.out.println(res.asString( ));
        //System.out.println(response.get("users[0].id").toString( ));

    }

    public void searchUserByInvalidId() throws IOException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when( )
                        .get("/user/search/id/70000")
                        .then( )
                        .assertThat( ).statusCode(404).extract( ).response( );

        JsonPath response = res.jsonPath( );
        System.out.println(response.get( ).toString( ));
        //System.out.println(res.asString());
        //System.out.println(response.get("users[].id").toString( ));

    }


    public void getUserListForIncorrectAuth() throws IOException {

        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", "incorrect token")
                        .when( )
                        .get("/user/list")
                        .then( )
                        .assertThat( ).statusCode(403).extract( ).response( );

        //System.out.println(res.asString());
        //JsonPath response = res.jsonPath();
        JsonPath response = res.jsonPath( );
        System.out.println(response.get("error.message").toString( ));

    }

    public void getUserListForBlankAuthorizationToken() throws IOException {

        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", "")
                        .when( )
                        .get("/user/list")
                        .then( )
                        .assertThat( ).statusCode(401).extract( ).response( );

        //System.out.println(res.asString());
        //JsonPath response = res.jsonPath();
        JsonPath response = res.jsonPath( );
        System.out.println(response.get("error.message").toString( ));
    }

    public void createAgent() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        UserModel agentModel = new UserModel(utils.getName( ), utils.getEmail( ), "1234", utils.generatePhoneNumber( ), "1234568", "Customer");
        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(agentModel)
                        .when( )
                        .post("/user/create")
                        .then( )
                        .assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
        Utils.setEnvVariable("agent_id", jsonResponse.get("user.id").toString( ));
        Utils.setEnvVariable("agent_name", jsonResponse.get("user.name"));
        Utils.setEnvVariable("agent_email", jsonResponse.get("user.email"));
        Utils.setEnvVariable("agent_phone_number", jsonResponse.get("user.phone_number"));
    }


    public void createAgentWithexistingCreds() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        UserModel agentModel = new UserModel( "renaldo.bosco@yahoo.com","01844202565", "Agent");
        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(agentModel)
                        .when( )
                        .post("/user/create")
                        .then( )
                        .assertThat( ).statusCode(208).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
        Utils.setEnvVariable("agent_id", jsonResponse.get("user.id"));
        Utils.setEnvVariable("agent_name", jsonResponse.get("user.name"));
        Utils.setEnvVariable("agent_email", jsonResponse.get("user.email"));
        Utils.setEnvVariable("agent_phone_number", jsonResponse.get("user.phone_number"));
    }

    public void createCustomer() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        UserModel customerModel = new UserModel(utils.getName( ), utils.getEmail( ), "1234", utils.generatePhoneNumber( ), "1234568", "Customer");
        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(customerModel)
                        .when( )
                        .post("/user/create")
                        .then( )
                        .assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
        Utils.setEnvVariable("customer_id", jsonResponse.get("user.id").toString( ));
        Utils.setEnvVariable("customer_name", jsonResponse.get("user.name"));
        Utils.setEnvVariable("customer_email", jsonResponse.get("user.email"));
        Utils.setEnvVariable("customer_phone_number", jsonResponse.get("user.phone_number"));
    }


    public void searchCustomerByPhoneNumber() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        UserModel regModel = new UserModel(utils.getName( ), utils.getEmail( ), "1234", utils.generatePhoneNumber( ), "1234568", "Customer");
        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(regModel)
                        .when( )
                        .post("/user/search/phone_number/"+props.getProperty("01844224574"));
        //.then()
        //.assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
        //Utils.setEnvVariable("customer_id", jsonResponse.get("user.id"));
        //Utils.setEnvVariable("customer_name", jsonResponse.get("user.name"));
        //Utils.setEnvVariable("customer_email", jsonResponse.get("user.email"));
        //Utils.setEnvVariable("customer_phone_number", jsonResponse.get("user.phone_number"));

    }
    public void DepositToAgent() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String agent_phone_number = props.getProperty("agent_phone_number");

        TransactionModel transactionModel = new TransactionModel("SYSTEM", agent_phone_number, 1000);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/deposit")
                        .then( )
                        .assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }

    public void DepositInsufficientBalanceToAgent() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        TransactionModel transactionModel = new TransactionModel("SYSTEM", props.getProperty("agent_phone_number"), 0);
        RestAssured.baseURI = props.getProperty("base_url");

        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/deposit")
                        .then( )
                        .assertThat( ).statusCode(208).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }

    public void DepositToCustomer() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String agent_phone_number = props.getProperty("agent_phone_number");
        String customer_phone_number = props.getProperty("customer_phone_number");

        TransactionModel transactionModel = new TransactionModel(agent_phone_number, customer_phone_number, 500);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/deposit");

        //.assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }

    public void DepositInsufficientBalanceToCustomer() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String agent_phone_number = props.getProperty("agent_phone_number");
        String customer_phone_number = props.getProperty("customer_phone_number");

        TransactionModel transactionModel = new TransactionModel(agent_phone_number, customer_phone_number, 0);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/deposit")
                        .then( )
                        .assertThat( ).statusCode(208).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }

    public void DepositToInvalidCustomer() throws ConfigurationException {
        String agent_phone_number = props.getProperty("agent_phone_number");
        String customer_phone_number = props.getProperty("customer_phone_number");
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        TransactionModel transactionModel = new TransactionModel(agent_phone_number, "01978456123", 1000);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/deposit")
                        .then( )
                        .assertThat( ).statusCode(404).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }

    public void checkCustomerBalance() {
       RestAssured.baseURI = props.getProperty("base_url");
       Response res =
            given()
                    .contentType("application/json")
                    .header("Authorization", props.getProperty("token"))
                    .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                    .when()
                    .get("/transaction/balance/"+props.getProperty("01844897216"));
                    //.then()
                    //.assertThat().statusCode(200).extract().response();




    JsonPath response = res.jsonPath( );
    System.out.println(response.get().toString());
        //JsonPath response = res.jsonPath( );
        //System.out.println(response.get().toString());
        //System.out.println(res.asString( ));

    }

    public void checkCustomerBalanceByInvalidId() {
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when( )
                        .post("/transaction/balance/id/0145")
        .then( )
        .assertThat( ).statusCode(404).extract( ).response( );
        JsonPath response = res.jsonPath( );
        System.out.println(response.get().toString());
        //System.out.println(res.asString( ));



    }

    public void moneyWithdrawByCustomer() {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String agent_phone_number = props.getProperty("agent_phone_number");
        String customer_phone_number = props.getProperty("customer_phone_number");

        TransactionModel transactionModel = new TransactionModel(customer_phone_number,agent_phone_number, 1000);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/withdraw")
                        .then( )
                        .assertThat( ).statusCode(208).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));

    }

    public void InsufficientMoneyWithdrawByCustomer() {

        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String agent_phone_number = props.getProperty("agent_phone_number");
        String customer_phone_number = props.getProperty("customer_phone_number");

        TransactionModel transactionModel = new TransactionModel(customer_phone_number,agent_phone_number, 0);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/withdraw")
                        .then( )
                        .assertThat( ).statusCode(208).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }

    public void sendMoneyByCustomerToAnotherCustomer() throws ConfigurationException {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String customer_phone_number1 = props.getProperty("customer_phone_number1");
        String customer_phone_number2 = props.getProperty("customer_phone_number2");

        TransactionModel transactionModel = new TransactionModel(customer_phone_number1, customer_phone_number2, 200);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/sendmoney");
        //.then( )
        //.assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        JsonPath jsonPath = res.jsonPath();
    System.out.println(jsonResponse.get( ).toString( ));

    }

    public void sendInsufficientMoneyByCustomerToAnotherCustomer() {
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String customer_phone_number1 = props.getProperty("customer_phone_number1");
        String customer_phone_number2 = props.getProperty("customer_phone_number2");

        TransactionModel transactionModel = new TransactionModel(customer_phone_number1, customer_phone_number2, 0);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .post("/transaction/sendmoney");
        //.then( )
        //.assertThat( ).statusCode(201).extract( ).response( );

        JsonPath jsonResponse = res.jsonPath( );
        System.out.println(jsonResponse.get( ).toString( ));
    }
    public void checkCustomerStatementByTrnxId(){
    RestAssured.baseURI = props.getProperty("base_url");
    Response res =
            given()
                    .contentType("application/json")
                    .header("Authorization", props.getProperty("token"))
                    .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                    .when()
                    .get("transaction/search/"+props.getProperty("TXN126657"));
                    //.then()
                    //.assertThat().statusCode(200).extract().response();

    JsonPath jsonResponse = res.jsonPath( );
    System.out.println(jsonResponse.get( ).toString( ));

    }
    public void checkCustomerStatementByInvalidTrnxId(){
        Utils utils = new Utils( );
        utils.generateRandomUser( );
        String agent_phone_number = props.getProperty("agent_phone_number");
        String customer_phone_number = props.getProperty("customer_phone_number");

        TransactionModel transactionModel = new TransactionModel(agent_phone_number, customer_phone_number, 500);
        RestAssured.baseURI = props.getProperty("base_url");
        Response res =
                given( )
                        .contentType("application/json")
                        .header("Authorization", props.getProperty("token"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(transactionModel)
                        .when( )
                        .get("transaction/search/"+props.getProperty("TX112665789"))
                        .then( )
                        .assertThat( ).statusCode(404).extract( ).response( );
        JsonPath response = res.jsonPath( );
        System.out.println(response.get().toString());
        //System.out.println(res.asString( ));

    }
}
