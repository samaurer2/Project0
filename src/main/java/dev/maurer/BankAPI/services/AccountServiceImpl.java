package dev.maurer.BankAPI.services;

import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.entitiy.Account;

import java.util.Set;

public class AccountServiceImpl implements AccountService{

    AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(Account account) {
        return accountDAO.createAccount(account);
    }

    @Override
    public Set<Account> getAllAccounts(int clientId) {
        return accountDAO.getAllAccounts(clientId);
    }

    @Override
    public Set<Account> getRangeAccounts(int clientId, int lowAccountId, int highAccountId) {
        return accountDAO.getRangeAccounts(clientId, lowAccountId, highAccountId);
    }

    @Override
    public Account getAccount(int clientId, int accountId) {
        return accountDAO.getAccount(clientId, accountId);
    }

    @Override
    public Account updateAccount(int clientId, Account account) {
        return accountDAO.updateAccount(clientId, account);
    }

    @Override
    public boolean deleteAccount(int clientId, int accountId) {
        return accountDAO.deleteAccount(clientId, accountId);
    }
}
