package dev.maurer.BankAPI.entitiy;

import java.util.Set;

public class Client {
    private int id;
    private String clientName;

    public Client(int id, String clientName){
        this.clientName = clientName;
        this.id = id;
    }

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                '}';
    }
}
