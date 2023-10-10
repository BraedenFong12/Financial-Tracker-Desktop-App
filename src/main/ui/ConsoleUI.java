package ui;

import model.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;
    private FinancialTracker financialTracker;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        financialTracker = new FinancialTracker();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();
            performAction(choice);
            exit = (choice == 8);
        }
    }


    private void performAction(int choice) {
        switch (choice) {
            case 1: addExpense();
                break;
            case 2: addIncome();
                break;
            case 3: addSavings();
                break;
            case 4: contributeToSavings();
                break;
            case 5: displaySummary();
                break;
            case 6: saveData();
                break;
            case 7: loadData();
                break;
            case 8:
                System.out.println(financialTracker.getEventLog());
                System.out.println("Exiting the program...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void displayMenu() {
        System.out.println("===== Personal Finance Tracker =====");
        System.out.println("1. Add Expense");
        System.out.println("2. Add Income");
        System.out.println("3. Add Savings");
        System.out.println("4. Contribute to Savings");
        System.out.println("5. Display Summary");
        System.out.println("6. Save Data");
        System.out.println("7. Load Data");
        System.out.println("8. Exit");
        System.out.println();
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void addExpense() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter description of expense: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount of expense: ");
        double amount = scanner.nextDouble();

        Expense expense = new Expense(description, amount);
        financialTracker.addExpense(expense);
        financialTracker.updateBalance(-amount); // Deduct the expense from the balance

        System.out.println("Expense added successfully.");
        System.out.println();
    }

    private void addIncome() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter source of income: ");
        String source = scanner.nextLine();
        System.out.print("Enter amount of income: ");
        double amount = scanner.nextDouble();

        Income income = new Income(source, amount);
        financialTracker.addIncome(income);
        financialTracker.updateBalance(amount); // Add the income to the balance

        System.out.println("Income added successfully.");
        System.out.println();
    }

    private void addSavings() {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter savings goal name: ");
        String goalName = scanner.nextLine();
        System.out.print("Enter target amount: ");
        double targetAmount = scanner.nextDouble();

        Savings savings = new Savings(goalName, targetAmount);
        financialTracker.addSavingsGoal(savings);

        System.out.println("Savings goal added successfully.");
        System.out.println();
    }

    private void contributeToSavings() {
        scanner.nextLine();
        System.out.print("Enter savings goal name: ");
        String goalName = scanner.nextLine();
        System.out.print("Enter amount to contribute: ");
        double amount = scanner.nextDouble();

        Savings savings = financialTracker.findSavingsGoal(goalName);
        if (savings != null) {
            savings.addContribution(amount);
            financialTracker.updateBalance(-amount); // Deduct the contribution from the balance
            System.out.println("Contribution added successfully.");
        } else {
            System.out.println("Savings goal not found.");
        }
        System.out.println();
    }

    private void displaySummary() {
        System.out.println("===== Summary =====");
        System.out.println("Expenses:");
        for (Expense expense : financialTracker.getExpenses()) {
            System.out.println(expense.getDescription() + " - $" + expense.getAmount());
        }
        System.out.println("Incomes:");
        for (Income income : financialTracker.getIncomes()) {
            System.out.println(income.getSource() + " - $" + income.getAmount());
        }
        System.out.println("Savings Goals:");
        for (Savings savings : financialTracker.getSavingsGoals()) {
            System.out.println(savings.getGoalName() + " - Target: $" + savings.getTargetAmount()
                    + ", Contributions: $" + savings.getTotalContributions());
        }
        System.out.println("Balance: $" + financialTracker.getBalance()); // Display the balance
        System.out.println();
    }

    private void saveData() {
        JsonWriter jsonWriter = new JsonWriter("./data/financialTracker.json");
        try {
            jsonWriter.open();
            jsonWriter.writeFinancialTracker(financialTracker); // Save the entire financialTracker object
            jsonWriter.close();
            System.out.println("Data saved successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to save data to file.");
        }
    }

    private void loadData() {
        JsonReader jsonReader = new JsonReader("./data/financialTracker.json");
        try {
            financialTracker = jsonReader.read();
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to load data from file.");
        }
    }

    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.run();
    }
}
