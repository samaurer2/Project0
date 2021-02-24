package dev.maurer.bank_api.daos;

import dev.maurer.bank_api.entitiy.Account;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.utilities.ConnectionUtil;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostgresClientDAO implements ClientDAO {

    @Override
    public Client createNewClient(Client client) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into client (client_name) values (?)";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getClientName());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            client.setId(rs.getInt("client_id"));

            return client;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Client> getAll() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from client";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();
            Set<Client> clientSet = new HashSet<Client>();
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setClientName(rs.getString("client_name"));
                clientSet.add(client);
            }

            return clientSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client getClient(int id) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from client where client_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            Client client = null;
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setClientName(rs.getString("client_name"));
            }
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "update client set client_name = ? where client_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getClientName());
            ps.setInt(2, client.getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            Client newClient = null;
            if (rs.next()) {
                newClient = new Client();
                newClient.setId(rs.getInt("client_id"));
                newClient.setClientName(rs.getString("client_name"));
            }
            return newClient;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteClient(int id) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "delete from client where client_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
