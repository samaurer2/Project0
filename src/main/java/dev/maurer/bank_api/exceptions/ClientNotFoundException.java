package dev.maurer.BankAPI.exceptions;

import java.sql.SQLException;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException(int id) {
        super("Client " + id + " does not exist.");
    }
}
