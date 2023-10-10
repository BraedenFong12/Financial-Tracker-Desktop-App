package persistence;

import model.Expense;
import model.Income;
import model.Savings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import model.*;
import org.json.*;

import org.json.*;

// Code was referenced from CPSC 210 Paul Carter.
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs a reader to read from the given source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads financial data from file and returns it as a FinancialTracker object;
    // throws IOException if an error occurs reading data from file
    public FinancialTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFinancialTracker(jsonObject);
    }

    // EFFECTS: reads source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (var stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FinancialTracker from JSON object and returns it
    private FinancialTracker parseFinancialTracker(JSONObject jsonObject) {
        FinancialTracker financialTracker = new FinancialTracker();
        parseExpenses(financialTracker, jsonObject);
        parseIncomes(financialTracker, jsonObject);
        parseSavingsGoals(financialTracker, jsonObject);
        financialTracker.setBalance(jsonObject.getDouble("balance"));
        return financialTracker;
    }

    // MODIFIES: financialTracker
    // EFFECTS: parses expenses from JSON object and adds them to financialTracker
    private void parseExpenses(FinancialTracker financialTracker, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            financialTracker.getExpenses().add(parseExpense(nextExpense));
        }
    }

    // MODIFIES: financialTracker
    // EFFECTS: parses incomes from JSON object and adds them to financialTracker
    private void parseIncomes(FinancialTracker financialTracker, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("incomes");
        for (Object json : jsonArray) {
            JSONObject nextIncome = (JSONObject) json;
            financialTracker.getIncomes().add(parseIncome(nextIncome));
        }
    }

    // MODIFIES: financialTracker
    // EFFECTS: parses savings goals from JSON object and adds them to financialTracker
    private void parseSavingsGoals(FinancialTracker financialTracker, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("savingsGoals");
        for (Object json : jsonArray) {
            JSONObject nextSavings = (JSONObject) json;
            financialTracker.getSavingsGoals().add(parseSavings(nextSavings));
        }
    }

    // EFFECTS: parses expense from JSON object and returns it
    private Expense parseExpense(JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        double amount = jsonObject.getDouble("amount");
        return new Expense(description, amount);
    }

    // EFFECTS: parses income from JSON object and returns it
    private Income parseIncome(JSONObject jsonObject) {
        String source = jsonObject.getString("source");
        double amount = jsonObject.getDouble("amount");
        return new Income(source, amount);
    }

    // EFFECTS: parses savings goal from JSON object and returns it
    private Savings parseSavings(JSONObject jsonObject) {
        String goalName = jsonObject.getString("goalName");
        double targetAmount = jsonObject.getDouble("targetAmount");
        double totalContributions = jsonObject.getDouble("totalContributions");
        return new Savings(goalName, targetAmount, totalContributions);
    }
}