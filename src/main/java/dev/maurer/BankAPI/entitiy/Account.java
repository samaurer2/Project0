package dev.maurer.BankAPI.entitiy;

public class Account {

    private int clientId;
    private int accountId;
    private double balance;

    public Account(int clientId, int accountId, double balance) {
        this.clientId = clientId;
        this.accountId = accountId;
        this.balance = balance;
    }

    public Account(int clientId, int accountId) {
            this.clientId = clientId;
            this.accountId = accountId;
            this.balance = 0;
    }

    public int getClientId() {
        return clientId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "clientId=" + clientId +
                ", accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
