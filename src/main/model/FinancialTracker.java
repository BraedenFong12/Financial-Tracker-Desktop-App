package model;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class FinancialTracker implements Writable {
    private List<Expense> expenses;
    private List<Income> incomes;
    private List<Savings> savingsGoals;
    private EventLog eventLog;
    private double balance;

    public FinancialTracker() {
        expenses = new ArrayList<>();
        incomes = new ArrayList<>();
        savingsGoals = new ArrayList<>();
        balance = 0.0;
        eventLog = EventLog.getInstance();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        eventLog.logEvent(new Event("Expense added"));
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public EventLog getEventLog() {
        return this.eventLog;
    }

    public void addIncome(Income income) {
        incomes.add(income);
        eventLog.logEvent(new Event("Income added"));
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void addSavingsGoal(Savings savings) {
        savingsGoals.add(savings);
        eventLog.logEvent(new Event("Savings goal added"));
    }

    public Savings findSavingsGoal(String goalName) {
        for (Savings savings : savingsGoals) {
            if (savings.getGoalName().equalsIgnoreCase(goalName)) {
                return savings;
            }
        }
        return null;
    }

    public List<Savings> getSavingsGoals() {
        return savingsGoals;
    }

    public void updateBalance(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Implement the Writable interface methods
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", balance);
        json.put("expenses", expensesToJson());
        json.put("incomes", incomesToJson());
        json.put("savingsGoals", savingsGoalsToJson());
        return json;
    }

    // EFFECTS: returns expenses as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Expense expense : expenses) {
            jsonArray.put(expense.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns incomes as a JSON array
    private JSONArray incomesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Income income : incomes) {
            jsonArray.put(income.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns savings goals as a JSON array
    private JSONArray savingsGoalsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Savings savings : savingsGoals) {
            jsonArray.put(savings.toJson());
        }
        return jsonArray;
    }


}
