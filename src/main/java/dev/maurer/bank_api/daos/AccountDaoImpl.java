package dev.maurer.bank_api.daos;

import dev.maurer.bank_api.entitiy.Account;

import java.util.*;

public class AccountDaoImpl implements AccountDAO {

    private static int idMaker = 0;
    private HashMap<Integer, Set<Account>> local;
    private ClientDAO clientDAO;

    public AccountDaoImpl(ClientDAO clientDAO) {
        local = new HashMap<Integer, Set<Account>>();
        this.clientDAO = clientDAO;
    }


    @Override
    public Account createAccount(Account account) {
        int clientId = account.getClientId();
        Set<Account> accountSet;

        if (!local.containsKey(clientId)) {
            accountSet = new HashSet<Account>();
            local.put(clientId, accountSet);
        }
        else {
            accountSet = local.get(clientId);
        }
        account.setAccountId(++idMaker);
        accountSet.add(account);
        return account;
    }

    @Override
    public Set<Account> getAllAccounts(int clientId) {
        Set<Account> allAccounts;
        if (!local.containsKey(clientId))
            return null;
        else
            allAccounts = local.get(clientId);
        return allAccounts;
    }

    @Override
    public Set<Account> getRangeAccounts(int clientId, double lowAccountBalance, double highAccountBalance) {
        Set<Account> accounts;
        if (!local.containsKey(clientId))
            return null;
        else
            accounts = local.get(clientId);

        Set<Account> accountSet = new HashSet<Account>();
        for (Account a: accounts) {
            if ((a.getBalance() > lowAccountBalance) && (a.getBalance()<highAccountBalance))
                accountSet.add(a);
        }
        return accountSet;
    }

    @Override
    public Account getAccount(int clientId, int accountId) {
        Set<Account> accounts;
        if (!local.containsKey(clientId))
            return null;
        else
            accounts = local.get(clientId);

        for (Account a: accounts) {
            if (a.getAccountId() == accountId)
                return a;
        }
        return null;

    }

    @Override
    public Account updateAccount(int clientId, Account account) {
        Set<Account> accounts = local.get(clientId);
        for (Account a: accounts) {
            if(a.getAccountId() == account.getAccountId()) {
                accounts.remove(a);
                accounts.add(account);
                local.put(clientId, accounts);
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean deleteAccount(int clientId, int accountId) {
        Set<Account> accounts = local.get(clientId);
        for (Account a: accounts) {
            if (a.getAccountId() == accountId) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }
}
