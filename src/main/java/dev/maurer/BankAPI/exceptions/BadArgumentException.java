package dev.maurer.BankAPI.exceptions;

public class BadArgumentException extends Exception{

    public BadArgumentException() {
        super("param lowId was greater than or equal to param highId.");
    }
}
