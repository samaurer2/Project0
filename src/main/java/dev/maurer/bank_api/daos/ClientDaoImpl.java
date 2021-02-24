package dev.maurer.bank_api.daos;

import dev.maurer.bank_api.entitiy.Client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientDaoImpl implements ClientDAO {

    private static int idMaker = 0;
    private HashMap<Integer, Client> doa;

    public ClientDaoImpl() {
        this.doa = new HashMap<Integer, Client>();
    }

    @Override
    public Client createNewClient(Client client) {
        client.setId(++idMaker);
        doa.put(client.getId(), client);
        return client;
    }

    @Override
    public Set<Client> getAll() {
        Set<Client> clients = new HashSet<>(doa.values());
        return clients;
    }

    @Override
    public Client getClient(int id) {
        if (doa.get(id) == null)
            return null;
        return doa.get(id);
    }

    @Override
    public Client updateClient(Client client) {
        if (!doa.containsKey(client.getId()))
            return null;

        doa.put(client.getId(), client);
        return getClient(client.getId());
    }

    @Override
    public boolean deleteClient(int id) {
        if (doa.get(id) == null)
            return false;

        doa.remove(id);
        return true;
    }
}
