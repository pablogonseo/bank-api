package com.bank.bankapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Account;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public Account create(@RequestParam String number, @RequestParam(defaultValue="0") int initialBalance) {
        return service.createAccount(number, initialBalance);
    }

    @PostMapping("/{number}/deposit")
    public Account deposit(@PathVariable String number, @RequestParam int amount) {
        return service.deposit(number, amount);
    }

    @PostMapping("/{number}/withdraw")
    public Account withdraw(@PathVariable String number, @RequestParam int amount) {
        return service.withdraw(number, amount);
    }

    @GetMapping("/{number}")
    public Account get(@PathVariable String number) {
        return service.getAccount(number);
    }

    @GetMapping("/{number}/movements")
    public Object movements(@PathVariable String number) {
        return service.getAccount(number).getMovimientos();
    }
}
