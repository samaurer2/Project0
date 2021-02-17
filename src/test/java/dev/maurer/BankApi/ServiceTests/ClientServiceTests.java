package dev.maurer.BankApi.ServiceTests;


import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.daos.ClientDaoImpl;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.services.ClientService;
import dev.maurer.BankAPI.services.ClientServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceTests {

    static ClientDAO clientDAO;
    static ClientService clientService;
    @BeforeAll
    static void setUp(){
        clientDAO = new ClientDaoImpl();
        clientService = new ClientServiceImpl(clientDAO);
    }


    @Test
    @Order(1)
    void createNewClientServiceTest(){
        Client client = new Client(0);
        clientService.createNewClient(client);
        Assertions.assertNotEquals(0,client.getId());
    }

    @Test
    @Order(2)
    void getClientServiceTest(){
        Client client = clientService.getClient(1);
        Assertions.assertEquals(1, client.getId());
    }

    @Test
    @Order(3)
    void getAllClientsServiceTest(){
        for(int i = 0; i < 5; ++i) {
            clientService.createNewClient(new Client(i));
        }
        Set<Client> allClients = clientService.getAll();
        Assertions.assertTrue(allClients.size() > 5);
    }

    @Test
    @Order(4)
    void deleteClientServiceTest(){
        Assertions.assertTrue(clientService.deleteClient(3));

    }

    @Test
    @Order(5)
    void updateClientServiceTest() {
        Client client = new Client(4);
        client.setClientName("New Client");

        clientService.updateClient(client);
        client = clientService.getClient(4);
        Assertions.assertEquals("New Client", client.getClientName());

    }
}
