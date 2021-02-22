package dev.maurer.BankAPI.controllers;

import com.google.gson.Gson;
import dev.maurer.BankAPI.entitiy.Account;
import dev.maurer.BankAPI.exceptions.ClientNotFoundException;
import dev.maurer.BankAPI.services.AccountService;
import io.javalin.http.Handler;

public class AccountController {

    private AccountService accountService;
    private Gson gson;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        this.gson = new Gson();
    }

    public Handler createAccountHandler = (ctx) -> {
        int clientId = Integer.parseInt(ctx.pathParam("clientId"));
        String body = ctx.body();
        Account account = gson.fromJson(body, Account.class);
        try {
            account = accountService.createAccount(account);
            ctx.result(gson.toJson(account));
            ctx.status(200);

        } catch (ClientNotFoundException e) {
            ctx.result(gson.toJson(null));
            ctx.status(404);
        }
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
