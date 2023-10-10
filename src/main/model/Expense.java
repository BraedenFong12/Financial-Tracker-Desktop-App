package model;

import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents an expense in the personal finance tracker.
 * An expense has a description and an amount.
 * Lets users add expenses
 */
public class Expense implements Writable {

    private String description;
    private double amount;


    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("amount", amount);
        return json;
    }
}

