package dev.maurer.bank_api.exceptions;

public class BadArgumentException extends Exception{

    public BadArgumentException(String message) {
        super(message);
    }
}
