package dev.maurer.BankAPI.controllers;

import com.google.gson.Gson;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;
import dev.maurer.BankAPI.services.ClientService;
import io.javalin.http.Handler;

import java.util.Set;


public class ClientController {

    private ClientService clientService;
    private Gson gson;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        gson = new Gson();
    }

    public Handler createClientHandler = (ctx) -> {
        Client client;
        String body = ctx.body();

        try {
            if (body.equals(""))
                client = new Client();
            else
                client = gson.fromJson(body, Client.class);

            client = clientService.createNewClient(client);
            ctx.result(gson.toJson(client));
            ctx.status(201);
        }
        catch (Exception e) {
            ctx.result(gson.toJson(null));
            ctx.status(400);
        }
    };

    public Handler getClientHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client;
        try {
            client = clientService.getClient(id);
            ctx.result(gson.toJson(client));
            ctx.status(200);
        } catch (ClientNotFoundException e) {
            ctx.result(gson.toJson(null));
            ctx.status(404);
        }

    };
    public Handler getAllClientsHandler = (ctx) -> {
        Set<Client> allClients;
        try {
          allClients = clientService.getAllClients();
          ctx.result(gson.toJson(allClients));
          ctx.status(200);
        } catch (Exception e) {
            ctx.result(gson.toJson(null));
            ctx.status(404);
        }
    };
    public Handler updateClientHandler = (ctx) -> {
        String body = ctx.body();
        Client client;

        try {
            client = gson.fromJson(body, Client.class);
            client = clientService.updateClient(client);
            ctx.result(gson.toJson(client));
            ctx.status(200);
        } catch (ClientNotFoundException e) {
            ctx.result(gson.toJson(null));
            ctx.status(404);
        } catch (Exception e) {
            ctx.result("Improperly formatted body");
            ctx.status(400);
        }

    };
    public Handler deleteClientHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("id"));

        try {
            clientService.deleteClient(clientId);
            ctx.result("true");
            ctx.status(200);
        } catch (ClientNotFoundException e) {
            ctx.result("false");
            ctx.status(404);
        }

    };
}
