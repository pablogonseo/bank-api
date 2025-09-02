package com.bank.bankapi;

import model.Account;
import model.Movimiento;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
	private Map<String, Account> store = new HashMap<>();

	public Account createAccount(String number, int initialBalance) {
		if (store.containsKey(number))
			throw new ConflictException("La cuenta ya existe.");
		if (initialBalance < 0)
			throw new BadRequestException("El balance de la cuenta no puede ser negativo.");
		Account acc = new Account();
		acc.setNumber(number);
		acc.setBalance(initialBalance);
		if (initialBalance > 0) {
			Movimiento m = new Movimiento();
			m.setType(Movimiento.Type.DEPOSIT);
			m.setAmount(initialBalance);
			m.setBalance(initialBalance);
			m.setDate(LocalDateTime.now());
			acc.getMovimientos().add(m);
		}
		store.put(number, acc);
		return acc;
	}

	public Account deposit(String number, int amount) {
		Account acc = store.get(number);
		if (acc == null)
			throw new NotFoundException("Cuenta no encontrada.");
		if (amount <= 0)
			throw new BadRequestException("La cantidad debe ser superior a 0.");
		acc.setBalance(acc.getBalance() + amount);
		Movimiento m = new Movimiento();
		m.setType(Movimiento.Type.DEPOSIT);
		m.setAmount(amount);
		m.setBalance(acc.getBalance());
		m.setDate(LocalDateTime.now());
		acc.getMovimientos().add(m);
		return acc;
	}

	public Account withdraw(String number, int amount) {
		Account acc = store.get(number);
		if (acc == null)
			throw new NotFoundException("Cuenta no encontrada.");
		if (amount <= 0)
			throw new BadRequestException("La cantidad debe ser superior a 0.");
		if (acc.getBalance() - amount < 0)
			throw new ConflictException("Saldo insuficiente.");
		acc.setBalance(acc.getBalance() - amount);
		Movimiento m = new Movimiento();
		m.setType(Movimiento.Type.WITHDRAW);
		m.setAmount(amount);
		m.setBalance(acc.getBalance());
		m.setDate(LocalDateTime.now());
		acc.getMovimientos().add(m);
		return acc;
	}

	public Account getAccount(String number) {
		Account acc = store.get(number);
		if (acc == null)
			throw new NotFoundException("Cuenta no encontrada.");
		return acc;
	}
}
