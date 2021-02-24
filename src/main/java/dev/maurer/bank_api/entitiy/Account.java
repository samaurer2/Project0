package dev.maurer.bank_api.entitiy;

public class Account {

    private int clientId;
    private int accountId;
    private double balance;
    private String accountName;

    public Account() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
