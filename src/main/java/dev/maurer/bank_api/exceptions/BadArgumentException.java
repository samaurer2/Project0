package dev.maurer.BankAPI.exceptions;

public class BadArgumentException extends Exception{

    public BadArgumentException(String message) {
        super(message);
    }
}
