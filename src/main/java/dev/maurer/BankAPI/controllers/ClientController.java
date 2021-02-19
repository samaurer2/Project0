package dev.maurer.BankAPI.controllers;

import com.google.gson.Gson;
import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.daos.AccountDaoImpl;
import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.daos.ClientDaoImpl;
import dev.maurer.BankAPI.entitiy.Client;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;
import dev.maurer.BankAPI.services.AccountServiceImpl;
import dev.maurer.BankAPI.services.ClientService;
import dev.maurer.BankAPI.services.AccountService;
import dev.maurer.BankAPI.services.ClientServiceImpl;
import io.javalin.http.Handler;

import java.util.Set;


public class ServiceController {

    private ClientService clientService;
    private AccountService accountService;
    private Gson gson;

    public ServiceController() {
        ClientDAO clientDAO = new ClientDaoImpl();
        AccountDAO accountDAO = new AccountDaoImpl(clientDAO);
        clientService = new ClientServiceImpl(clientDAO);
        accountService = new AccountServiceImpl(clientDAO, accountDAO);
        gson = new Gson();
    }

    public Handler createClientHandler = (ctx) -> {
        String body = ctx.body();

        Client client;
        try {
            client = gson.fromJson(body, Client.class);
            client = clientService.createNewClient(client);
            ctx.result(gson.toJson(client));
            ctx.status(201);
        } catch (Exception e) {
            ctx.result(gson.toJson(null));
            ctx.status(404);
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
        Client client = gson.fromJson(body, Client.class);
        try {
            client = clientService.updateClient(client);
            ctx.status(200);
        } catch (ClientNotFoundException e) {
            ctx.result(gson.toJson(null));
            ctx.status(404);
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
    public Handler createAccountHandler = (ctx) -> {
    };
    public Handler getAccountHandler = (ctx) -> {
    };
    public Handler getAllAccountsOfClientHandler = (ctx) -> {
    };
    public Handler getRangeAccountsHandler = (ctx) -> {
    };
    public Handler updateAccountHandler = (ctx) -> {
    };
    public Handler deleteAccountHandler = (ctx) -> {
    };


}
