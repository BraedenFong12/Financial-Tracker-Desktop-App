package model;


import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents a savings goal in the personal finance tracker.
 * A savings goal has a name and a target amount.
 * It lets users add savings goals and track progress.
 */

public class Savings implements Writable {

    private String goalName;
    private double targetAmount;
    private double totalContributions;
    private EventLog eventLog;



    // Modifies: this
    // Effects: Initializes the Savings object with the specified goal name and target amount
    public Savings(String goalName, double targetAmount, double totalContributions) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.totalContributions = totalContributions;

    }

    public Savings(String goalName, double targetAmount) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.totalContributions = 0;
        eventLog = EventLog.getInstance();
    }

    public String getGoalName() {
        return goalName;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getTotalContributions() {
        return totalContributions;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    // Requires: amount >= 0
    // Modifies: this
    // Effects: Adds the specified amount to the total contributions made to the savings goal
    public void addContribution(double amount) {
        totalContributions += amount;
        eventLog.logEvent(new Event("Contributed to Savings"));
    }
    // // Effects: Calculates and returns the progress towards the savings goal as a percentage

    public double calculateProgress() {
        if (targetAmount == 0.0) {
            return 0.0;
        }
        return (totalContributions / targetAmount) * 100;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("goalName", goalName);
        json.put("targetAmount", targetAmount);
        json.put("totalContributions", totalContributions);
        return json;
    }

    // EFFECTS: returns a string of all attributes of savings
    @Override
    public String toString() {
        return "Goal: " + goalName + ", Target Amount: $" + targetAmount + ", Total Contributions: $"
                + totalContributions;
    }
}
