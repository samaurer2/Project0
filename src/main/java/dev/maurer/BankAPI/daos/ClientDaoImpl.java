package dev.maurer.BankAPI.daos;

import dev.maurer.BankAPI.entitiy.Client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Stand-in for a remote server
 */
public class ClientDaoImpl implements ClientDAO {

    private static int idMaker = 0;
    private HashMap<Integer, Client> local;

    public ClientDaoImpl() {
        this.local = new HashMap<Integer, Client>();
    }

    @Override
    public Client createNewClient(Client client) {
        client.setId(++idMaker);
        local.put(client.getId(), client);
        return client;
    }

    @Override
    public Set<Client> getAll() {
        Set<Client> clients = new HashSet<>(local.values());
        return clients;
    }

    @Override
    public Client getClient(int id) {
        if (local.get(id) == null)
            return null;
        return local.get(id);
    }

    @Override
    public Client updateClient(Client client) {
        local.put(client.getId(), client);
        return getClient(client.getId());
    }

    @Override
    public boolean deleteClient(int id) {
        local.remove(id);
        return !local.containsKey(id);
    }
}
