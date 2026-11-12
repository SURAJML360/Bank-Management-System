package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    // Account details
    private String accountNumber;
    private String name;
    private int age;
    private String phone;
    private String address;
    private String accountType;
    private String pin;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    // Constructor
    public Account(
            String accountNumber,
            String name,
            int age,
            String phone,
            String address,
            String accountType,
            String pin,
            double balance) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.accountType = accountType;
        this.pin = pin;
        this.balance = balance;
    }

    // Getters

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Deposit money
    public void deposit(double amount) {

        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    // Add a transaction to account history
    public void addTransaction(Transaction transaction) {

        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    // Get transaction history
    public List<Transaction> getTransactions() {

        return Collections.unmodifiableList(transactions);
    }
}