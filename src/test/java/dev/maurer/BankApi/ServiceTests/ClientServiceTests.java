//package dev.maurer.BankApi.ServiceTests;
//
//
//import dev.maurer.bank_api.daos.ClientDAO;
//import dev.maurer.bank_api.daos.ClientDaoImpl;
//import dev.maurer.bank_api.daos.PostgresClientDAO;
//import dev.maurer.bank_api.entitiy.Client;
//import dev.maurer.bank_api.services.ClientService;
//import dev.maurer.bank_api.services.ClientServiceImpl;
//import org.junit.jupiter.api.*;
//
//import java.util.Set;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class ClientServiceTests {
//
//    static ClientDAO clientDAO;
//    static ClientService clientService;
//
//    @BeforeAll
//    static void setUp() {
//        clientDAO = new PostgresClientDAO();
//        clientService = new ClientServiceImpl(clientDAO);
//    }
//
//
//    @Test
//    @Order(1)
//    void createNewClientServiceTest() {
//        Client client = new Client();
//        try {
//            clientService.createNewClient(client);
//            Assertions.assertNotEquals(0, client.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assertions.fail();
//        }
//    }
//
//    @Test
//    @Order(2)
//    void getClientServiceTest() {
//        Client client = null;
//        try {
//            client = clientService.getClient(1);
//            Assertions.assertEquals(1, client.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    @Order(3)
//    void getAllClientsServiceTest() {
//        try {
//            for (int i = 0; i < 5; ++i) {
//                clientService.createNewClient(new Client());
//            }
//            Set<Client> allClients = clientService.getAllClients();
//            Assertions.assertTrue(allClients.size() > 5);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @Order(4)
//    void deleteClientServiceTest() {
//        try {
//            System.out.println(clientService.getClient(3));
//            Assertions.assertTrue(clientService.deleteClient(3));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    @Order(5)
//    void updateClientServiceTest() {
//        Client client = new Client();
//        client.setId(4);
//        client.setClientName("New Client");
//
//        try {
//            clientService.updateClient(client);
//            client = clientService.getClient(4);
//            Assertions.assertEquals("New Client", client.getClientName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
