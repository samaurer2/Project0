package dev.maurer.BankApi.ServiceTests;

import dev.maurer.bank_api.daos.*;
import dev.maurer.bank_api.entitiy.Account;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.services.AccountService;
import dev.maurer.bank_api.services.AccountServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTests {

    static ClientDAO clientDAO;
    static AccountDAO accountDAO;
    static AccountService accountService;
    static Client defaultClient;

    @BeforeAll
    static void setUp() {

        clientDAO = new PostgresClientDAO();
        accountDAO = new PostgresAccountDAO(clientDAO);
        accountService = new AccountServiceImpl(clientDAO, accountDAO);
        defaultClient = new Client();
        clientDAO.createNewClient(defaultClient);
    }

    @Test
    @Order(1)
    void createAccountServiceTest() {
        try {
            Account account = new Account();
            account.setClientId(defaultClient.getId());
            accountService.createAccount(account);
            Assertions.assertNotEquals(0, account.getAccountId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(2)
    void getAllAccountsServiceTest() {
        try {
            Account account;
            for (int i = 0; i < 5; ++i) {
                account = new Account();
                account.setClientId(defaultClient.getId());
                accountService.createAccount(account);
            }
            Set<Account> allAccounts = accountService.getAllAccounts(defaultClient.getId());
            Assertions.assertTrue(allAccounts.size() > 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void getAccountServiceTest() {
        try {
            Account account = accountService.getAccount(defaultClient.getId(), 3);
            Assertions.assertEquals(3, account.getAccountId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void updateAccountServiceTest() {
        try {
            Account account = new Account();
            account.setClientId(defaultClient.getId());
            account.setAccountId(2);
            account.setBalance(420.69);

            accountService.updateAccount(defaultClient.getId(), account);

            Account newAccount = accountService.getAccount(defaultClient.getId(), 2);
            Assertions.assertEquals(420.69, newAccount.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @Order(5)
    void getRangeAccountServiceTest() {
        try {
            Set<Account> accountRange = accountService.getRangeAccounts(defaultClient.getId(), 100, 500);
            Assertions.assertEquals(1, accountRange.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void deleteAccountServiceTest() {
        try {
            Assertions.assertTrue(accountService.deleteAccount(defaultClient.getId(), 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
