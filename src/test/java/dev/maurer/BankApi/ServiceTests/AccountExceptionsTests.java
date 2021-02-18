package dev.maurer.BankApi.ServiceTests;

import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.daos.AccountDaoImpl;
import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.daos.ClientDaoImpl;
import dev.maurer.BankAPI.entitiy.Account;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.services.AccountService;
import dev.maurer.BankAPI.services.AccountServiceImpl;
import dev.maurer.BankAPI.services.ClientService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountExceptionsTests {


    static ClientDAO clientDAO;
    static AccountDAO accountDAO;
    static AccountService accountService;

    @BeforeAll
    static void setUp(){
        clientDAO = new ClientDaoImpl();
        accountDAO = new AccountDaoImpl();
        accountService = new AccountServiceImpl(accountDAO);
        for (int i = 0; i < 5; i++) {
            Client client = new Client(0);
            clientDAO.createNewClient(client);
            for (int j = 0; j < 3; ++j) {
                Account account = new Account(client.getId(),0);
                accountDAO.createAccount(account);
            }
        }
    }
    @Test
    @Order(1)
    void test1() {
        Assertions.fail();
    }

    @Test
    @Order(2)
    void test2() {
        Assertions.fail();
    }

    @Test
    @Order(3)
    void test3() {
        Assertions.fail();
    }

    @Test
    @Order(4)
    void test4() {
        Assertions.fail();
    }

    @Test
    @Order(5)
    void test5() {
        Assertions.fail();
    }

    @Test
    @Order(6)
    void test6() {
        Assertions.fail();
    }
}
