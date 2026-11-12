package model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LocalDateTime dateTime;
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String description;

    // Constructor
    public Transaction(
            String type,
            double amount,
            double balanceAfter,
            String description) {

        this.dateTime = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }

    // Getters

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getDescription() {
        return description;
    }
}