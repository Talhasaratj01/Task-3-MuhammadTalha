package ATM;

import java.util.List;
import java.util.Scanner;

public class ATM {
    private final BankAccount account;
    private final Scanner scanner = new Scanner(System.in);

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        System.out.println("Welcome to the OOP ATM Demo");

        // PIN login (3 attempts)
        int attempts = 0;
        boolean authenticated = false;
        while (attempts < 3 && !authenticated) {
            System.out.print("Enter PIN: ");
            String pinInput = scanner.nextLine().trim();
            if (account.checkPin(pinInput)) {
                authenticated = true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Exiting.");
            return;
        }

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Choose option: ");
            String choiceLine = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceLine);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                continue;
            }

            switch (choice) {
                case 1:
                    doDeposit();
                    break;
                case 2:
                    doWithdraw();
                    break;
                case 3:
                    System.out.printf("Current balance: %.2f\n", account.getBalance());
                    break;
                case 4:
                    showHistory();
                    break;
                case 5:
                    System.out.println("Exiting. Have a nice day!");
                    running = false;
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Check Balance");
        System.out.println("4) Transaction History");
        System.out.println("5) Exit");
    }

    private void doDeposit() {
        System.out.print("Enter amount to deposit: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (amount <= 0) {
                System.out.println("Invalid amount. Must be greater than zero.");
                return;
            }
            boolean ok = account.deposit(amount);
            if (ok) System.out.printf("Deposit successful: +%.2f\n", amount);
            else System.out.println("Deposit failed.");
        } else {
            String bad = scanner.nextLine();
            System.out.println("Invalid input: '" + bad + "'. Please enter a numeric amount.");
        }
    }

    private void doWithdraw() {
        System.out.print("Enter amount to withdraw: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (amount <= 0) {
                System.out.println("Invalid amount. Must be greater than zero.");
                return;
            }
            if (amount > account.getBalance()) {
                System.out.println("Insufficient funds.");
                return;
            }
            boolean ok = account.withdraw(amount);
            if (ok) System.out.printf("Withdrawal successful: -%.2f\n", amount);
            else System.out.println("Withdrawal failed.");
        } else {
            String bad = scanner.nextLine();
            System.out.println("Invalid input: '" + bad + "'. Please enter a numeric amount.");
        }
    }

    private void showHistory() {
        System.out.println("\n--- Transaction History ---");
        List<String> hist = account.getHistory();
        if (hist.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (String h : hist) {
            System.out.println(h);
        }
    }

    public static void main(String[] args) {
        // Create an account with initial balance and a PIN (PIN: 1234)
        BankAccount acct = new BankAccount(1000.00, "1234");
        ATM atm = new ATM(acct);
        atm.start();
    }
}
