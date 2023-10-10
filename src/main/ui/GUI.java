package ui;

import model.*;
import model.Event;
import model.FinancialTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {
    private JFrame frame;
    private FinancialTracker financialTracker;
    private EventLog eventLog;

    public GUI() {
        financialTracker = new FinancialTracker();
        eventLog = EventLog.getInstance();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Financial Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Add spacing between buttons


        addButton("Add Income", e -> addIncome(), mainPanel);
        addButton("Add Expense", e -> addExpense(), mainPanel);
        addButton("Add Savings Goal", e -> addSavingsGoal(), mainPanel);
        addButton("Contribute to Savings", e -> contributeToSavings(), mainPanel);
        addButton("Save Data", e -> saveData(), mainPanel);
        addButton("Load Data", e -> loadData(), mainPanel);
        addButton("Display Summary", e -> displaySummary(), mainPanel);
        addButton("Exit", e -> exitProgram(), mainPanel);

        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add space around the panel
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addButton(String label, ActionListener listener, JPanel panel) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        button.setBackground(new Color(100, 149, 237)); // Royal blue button color
        button.setForeground(Color.black);
        button.setFocusPainted(false);
        panel.add(button);
    }

    private void addIncome() {
        String source = JOptionPane.showInputDialog(frame, "Enter source of income:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter amount of income:"));

        Income income = new Income(source, amount);
        financialTracker.addIncome(income);
        financialTracker.updateBalance(amount);

        JOptionPane.showMessageDialog(frame, "Added income successfully.");
    }

    private void addExpense() {
        String description = JOptionPane.showInputDialog(frame, "Enter description of expense:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter amount of expense:"));

        Expense expense = new Expense(description, amount);
        financialTracker.addExpense(expense);
        financialTracker.updateBalance(-amount);

        JOptionPane.showMessageDialog(frame, "Added expense successfully.");
    }

    private void addSavingsGoal() {
        String goalName = JOptionPane.showInputDialog(frame, "Enter savings goal name:");
        double targetAmount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter target amount:"));

        Savings savings = new Savings(goalName, targetAmount);
        financialTracker.addSavingsGoal(savings);

        JOptionPane.showMessageDialog(frame, "Added savings goal successfully.");
    }

    private void contributeToSavings() {
        String goalName = JOptionPane.showInputDialog(frame, "Enter savings goal name:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter amount to contribute:"));

        Savings savings = financialTracker.findSavingsGoal(goalName);
        if (savings != null) {
            savings.addContribution(amount);
            financialTracker.updateBalance(-amount);
            JOptionPane.showMessageDialog(frame, "Contributed to savings goal successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Savings goal not found.");
        }
    }

    private void loadData() {
        JsonReader jsonReader = new JsonReader("./data/financialTracker.json");
        try {
            financialTracker = jsonReader.read();
            JOptionPane.showMessageDialog(frame, "Data loaded successfully.");
            eventLog.logEvent(new Event("Data loaded"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error: Unable to load data from file.");
        }
    }

    private void saveData() {
        JsonWriter jsonWriter = new JsonWriter("./data/financialTracker.json");
        try {
            jsonWriter.open();
            jsonWriter.writeFinancialTracker(financialTracker);
            jsonWriter.close();
            eventLog.logEvent(new Event("Data saved"));
            JOptionPane.showMessageDialog(frame, "Data saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error: Unable to save data to file.");
        }
    }



    private void displaySummary() {
        StringBuilder summary = new StringBuilder("===== Summary =====\n");

        summary.append("Expenses:\n");
        for (Expense expense : financialTracker.getExpenses()) {
            summary.append("-").append(expense.getDescription()).append(": $").append(expense.getAmount()).append("\n");
        }

        summary.append("Incomes:\n");
        for (Income income : financialTracker.getIncomes()) {
            summary.append("  + ").append(income.getSource()).append(" : $").append(income.getAmount()).append("\n");
        }

        summary.append("Savings Goals:\n");
        for (Savings savings : financialTracker.getSavingsGoals()) {
            summary.append("  * ").append(savings.getGoalName())
                    .append(" : Target: $").append(savings.getTargetAmount())
                    .append(", Contributions: $").append(savings.getTotalContributions()).append("\n");
        }

        summary.append("Balance: $").append(financialTracker.getBalance()).append("\n");

        JTextArea textArea = new JTextArea(summary.toString());
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(frame, scrollPane, "Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exitProgram() {
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?",
                "Exit", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            for (model.Event e: EventLog.getInstance()) {
                System.out.println(e.toString());
            }
            System.exit(0); // Exit the program
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SplashScreen());
    }
}
