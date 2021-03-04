package dev.maurer.bank_api.exceptions;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(int accountId) {
        super("Account " + accountId + " does not exist.");
    }
}
