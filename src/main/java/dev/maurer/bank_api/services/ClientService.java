package dev.maurer.bank_api.services;

import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.exceptions.ClientNotFoundException;

import java.util.Set;

public interface ClientService {
    /**
     * Creates a new client object and returns it.
     * @return the client object created
     */
    Client createNewClient(Client client);

    /**
     * Returns all client objects as a set
     * @return all client objects
     */
    Set<Client> getAllClients();

    /**
     * Returns a client by unique id number or null if none is found
     * @param id unique client id number
     * @return the client with specified id or ClientNotFoundException if none is found
     * @throws ClientNotFoundException
     */
    Client getClient(int id) throws ClientNotFoundException;

    /**
     * Updates an existing client
     * @param client the client that has been updated
     * @return the client that was updated
     */
    Client updateClient(Client client) throws ClientNotFoundException;

    /**
     * Deletes a client
     * @param id the unique client id
     * @return true if deletion was successful, else false
     */
    boolean deleteClient(int id);
}
