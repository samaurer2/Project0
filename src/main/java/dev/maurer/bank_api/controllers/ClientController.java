package dev.maurer.bank_api.controllers;

import com.google.gson.Gson;
import dev.maurer.bank_api.app.App;
import dev.maurer.bank_api.entitiy.Client;
import dev.maurer.bank_api.exceptions.ClientNotFoundException;
import dev.maurer.bank_api.services.ClientService;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class ClientController {

    private ClientService clientService;
    private Gson gson;
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
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
            logger.info("Create client");
            client = clientService.createNewClient(client);
            ctx.result(gson.toJson(client));
            ctx.status(201);
            logger.info("Client created: " + gson.toJson(client));
        } catch (Exception e) {
            ctx.result(gson.toJson(null));
            ctx.status(400);
            logger.info(e.getMessage());
        }
    };

    public Handler getClientHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client;
        try {
            client = clientService.getClient(id);
            logger.info("Getting client: " + gson.toJson(client));
            ctx.result(gson.toJson(client));
            ctx.status(200);
            logger.info("Success");
        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());
        }

    };

    public Handler getAllClientsHandler = (ctx) -> {
        Set<Client> allClients;
        try {
            logger.info("Getting all clients");
            allClients = clientService.getAllClients();
            ctx.result(gson.toJson(allClients));
            ctx.status(200);
            logger.info("Success");
        } catch (Exception e) {
            ctx.status(404);
            logger.info(e.getMessage());
        }
    };

    public Handler updateClientHandler = (ctx) -> {
        String body = ctx.body();
        Client client;
        int clientId = Integer.parseInt(ctx.pathParam("id"));
        try {
            client = gson.fromJson(body, Client.class);
            client.setId(clientId); //fixes id in body to match request param
            client = clientService.updateClient(client);
            ctx.result(gson.toJson(client));
            ctx.status(200);
            logger.info("Success");
        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());
        } catch (Exception e) {
            ctx.status(400);
            logger.info(e.getMessage());
        }
    };

    public Handler deleteClientHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("id"));
        if (clientService.deleteClient(clientId)) {
            ctx.result("Client deleted");
            ctx.status(200);
            logger.info("Success");
        } else {
            ctx.result("Cannot delete");
        }
    };

}
