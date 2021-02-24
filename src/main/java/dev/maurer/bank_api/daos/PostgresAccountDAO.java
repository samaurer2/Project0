package dev.maurer.bank_api.daos;

import dev.maurer.bank_api.entitiy.Account;
import dev.maurer.bank_api.utilities.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostgresAccountDAO implements AccountDAO {

    private ClientDAO postgresClientDAO;

    public PostgresAccountDAO(ClientDAO postgresClientDAO) {
        this.postgresClientDAO = postgresClientDAO;
    }

    @Override
    public Account createAccount(Account account) {

        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into account(c_id,account_balance,account_name) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, account.getClientId());
            ps.setDouble(2,account.getBalance());
            ps.setString(3,account.getAccountName());
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

            ResultSet rs = ps.executeQuery();
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

            ResultSet rs = ps.executeQuery();
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

            ResultSet rs = ps.executeQuery();
            Account account = null;
            if (rs.next()) {
                account = new Account();
                account.setClientId(rs.getInt("c_id"));
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountName(rs.getString("account_name"));
                account.setBalance(rs.getDouble("account_balance"));
            }
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
            Account newAccount = null;
            if (rs.next()) {
                newAccount = new Account();
                newAccount.setAccountName(rs.getString("account_name"));
                newAccount.setBalance(rs.getDouble("account_balance"));
                newAccount.setAccountId(rs.getInt("account_id"));
                newAccount.setClientId(rs.getInt("c_id"));
            }

            return newAccount;

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
}
