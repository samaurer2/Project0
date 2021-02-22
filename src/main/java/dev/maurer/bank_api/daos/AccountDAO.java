package dev.maurer.BankAPI.daos;

import dev.maurer.BankAPI.entitiy.Account;

import java.util.Set;

public interface AccountDAO {

    /**
     * Creates a new account
     * @param accountId unique account id number
     * @return the newly created account
     */
    Account createAccount(Account account);

    /**
     * Returns all accounts as a set
     * @return as set of all accounts
     */
    Set<Account> getAllAccounts(int clientId);

    /**
     * Returns a set of accounts whose unique id's satisfy the boolean condition:
     * balance>loAccountBalance && balance<highAccountBalance
     * @param lowAccountBalance lower bound for range search
     * @param highAccountBalance upper bound for range search
     * @return a set of all accounts satisfying the condition
     */
    Set<Account> getRangeAccounts(int clientId, double lowAccountBalance, double highAccountBalance);

    /**
     * Gets adn returns the account with the specified id
     * @param accountId the unique id of the account
     * @return the account holding the unique id
     */
    Account getAccount(int clientId, int accountId);

    /**
     * Updates and existing account
     * @param account the account to be updated
     * @return the account that was updated
     */
    Account updateAccount(int clientId,Account account);

    /**
     * Deletes the specified account
     * @param accountId the unique id of the account to be deleted
     * @return true if deletion is successful, else false
     */
    boolean deleteAccount(int clientId, int accountId);

}
