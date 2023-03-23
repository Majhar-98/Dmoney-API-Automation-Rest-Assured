package testrunner;

import controller.User;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;
import setup.Setup;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user;

    @Test (priority = 4, description = "Calling the login API by giving valid credentials")
    public void doLogin() throws ConfigurationException, IOException {
        user = new User( );
        user.callingLoginAPI( );
    }

    @Test (priority = 1, description = "Incorrect email provided for login")
    public void loginWithIncorrectEmail() throws ConfigurationException, IOException {
        user = new User( );
        user.loginWithIncorrectEmail( );

    }

    @Test (priority = 2, description = "Incorrect password provided for login")
    public void loginWithIncorrectPassword() throws ConfigurationException, IOException {
        user = new User( );
        user.loginWithIncorrectPassword( );
    }

    @Test (priority = 3, description = "No password provided for login")
    public void blankPassword() throws ConfigurationException, IOException {
        user = new User( );
        user.blankPassword( );
    }

    @Test (priority = 7, description = "User information will be displayed by giving proper authorization")
    public void getUserList() throws IOException {
        user = new User( );
        user.getUserList( );
    }

    @Test (priority = 10, description = "Particular user information will be displayed by giving proper Id")
    public void searchUserByValidId() throws IOException {
        user = new User( );
        user.searchUserByValidId();
    }

    @Test (priority = 8, description = "Particular user information will never be displayed due to giving invalid id")
    public void searchUserByInvalidId() throws IOException {
        user = new User( );
        user.searchUserByInvalidId( );
    }

    @Test (priority = 5, description = "Cannot get user list for incorrect authorization token")
    public void getUserListForIncorrectAuth() throws IOException {
        user = new User( );
        user.getUserListForIncorrectAuth( );
    }

    @Test (priority = 6, description = "If user does not input token the system will give 401 status code")
    public void getUserListForBlankAuthorizationToken() throws IOException {
        user = new User( );
        user.getUserListForBlankAuthorizationToken( );
    }
    @Test (priority = 12, description = "New agent created")
    public void createAgent() throws ConfigurationException, IOException {
        user = new User();
        user.createAgent();
    }
    @Test (priority = 11, description = "New agent created")
    public void createAgentWithexistingCreds() throws ConfigurationException, IOException {
        user = new User();
        user.createAgentWithexistingCreds();
    }
    @Test (priority = 13, description = "New customer created")
    public void createCustomer() throws ConfigurationException, IOException {
        user= new User();
        user.createCustomer();
    }
    @Test (priority = 14, description = "Search customer by phone number")
    public void searchCustomerByPhoneNumber() throws ConfigurationException, IOException {
        user = new User();
        user.searchCustomerByPhoneNumber();
    }
    @Test (priority = 16, description = "Deposit to agent with proper information")
    public void DepositToAgent() throws ConfigurationException, IOException {
        user = new User();
        user.DepositToAgent();
    }
    @Test (priority = 15, description = "Deposit to agent with insufficient balance can not be executed")
    public void DepositInsufficientBalanceToAgent() throws ConfigurationException, IOException {
        user = new User();
        user.DepositInsufficientBalanceToAgent();
    }
    @Test(priority = 18, description = "Deposit to customer with proper information")
    public void DepositToCustomer() throws ConfigurationException, IOException {
        user = new User();
        user.DepositToCustomer();
    }
    @Test (priority = 17, description = "Deposit customer with insufficient balance can not be executed")
    public void DepositInsufficientBalanceToCustomer() throws ConfigurationException, IOException {
        user = new User();
        user.DepositInsufficientBalanceToCustomer();
    }
    @Test (priority = 20, description = "Can not check customer balance if phone number is invalid")
    public void DepositToInvalidCustomer() throws ConfigurationException, IOException {
        user =  new User();
        user.DepositInsufficientBalanceToCustomer();
    }
    @Test(priority = 22, description = "Check customer balance successfully with valid customer" )
    public void checkCustomerBalance() throws IOException {
        user = new User();
        user.checkCustomerBalance();
    }
    @Test (priority = 21, description = "Can not check customer balance if Id is invalid")
    public void checkCustomerBalanceByInvalidId() throws IOException {
        user = new User();
        user.checkCustomerBalanceByInvalidId();
    }
    @Test (priority = 24, description = "Customer can withdraw money successfully to valid agent")
    public void moneyWithdrawByCustomer() throws IOException {
        user = new User();
        user.moneyWithdrawByCustomer();
    }
    @Test (priority = 23 ,description = "Customer can not send money due to insufficient balance")
    public void InsufficientMoneyWithdrawByCustomer() throws IOException {
        user = new User();
        user.InsufficientMoneyWithdrawByCustomer();
    }

    @Test (priority = 26 , description = "Customer can send money successfully to another valid customer")
    public void sendMoneyByCustomerToAnotherCustomer() throws IOException, ConfigurationException {
        user = new User();
        user.sendMoneyByCustomerToAnotherCustomer();
    }

    @Test(priority = 25 ,description = "Customer can not send money to another customer if balance is insufficient")
    public void sendInsufficientMoneyByCustomerToAnotherCustomer() throws IOException {
        user = new User();
        user.sendInsufficientMoneyByCustomerToAnotherCustomer();
    }

    @Test (priority = 27 ,description = "Customer can check balance successfully with valid customer transaction id")
    public void checkCustomerStatementByTrnxId() throws IOException {
        user = new User();
        user.checkCustomerStatementByTrnxId();
    }
    @Test (priority = 26 ,description = "Customer cannot check balance successfully with invalid customer transaction id")
    public void checkCustomerStatementByInvalidTrnxId() throws IOException {
        user = new User();
        user.checkCustomerStatementByInvalidTrnxId();
    }


}

