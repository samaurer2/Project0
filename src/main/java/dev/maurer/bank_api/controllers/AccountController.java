package dev.maurer.bank_api.controllers;

import com.google.gson.Gson;
import dev.maurer.bank_api.app.App;
import dev.maurer.bank_api.entitiy.Account;
import dev.maurer.bank_api.exceptions.AccountNotFoundException;
import dev.maurer.bank_api.exceptions.ClientNotFoundException;
import dev.maurer.bank_api.services.AccountService;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class AccountController {

    private AccountService accountService;
    private Gson gson;
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        this.gson = new Gson();
    }

    public Handler createAccountHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("id"));
        String body = ctx.body();
        Account account;
        try {
            logger.info("Creating account");
            if (body.equals(""))
                account = new Account();
           else
                account = gson.fromJson(body, Account.class);
            account.setClientId(clientId); //fixes body to match path param
            account = accountService.createAccount(account);
            ctx.result(gson.toJson(account));
            ctx.status(201);
            logger.info("Account created: " + gson.toJson(account));

        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());
        }
    };
    public Handler getAccountHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("cid"));
        int accountId = Integer.parseInt(ctx.pathParam("aid"));
        Account account = null;
        try {
            logger.info("Getting account " + accountId + " for client " + clientId);
            account = accountService.getAccount(clientId, accountId);
            ctx.result(gson.toJson(account, Account.class));
            ctx.status(200);
            logger.info("Account retrieved: " + gson.toJson(account));
        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());
        } catch (AccountNotFoundException e) {
            ctx.result("Account not found");
            ctx.status(404);
            logger.info(e.getMessage());
        }

    };
    public Handler getAllAccountsOfClientHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("id"));
        double lowBalance;
        double highBalance;
        Set<Account> accountSet;
        logger.info("Getting all accounts of client " + clientId);
        if (ctx.queryParam("amountGreaterThan") == null)
            lowBalance = Double.NEGATIVE_INFINITY;
        else
            lowBalance = Double.parseDouble(ctx.queryParam("amountGreaterThan"));

        if (ctx.queryParam("amountLessThan") == null)
            highBalance = Double.POSITIVE_INFINITY;
        else
            highBalance = Double.parseDouble(ctx.queryParam("amountLessThan"));

        try {
            if ((lowBalance == Double.NEGATIVE_INFINITY) && (highBalance == Double.POSITIVE_INFINITY)) {
                accountSet = accountService.getAllAccounts(clientId);
                ctx.result(gson.toJson(accountSet));
                ctx.status(200);
                logger.info("Success");
            } else {
                accountSet = accountService.getRangeAccounts(clientId, lowBalance, highBalance);
                ctx.result(gson.toJson(accountSet));
                ctx.status(200);
                logger.info("Success");
            }

        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());
        }
    };
    public Handler updateAccountHandler = (ctx) -> {
        int clientID = Integer.parseInt(ctx.pathParam("cid"));
        int accountId = Integer.parseInt(ctx.pathParam("aid"));
        String body = ctx.body();
        try {
            Account account = gson.fromJson(body, Account.class);
            logger.info("Updating account " + accountId + " for client " + clientID);
            account.setClientId(clientID);
            account.setAccountId(accountId);
            account = accountService.updateAccount(clientID, account);

            ctx.result(gson.toJson(account));
            ctx.status(200);
            logger.info("Success");
        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());

        } catch (AccountNotFoundException e) {
            ctx.result("Account not found");
            ctx.status(404);
            logger.info(e.getMessage());
        }
    };
    public Handler deleteAccountHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("cid"));
        int accountId = Integer.parseInt(ctx.pathParam("aid"));

        try {
            logger.info("Deleting account" + accountId + "for client " + clientId);
            accountService.deleteAccount(clientId, accountId);
            ctx.result("Account deleted");
            ctx.status(200);
            logger.info("Account deleted");
        } catch (ClientNotFoundException e) {
            ctx.result("Client not found");
            ctx.status(404);
            logger.info(e.getMessage());

        } catch (AccountNotFoundException e) {
            ctx.result("Account not found");
            ctx.status(404);
            logger.info(e.getMessage());
        }
    };
}
