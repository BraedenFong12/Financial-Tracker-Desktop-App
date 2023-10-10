package model;

import org.json.JSONObject;
import persistence.Writable;

/**
* ALlows users to see their total balance of money
 */
public class Balance implements Writable {

    // constructor
    private double totalBalance;

    // Effects: initializes the balance object
    public Balance() {
        totalBalance = 0.0;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void addToBalance(double amount) {
        totalBalance += amount;
    }

    public void subtractFromBalance(double amount) {
        totalBalance -= amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("totalBalance", totalBalance);
        return json;
    }

}
