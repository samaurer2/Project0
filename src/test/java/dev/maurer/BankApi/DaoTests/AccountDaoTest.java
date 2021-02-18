package dev.maurer.BankApi.DaoTests;

import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.daos.AccountDaoImpl;
import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.entitiy.Account;
import dev.maurer.BankAPI.entitiy.Client;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class AccountDaoTest {
    @Mock
    static ClientDAO clientDAO = null;
    static AccountDAO aDao;

    @BeforeAll
    static void initialize() {
        aDao = new AccountDaoImpl();
    }

    @BeforeEach
    void setUp() {
        Client c1 = new Client(1);

        Mockito.when(clientDAO.getClient(1)).thenReturn(c1);
    }

    @Test
    @Order(1)
    void createAccountTest() {
        Client client = clientDAO.getClient(1);
        Account account = new Account(client.getId(), 0);
        aDao.createAccount(account);
        Assertions.assertNotEquals(0, account.getAccountId());
    }

    @Test
    @Order(2)
    void getAllAccountsTest() {

        Client client = clientDAO.getClient(1);
        for (int i = 0; i < 5; ++i) {
            Account account = new Account(client.getId(), 0);
            aDao.createAccount(account);
        }
        Set<Account> allAccounts = aDao.getAllAccounts(client.getId());

        Assertions.assertEquals(6, allAccounts.size());
    }

    @Test
    @Order(3)
    void getOneAccountTest() {
        Client client = clientDAO.getClient(1);
        Account account = aDao.getAccount(client.getId(), 4);
        Assertions.assertEquals(4, account.getAccountId());
    }


    @Test
    @Order(4)
    void updateAccountTest() {
        Client client = clientDAO.getClient(1);
        Account account = aDao.getAccount(client.getId(), 2);

        account.setBalance(42.69);
        aDao.updateAccount(client.getId(), account);

        Account newAccount = aDao.getAccount(client.getId(), 2);
        Assertions.assertEquals(42.69, newAccount.getBalance());
    }

    @Test
    @Order(5)
    void getRangeAccountsTest() {
        Client client = clientDAO.getClient(1);
        Set<Account> accountSet = aDao.getRangeAccounts(client.getId(),20, 50);
        Assertions.assertEquals(1, accountSet.size());
    }

    @Test
    @Order(6)
    void deleteAccountTest() {
        Client client = clientDAO.getClient(1);
        Assertions.assertTrue(aDao.deleteAccount(client.getId(), 5));
    }
}
