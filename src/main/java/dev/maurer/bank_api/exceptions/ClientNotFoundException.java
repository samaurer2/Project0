package dev.maurer.bank_api.exceptions;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException(int id) {
        super("Client " + id + " does not exist.");
    }
}
