package model;

import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents an income in the personal finance tracker.
 * has a source and an amount.
 * Lets users add income and calculating the total income.
 */
public class Income implements Writable {
    private String source;
    private double amount;

    public Income(String source, double amount) {
        this.source = source;
        this.amount = amount;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("source", source);
        json.put("amount", amount);
        return json;
    }


}
