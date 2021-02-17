package dev.maurer.BankAPI.services;

import dev.maurer.BankAPI.entitiy.Account;

import java.util.Set;

public class AccountServiceImpl implements AccountService{

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public Set<Account> getAllAccounts(int clientId) {
        return null;
    }

    @Override
    public Set<Account> getRangeAccounts(int clientId, int lowAccountId, int highAccountId) {
        return null;
    }

    @Override
    public Account getAccount(int clientId, int accountId) {
        return null;
    }

    @Override
    public Account updateAccount(int clientId, Account account) {
        return null;
    }

    @Override
    public boolean deleteAccount(int clientId, int accountId) {
        return false;
    }
}
