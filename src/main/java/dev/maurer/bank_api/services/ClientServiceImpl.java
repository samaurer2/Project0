package dev.maurer.bank_api.services;

import dev.maurer.bank_api.daos.ClientDAO;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.exceptions.ClientNotFoundException;

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
     * @return IMPORTANT CAN RETURN NULL
     */
    @Override
    public Set<Client> getAllClients() {
        return clientDAO.getAll();
    }

    @Override
    public Client getClient(int id) throws ClientNotFoundException {
        if (clientDAO.getClient(id) == null)
            throw new ClientNotFoundException(id);
        return clientDAO.getClient(id);
    }

    @Override
    public Client updateClient(Client client) throws ClientNotFoundException {
        if (clientDAO.updateClient(client) == null)
            throw new ClientNotFoundException(client.getId());

        return clientDAO.updateClient(client);
    }

    @Override
    public boolean deleteClient(int id) {
        if (clientDAO.deleteClient(id))
            return true;
        return false;
    }
}
