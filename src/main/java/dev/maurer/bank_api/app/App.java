package dev.maurer.BankAPI.app;

import dev.maurer.BankAPI.controllers.AccountController;
import dev.maurer.BankAPI.controllers.ClientController;
import dev.maurer.BankAPI.daos.AccountDAO;
import dev.maurer.BankAPI.daos.AccountDaoImpl;
import dev.maurer.BankAPI.daos.ClientDAO;
import dev.maurer.BankAPI.daos.ClientDaoImpl;
import dev.maurer.BankAPI.services.AccountService;
import dev.maurer.BankAPI.services.AccountServiceImpl;
import dev.maurer.BankAPI.services.ClientService;
import dev.maurer.BankAPI.services.ClientServiceImpl;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create();

        ClientDAO clientDAO = new ClientDaoImpl();
        ClientService clientService = new ClientServiceImpl(clientDAO);
        ClientController clientController = new ClientController(clientService);

        AccountDAO accountDAO = new AccountDaoImpl(clientDAO);
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
        app.get("/clients/:id/accounts?amountLessThan=:lowerBound&amountGreaterThan=:upperBound", accountController.getRangeAccountsHandler);
        app.put("/clients/:cid/accounts/:aid", accountController.updateAccountHandler);
        app.delete("/clients/:cid/accounts/:aid", accountController.deleteAccountHandler);

        app.start();

    }
}
