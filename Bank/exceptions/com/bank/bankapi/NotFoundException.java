package com.bank.bankapi;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String msg) {
		super(msg);
	}
}