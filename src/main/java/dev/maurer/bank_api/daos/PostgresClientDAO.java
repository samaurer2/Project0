package dev.maurer.bank_api.daos;

import dev.maurer.bank_api.entitiy.Account;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.utilities.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostgresDAO implements ClientDAO, AccountDAO {
    @Override
    public Account createAccount(Account account) {

        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into account(c_id) values (?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, account.getClientId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            account.setAccountId(rs.getInt("account_id"));

            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> getAllAccounts(int clientId) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account where c_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clientId);
            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();
            Set<Account> accountSet = new HashSet<Account>();
            while (rs.next()) {
                Account account = new Account();
                account.setClientId(rs.getInt("c_id"));
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountName(rs.getString("account_name"));
                account.setBalance(rs.getDouble("account_balance"));
                accountSet.add(account);
            }

            return accountSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> getRangeAccounts(int clientId, double lowAccountBalance, double highAccountBalance) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account where c_id = ? and account_balance > ? and account_balance < ?";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clientId);
            ps.setDouble(2, lowAccountBalance);
            ps.setDouble(3, highAccountBalance);
            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();
            Set<Account> accountSet = new HashSet<Account>();
            while (rs.next()) {
                Account account = new Account();
                account.setClientId(rs.getInt("c_id"));
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountName(rs.getString("account_name"));
                account.setBalance(rs.getDouble("account_balance"));
                accountSet.add(account);
            }
            return accountSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account getAccount(int clientId, int accountId) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from account where c_id = ? and account_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clientId);
            ps.setInt(2, accountId);
            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();
            Account account = new Account();
            account.setClientId(rs.getInt("c_id"));
            account.setAccountId(rs.getInt("account_id"));
            account.setAccountName(rs.getString("account_name"));
            account.setBalance(rs.getDouble("account_balance"));
            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Account updateAccount(int clientId, Account account) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "update account set account_name = ?, account_balance = ? where c_id = ? and account_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getAccountName());
            ps.setDouble(2, account.getBalance());
            ps.setInt(3, account.getClientId());
            ps.setInt(4, account.getAccountId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            account.setAccountName(rs.getString("account_name"));
            account.setBalance(rs.getDouble("account_balance"));

            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAccount(int clientId, int accountId) {

        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "delete from account where c_id = ? and account_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, clientId);
            ps.setInt(2, accountId);
            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
            rs.next();
            Client client = new Client();
            client.setId(rs.getInt("client_id"));
            client.setClientName(rs.getString("client_name"));

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
            rs.next();
            Client newClient = new Client();
            newClient.setId(rs.getInt("client_id"));
            newClient.setClientName(rs.getString("client_name"));

            return client;
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
