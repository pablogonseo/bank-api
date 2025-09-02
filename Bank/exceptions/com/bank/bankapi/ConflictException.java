package com.bank.bankapi;

public class ConflictException extends RuntimeException {
	public ConflictException(String msg) {
		super(msg);
	}
}