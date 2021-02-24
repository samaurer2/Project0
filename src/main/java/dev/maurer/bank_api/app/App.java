package dev.maurer.bank_api.app;

import dev.maurer.bank_api.controllers.AccountController;
import dev.maurer.bank_api.controllers.ClientController;
import dev.maurer.bank_api.daos.*;
import dev.maurer.bank_api.services.AccountService;
import dev.maurer.bank_api.services.AccountServiceImpl;
import dev.maurer.bank_api.services.ClientService;
import dev.maurer.bank_api.services.ClientServiceImpl;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        ClientDAO clientDAO = new PostgresClientDAO();
        ClientService clientService = new ClientServiceImpl(clientDAO);
        ClientController clientController = new ClientController(clientService);

        AccountDAO accountDAO = new PostgresAccountDAO(clientDAO);
        AccountService accountService = new AccountServiceImpl(clientDAO, accountDAO);
        AccountController  accountController = new AccountController(accountService);

        app.post("/clients", clientController.createClientHandler);
        app.get("/clients", clientController.getAllClientsHandler);
        app.get("/clients/:id", clientController.getClientHandler);
        app.put("/clients/:id", clientController.updateClientHandler);
        app.delete("/clients/:id", clientController.deleteClientHandler);

        app.post("/clients/:id/accounts", accountController.createAccountHandler);
        app.get("/clients/:id/accounts",accountController.getAllAccountsOfClientHandler);
        app.get("/clients/:cid/accounts/:aid", accountController.getAccountHandler);
        app.put("/clients/:cid/accounts/:aid", accountController.updateAccountHandler);
        app.delete("/clients/:cid/accounts/:aid", accountController.deleteAccountHandler);

        app.start();

    }
}
