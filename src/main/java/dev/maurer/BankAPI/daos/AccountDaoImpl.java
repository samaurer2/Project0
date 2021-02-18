package dev.maurer.BankAPI.daos;

import dev.maurer.BankAPI.entitiy.Account;

import java.util.*;

public class AccountDaoImpl implements AccountDAO {

    private static int idMaker = 0;
    private HashMap<Integer, List<Account>> local;

    public AccountDaoImpl() {
        local = new HashMap<Integer, List<Account>>();
    }


    @Override
    public Account createAccount(Account account) {
        int clientId = account.getClientId();
        List<Account> accountList;

        if (!local.containsKey(clientId)) {
            accountList = new ArrayList<Account>();
            local.put(clientId, accountList);
        }
        else {
            accountList = local.get(clientId);
        }
        account.setAccountId(++idMaker);
        accountList.add(account);
        return account;
    }

    @Override
    public Set<Account> getAllAccounts(int clientId) {
        List<Account> allAccounts;
        if (!local.containsKey(clientId))
            return null;
        else
            allAccounts = local.get(clientId);

        Set<Account> accountSet = new HashSet<Account>();
        accountSet.addAll(allAccounts);

        return accountSet;
    }

    @Override
    public Set<Account> getRangeAccounts(int clientId, double lowAccountBalance, double highAccountBalance) {
        List<Account> accounts;
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
        List<Account> accounts;
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
        List<Account> accounts = local.get(clientId);
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
        List<Account> accounts = local.get(clientId);
        for (Account a: accounts) {
            if (a.getAccountId() == accountId) {
                accounts.remove(a);
                return true;
            }
        }
        return false;
    }
}
