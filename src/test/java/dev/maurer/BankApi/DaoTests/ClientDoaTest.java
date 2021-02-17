package dev.maurer.BankApi.DaoTests;

import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.daos.ClientDaoImpl;
import dev.maurer.BankAPI.entitiy.Client;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientDoaTest {
    static ClientDAO bdao;
    static Client testClient;

    @BeforeAll
    public static void setUp() {
        bdao = new ClientDaoImpl();
    }

    @Test
    @Order(1)
    void createNewClientTest() {
        Client client = new Client(0);
        testClient = client;
        bdao.createNewClient(client);
        Assertions.assertNotEquals(0,client.getId());
    }

    @Test
    @Order(2)
    void getAllClientsTest() {
        Client client;
        for (int i = 0; i < 5; i++) {
            client = new Client(0);
            bdao.createNewClient(client);
        }
        Set<Client> clients = bdao.getAll();
        Assertions.assertTrue(clients.size() > 5);

    }

    @Test
    @Order(3)
    void getClientTest() {
        Client client = bdao.getClient(2);
        Assertions.assertTrue(client.getId() == 2);
    }

    @Test
    @Order(4)
    void deleteClientTest() {
        int id = testClient.getId();
        Assertions.assertTrue(bdao.deleteClient(id));
    }

    @Test
    @Order(5)
    void updateClientTest() {
        Client client = bdao.getClient(3);
        client.setClientName("New Company");
        bdao.updateClient(client);

        Client newClient = bdao.getClient(3);

        Assertions.assertEquals("New Company", newClient.getClientName());
    }

}
