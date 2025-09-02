package model;

import java.time.LocalDateTime;

public class Movimiento {

	public enum Type { DEPOSIT, WITHDRAW }

    private LocalDateTime date;
    private Type type;
    private int amount;
    private int balance;

    // Getters y setters
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
}
