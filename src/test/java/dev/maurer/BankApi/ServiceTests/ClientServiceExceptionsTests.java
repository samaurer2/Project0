package dev.maurer.BankApi.ServiceTests;

import dev.maurer.bank_api.daos.ClientDAO;
import dev.maurer.bank_api.daos.ClientDaoImpl;
import dev.maurer.bank_api.daos.PostgresClientDAO;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.exceptions.ClientNotFoundException;
import dev.maurer.bank_api.services.ClientService;
import dev.maurer.bank_api.services.ClientServiceImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceExceptionsTests {

    static ClientDAO clientDao;
    static ClientService clientService;

    @BeforeAll
    static void setUp() {
        clientDao = new PostgresClientDAO();
        clientService = new ClientServiceImpl(clientDao);
        for (int i = 0; i < 10; ++i) {
            Client client = new Client();
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

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }

    }

    @Test
    @Order(2)
    void updateClientExceptionTest() {
        try {
            Client client = new Client();
            client.setId(42);
            clientService.updateClient(client);
            Assertions.fail();
        } catch (ClientNotFoundException e) {

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

}
