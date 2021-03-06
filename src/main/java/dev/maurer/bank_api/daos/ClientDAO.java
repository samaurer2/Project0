package dev.maurer.bank_api.daos;

import dev.maurer.bank_api.entitiy.Client;

import java.util.Set;

public interface ClientDAO {

    /**
     * Creates a new client object and returns it.
     * @return the client object created
     */
    Client createNewClient(Client client);

    /**
     * Returns all client objects as a set
     * @return all client objects
     */
    Set<Client> getAll();

    /**
     * Returns a client by unique id number or null if none is found
     * @param id unique client id number
     * @return the client with specified id, can return null
     */
    Client getClient(int id);

    /**
     * Updates an existing client
     * @param client the client that has been updated
     * @return the client that was updated, can return null
     */
    Client updateClient(Client client);

    /**
     * Deletes a client
     * @param id the unique client id
     * @return true if deletion was successful, else false
     */
    boolean deleteClient(int id);
}
