package dev.maurer.BankApi.ServiceTests;

import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.daos.ClientDaoImpl;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;
import dev.maurer.BankAPI.services.ClientService;
import dev.maurer.BankAPI.services.ClientServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.SQLException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceExceptionsTests {

    static ClientDAO clientDao;
    static ClientService clientService;

    @BeforeAll
    static void setUp() {
        clientDao = new ClientDaoImpl();
        clientService = new ClientServiceImpl(clientDao);
        for (int i = 0; i < 10; ++i) {
            Client client = new Client(0);
            clientDao.createNewClient(client);
        }
    }

    @Test
    @Order(1)
    void getClientExceptionTest() {
        try {
            clientService.getClient(42);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        }

    }

    @Test
    @Order(2)
    void updateClientExceptionTest() {
        try {
            Client client = new Client(42);
            clientService.updateClient(client);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        }
    }

    @Test
    @Order(3)
    void deleteClientExceptionTest() {
        try {
            clientService.deleteClient(42);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        }

    }
}
