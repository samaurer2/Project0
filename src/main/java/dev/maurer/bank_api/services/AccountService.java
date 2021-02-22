package dev.maurer.BankAPI.services;

import dev.maurer.BankAPI.entitiy.Account;
import dev.maurer.BankAPI.exceptions.AccountNotFoundException;
import dev.maurer.BankAPI.exceptions.BadArgumentException;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;

import java.sql.SQLException;
import java.util.Set;

public interface AccountService {

    /**
     * Creates a new account
     * @param account unique account
     * @return the newly created account
     * @throws ClientNotFoundException the client for this account was not found
     * */
    Account createAccount(Account account) throws ClientNotFoundException;

    /**
     * Returns all accounts as a set
     * @return as set of all accounts
     * @throws ClientNotFoundException client with id is not found
     */
    Set<Account> getAllAccounts(int clientId) throws ClientNotFoundException;

    /**
     * Returns a set of accounts whose unique id's satisfy the boolean condition:
     * balance>loAccountBalance && balance<highAccountBalance
     * @param lowAccountBalance lower bound for range search
     * @param highAccountBalance upper bound for range search
     * @return a set of all accounts satisfying the condition
     * @throws BadArgumentException lowBalance is greater than or equal to highBalance
     * @throws ClientNotFoundException client with id is not found
     */
    Set<Account> getRangeAccounts(int clientId, double lowAccountBalance, double highAccountBalance) throws BadArgumentException, ClientNotFoundException;

    /**
     * Gets adn returns the account with the specified id
     * @param accountId the unique id of the account
     * @return the account holding the unique id
     * @throws ClientNotFoundException client with id is not found
     * @throws AccountNotFoundException account with id is not found
     */
    Account getAccount(int clientId, int accountId) throws ClientNotFoundException, AccountNotFoundException;

    /**
     * Updates and existing account
     * @param account the account to be updated
     * @return the account that was updated
     * @throws ClientNotFoundException client with id is not found
     * @throws AccountNotFoundException account with id is not found
     */
    Account updateAccount(int clientId,Account account) throws ClientNotFoundException, AccountNotFoundException;

    /**
     * Deletes the specified account
     * @param accountId the unique id of the account to be deleted
     * @return true if deletion is successful, else false
     * @throws ClientNotFoundException client with id is not found
     * @throws AccountNotFoundException account with id is not found
     */
    boolean deleteAccount(int clientId, int accountId) throws ClientNotFoundException, AccountNotFoundException;
}
