package dev.maurer.bank_api.entitiy;

public class Client {
    private int id;
    private String clientName;

    public Client(
    ){}

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
