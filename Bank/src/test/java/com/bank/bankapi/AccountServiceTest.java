package com.bank.bankapi;

import org.junit.jupiter.api.Test;

import model.Account;
import model.Movimiento;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AccountServiceTest {

    private AccountService service = new AccountService();

    @Test
    void testCreateAccountAndDeposit() {
        // Crear cuenta
        Account acc = service.createAccount("123", 100);
        assertEquals(100, acc.getBalance());

        // Depositar
        service.deposit("123", 50);
        Account updated = service.getAccount("123");
        assertEquals(150, updated.getBalance());

        // Validar movimientos
        List<Movimiento> movimientos = updated.getMovimientos();
        assertEquals(2, movimientos.size()); // 1 por creación, 1 por depósito
        assertEquals(Movimiento.Type.DEPOSIT, movimientos.get(1).getType());
    }

    @Test
    void testWithdrawSuccess() {
        Account acc = service.createAccount("456", 200);
        service.withdraw("456", 50);

        Account updated = service.getAccount("456");
        assertEquals(150, updated.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        Account acc = service.createAccount("789", 20);

        Exception ex = assertThrows(ConflictException.class, () -> {
            service.withdraw("789", 50);
        });

        assertEquals("Saldo insuficiente.", ex.getMessage());
    }
}
