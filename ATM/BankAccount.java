package ATM;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private final String pin;
    private final List<String> history;

    public BankAccount(double initialBalance, String pin) {
        this.balance = Math.max(0, initialBalance);
        this.pin = pin;
        this.history = new ArrayList<>();
        history.add(String.format("Account created with balance: %.2f", this.balance));
    }

    public boolean checkPin(String inputPin) {
        return pin.equals(inputPin);
    }

    public synchronized boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        history.add(String.format("Deposited: +%.2f | Balance: %.2f", amount, balance));
        return true;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        history.add(String.format("Withdrew: -%.2f | Balance: %.2f", amount, balance));
        return true;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized List<String> getHistory() {
        return new ArrayList<>(history);
    }

    // Optional convenience for recording messages
    public synchronized void record(String msg) {
        history.add(msg);
    }
}
