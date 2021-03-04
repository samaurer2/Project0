//package dev.maurer.BankApi.DaoTests;
//
//import dev.maurer.bank_api.daos.*;
//import dev.maurer.bank_api.entitiy.Account;
//import dev.maurer.bank_api.entitiy.Client;
//import org.junit.jupiter.api.*;
//
//import java.util.Set;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class PostgresAccountDaoTests {
//    static ClientDAO clientDAO;
//    static AccountDAO aDao;
//
//    @BeforeAll
//    static void initialize() {
//        clientDAO = new PostgresClientDAO();
//        aDao = new PostgresAccountDAO(clientDAO);
//        Client client = new Client();
//        clientDAO.createNewClient(client);
//    }
//
//    @Test
//    @Order(1)
//    void createAccountTest() {
//        Client client = clientDAO.getClient(1);
//        Account account = new Account();
//        account.setClientId(client.getId());
//        aDao.createAccount(account);
//        Assertions.assertNotEquals(0, account.getAccountId());
//    }
//
//    @Test
//    @Order(2)
//    void getAllAccountsTest() {
//
//        Client client = clientDAO.getClient(1);
//        for (int i = 0; i < 5; ++i) {
//            Account account = new Account();
//            account.setClientId(client.getId());
//            aDao.createAccount(account);
//        }
//        Set<Account> allAccounts = aDao.getAllAccounts(client.getId());
//        Assertions.assertTrue(allAccounts.size() > 5);
//    }
//
//    @Test
//    @Order(3)
//    void getOneAccountTest() {
//        Client client = clientDAO.getClient(1);
//        Account account = aDao.getAccount(client.getId(), 4);
//        Assertions.assertEquals(4, account.getAccountId());
//    }
//
//
//    @Test
//    @Order(4)
//    void updateAccountTest() {
//        Client client = clientDAO.getClient(1);
//        Account account = aDao.getAccount(client.getId(), 2);
//
//        account.setBalance(42.69);
//        aDao.updateAccount(client.getId(), account);
//
//        Account newAccount = aDao.getAccount(client.getId(), 2);
//        Assertions.assertEquals(42.69, newAccount.getBalance());
//    }
//
//    @Test
//    @Order(5)
//    void getRangeAccountsTest() {
//        Client client = clientDAO.getClient(1);
//        Set<Account> accountSet = aDao.getRangeAccounts(client.getId(), 20, 50);
//        Assertions.assertEquals(1, accountSet.size());
//    }
//
//    @Test
//    @Order(6)
//    void deleteAccountTest() {
//        Client client = clientDAO.getClient(1);
//        Assertions.assertTrue(aDao.deleteAccount(client.getId(), 5));
//    }
//}
