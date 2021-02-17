package dev.maurer.BankAPI.services;

import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.entitiy.Client;

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

    @Override
    public Set<Client> getAllClients() {
        return clientDAO.getAll();
    }

    @Override
    public Client getClient(int id) {
        if(clientDAO.getClient(id) == null)
            return null;
        return clientDAO.getClient(id);
    }

    @Override
    public Client updateClient(Client client) {
        clientDAO.updateClient(client);
        return client;
    }

    @Override
    public boolean deleteClient(int id) {
        return clientDAO.deleteClient(id);
    }
}
