package persistence;

import model.*;
import model.Expense;
import model.Income;
import model.Savings;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

// Code was referenced from CPSC 210 by Paul Carter.
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of financial tracker to file
    public void writeFinancialTracker(FinancialTracker financialTracker) {
        JSONObject jsonObject = financialTrackerToJson(financialTracker);
        saveToFile(jsonObject.toString(4)); // 4 is for indentation (for better readability)
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // EFFECTS: returns JSON representation of financial tracker
    private JSONObject financialTrackerToJson(FinancialTracker financialTracker) {
        JSONObject json = new JSONObject();
        json.put("balance", financialTracker.getBalance());
        json.put("expenses", expensesToJson(financialTracker.getExpenses()));
        json.put("incomes", incomesToJson(financialTracker.getIncomes()));
        json.put("savingsGoals", savingsGoalsToJson(financialTracker.getSavingsGoals()));
        return json;
    }

    // EFFECTS: returns JSON representation of expenses
    private JSONArray expensesToJson(List<Expense> expenses) {
        JSONArray jsonArray = new JSONArray();
        for (Expense expense : expenses) {
            jsonArray.put(expense.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns JSON representation of incomes
    private JSONArray incomesToJson(List<Income> incomes) {
        JSONArray jsonArray = new JSONArray();
        for (Income income : incomes) {
            jsonArray.put(income.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns JSON representation of savings goals
    private JSONArray savingsGoalsToJson(List<Savings> savingsGoals) {
        JSONArray jsonArray = new JSONArray();
        for (Savings savings : savingsGoals) {
            jsonArray.put(savings.toJson());
        }
        return jsonArray;
    }
}
