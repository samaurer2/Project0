package dev.maurer.BankApi.ServiceTests;

import dev.maurer.bank_api.daos.*;
import dev.maurer.bank_api.entitiy.Account;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.exceptions.AccountNotFoundException;
import dev.maurer.bank_api.exceptions.BadArgumentException;
import dev.maurer.bank_api.exceptions.ClientNotFoundException;
import dev.maurer.bank_api.services.AccountService;
import dev.maurer.bank_api.services.AccountServiceImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountExceptionsTests {


    static ClientDAO clientDAO;
    static AccountDAO accountDAO;
    static AccountService accountService;

    @BeforeAll
    static void setUp() {
        clientDAO = new PostgresClientDAO();
        accountDAO = new PostgresAccountDAO(clientDAO);
        accountService = new AccountServiceImpl(clientDAO, accountDAO);
        for (int i = 0; i < 5; i++) {
            Client client = new Client();
            clientDAO.createNewClient(client);
            for (int j = 0; j < 3; ++j) {
                Account account = new Account();
                account.setClientId(client.getId());
                accountDAO.createAccount(account);
            }
        }
    }

    @Test
    @Order(1)
    void createNewAccountExceptionTest() {
        try {
            Account account = new Account();
            account.setClientId(10);
            accountService.createAccount(account);
            Assertions.fail();
        } catch (ClientNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(2)
    void getAllAccountsExceptionTest() {
        try {
            accountService.getAllAccounts(10);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }

    }

    @Test
    @Order(3)
    void getAccountClientNotFoundExceptionTest() {
        try {
            accountService.getAccount(10, 10);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(4)
    void getAccountAccountNotFoundExceptionTest() {
        try {
            accountService.getAccount(1, 10);
            Assertions.fail();
        } catch (AccountNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(5)
    void getRangeAccountsClientNotFoundExceptionTest() {

        try {
            accountService.getRangeAccounts(10, 0, 100);
            Assertions.fail();
        } catch (ClientNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(6)
    void getRangeAccountsBadArgumentExceptionTest1() {
        try {
            accountService.getRangeAccounts(1, 20, 19);
            Assertions.fail();
        } catch (BadArgumentException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(7)
    void getRangeAccountsBadArgumentExceptionTest2() {
        try {
            accountService.getRangeAccounts(1, 20, 20);
            Assertions.fail();
        } catch (BadArgumentException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(8)
    void updateAccountClientNotFoundExceptionTest() {
        try {
            Account account = new Account();
            account.setClientId(100);
            accountService.updateAccount(100, account);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(9)
    void updateAccountAccountNotFoundExceptionTest() {
        try {
            Account account = new Account();
            account.setClientId(1);
            accountService.updateAccount(1, account);
            Assertions.fail();
        } catch (AccountNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(10)
    void deleteAccountClientNotFoundExceptionTest() {
        try {
            accountService.deleteAccount(10, 100);
        } catch (ClientNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Order(11)
    void deleteAccountAccountNotFoundExceptionTest() {
        try {
            accountService.deleteAccount(1, 100);
            Assertions.fail();
        } catch (AccountNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
