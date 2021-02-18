package dev.maurer.BankAPI.services;

import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;

import java.sql.SQLException;
import java.util.Set;

public class ClientServiceImpl implements ClientService {

    ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO bdao) {
        this.clientDAO = bdao;
    }

    @Override
    public Client createNewClient(Client client) {
        clientDAO.createNewClient(client);
        return client;
    }

    /**
     *
     * @return IMPORTANT CAN RETURN NULL
     */
    @Override
    public Set<Client> getAllClients() {
        return clientDAO.getAll();
    }

    @Override
    public Client getClient(int id) throws ClientNotFoundException {
        if(clientDAO.getClient(id) == null)
            throw new ClientNotFoundException(id);
        return clientDAO.getClient(id);
    }

    @Override
    public Client updateClient(Client client) throws ClientNotFoundException{
        if (clientDAO.updateClient(client) == null)
            throw new ClientNotFoundException(client.getId());

        return clientDAO.updateClient(client);
    }

    @Override
    public boolean deleteClient(int id) throws ClientNotFoundException {
        if (clientDAO.deleteClient(id))
            return true;
        throw new ClientNotFoundException(id);
    }
}
