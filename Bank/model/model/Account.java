package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String number;
    private int balance;
    private List<Movimiento> movimientos = new ArrayList<>();

    // Getters y setters
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }
    public List<Movimiento> getMovimientos() { return movimientos; }
    public void setMovimientos(List<Movimiento> movimientos) { this.movimientos = movimientos; }
}
