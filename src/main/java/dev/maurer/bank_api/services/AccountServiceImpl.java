package dev.maurer.BankAPI.services;

import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.entitiy.Account;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.exceptions.AccountNotFoundException;
import dev.maurer.BankAPI.exceptions.BadArgumentException;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;

import java.util.Set;

public class AccountServiceImpl implements AccountService{

    AccountDAO accountDAO;
    ClientDAO clientDAO;

    public AccountServiceImpl(ClientDAO clientDAO, AccountDAO accountDAO) {
        this.clientDAO = clientDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public Account createAccount(Account account) throws ClientNotFoundException {
        Client client = clientDAO.getClient(account.getClientId());
        if (client == null)
            throw new ClientNotFoundException(account.getClientId());

        return accountDAO.createAccount(account);
    }

    @Override
    public Set<Account> getAllAccounts(int clientId) throws ClientNotFoundException {
        Client client = clientDAO.getClient(clientId);
        if (client == null)
            throw new ClientNotFoundException(clientId);

        return accountDAO.getAllAccounts(clientId);
    }

    @Override
    public Set<Account> getRangeAccounts(int clientId, double lowAccountBalance, double highAccountBalance) throws ClientNotFoundException, BadArgumentException {
        if (lowAccountBalance >= highAccountBalance)
            throw new BadArgumentException("lowAccountBalance was lower than or equal to highAccountBalance");

        Client client = clientDAO.getClient(clientId);
        if (client == null)
            throw new ClientNotFoundException(clientId);

        return accountDAO.getRangeAccounts(clientId, lowAccountBalance, highAccountBalance);
    }

    @Override
    public Account getAccount(int clientId, int accountId) throws ClientNotFoundException, AccountNotFoundException {
        Client client = clientDAO.getClient(clientId);
        if (client == null)
            throw new ClientNotFoundException(clientId);

        Account account = accountDAO.getAccount(clientId, accountId);
        if (account == null)
            throw new AccountNotFoundException(accountId);

        return account;
    }

    @Override
    public Account updateAccount(int clientId, Account account) throws ClientNotFoundException, AccountNotFoundException{
        Client client = clientDAO.getClient(clientId);
        if (client == null)
            throw new ClientNotFoundException(clientId);

        Account updatedAccount = accountDAO.updateAccount(clientId,account);
        if (updatedAccount == null)
            throw new AccountNotFoundException(account.getAccountId());

        return updatedAccount;
    }

    @Override
    public boolean deleteAccount(int clientId, int accountId) throws ClientNotFoundException, AccountNotFoundException{
        Client client = clientDAO.getClient(clientId);
        if (client == null)
            throw new ClientNotFoundException(clientId);

        Account account = accountDAO.getAccount(clientId, accountId);
        if (account == null)
            throw new AccountNotFoundException(accountId);

        return accountDAO.deleteAccount(clientId, accountId);
    }
}
