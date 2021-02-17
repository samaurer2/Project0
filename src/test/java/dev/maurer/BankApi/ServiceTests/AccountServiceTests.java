package dev.maurer.BankApi.ServiceTests;

import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.daos.AccountDaoImpl;
import dev.maurer.BankAPI.entitiy.Account;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.services.AccountService;
import dev.maurer.BankAPI.services.AccountServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTests {


    static AccountDAO accountDAO;
    static AccountService accountService;
    static Client defaultClient;

    @BeforeAll
    static void setUp(){
        accountService = new AccountServiceImpl(new AccountDaoImpl());
        defaultClient = new Client(1);
    }

    @Test
    @Order(1)
    void createAccountServiceTest(){
        Account account = new Account(defaultClient.getId(), 0);
        accountService.createAccount(account);
        Assertions.assertNotEquals(0, account.getAccountId());
    }

    @Test
    @Order(2)
    void getAllAccountsServiceTest(){
        Account account;
        for (int i = 0; i < 5; ++i) {
            account = new Account(defaultClient.getId(), 0);
            accountService.createAccount(account);
        }
        Set<Account> allAccounts = accountService.getAllAccounts(defaultClient.getId());
        for (Account a :allAccounts) {
            System.out.println(a);
        }
        Assertions.assertTrue(allAccounts.size()>5);
    }

    @Test
    @Order(3)
    void getAccountServiceTest(){
        Account account = accountService.getAccount(defaultClient.getId(), 3);
        Assertions.assertEquals(3, account.getAccountId());
    }

    @Test
    @Order(4)
    void getRangeAccountServiceTest(){
        Set<Account> accountRange = accountService.getRangeAccounts(defaultClient.getId(),1,4);
        Assertions.assertEquals(2, accountRange.size());
    }

    @Test
    @Order(5)
    void deleteAccountServiceTest(){
        Assertions.assertTrue(accountService.deleteAccount(defaultClient.getId(), 5));
    }

    @Test
    @Order(6)
    void updateAccountServiceTest(){
        Account account = new Account(defaultClient.getId(), 2);

        account.setBalance(420.69);
        accountService.updateAccount(account.getClientId(), account);

        Account newAccount = accountService.getAccount(defaultClient.getId(), 2);
        Assertions.assertEquals(420.69, newAccount.getBalance());
    }
}
