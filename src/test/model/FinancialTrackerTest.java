package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinancialTrackerTest {
    private FinancialTracker financialTracker;

    @BeforeEach
    public void runBefore() {
        financialTracker = new FinancialTracker();
    }

    @Test
    public void testAddExpense() {
        Expense expense = new Expense("Groceries", 50.0);
        financialTracker.addExpense(expense);

        assertEquals(1, financialTracker.getExpenses().size());
        assertEquals(expense, financialTracker.getExpenses().get(0));
    }

    @Test
    public void testAddIncome() {
        Income income = new Income("Salary", 2000.0);
        financialTracker.addIncome(income);

        assertEquals(1, financialTracker.getIncomes().size());
        assertEquals(income, financialTracker.getIncomes().get(0));
    }

    @Test
    public void testAddSavingsGoal() {
        Savings savingsGoal = new Savings("Vacation Fund", 1000.0);
        financialTracker.addSavingsGoal(savingsGoal);
        assertEquals(1, financialTracker.getSavingsGoals().size());
        assertEquals(savingsGoal, financialTracker.getSavingsGoals().get(0));
    }

    @Test
    public void testFindSavingsGoalExisting() {
        Savings savingsGoal1 = new Savings("Vacation Fund", 1000.0);
        Savings savingsGoal2 = new Savings("Emergency Fund", 500.0);
        financialTracker.addSavingsGoal(savingsGoal1);
        financialTracker.addSavingsGoal(savingsGoal2);
        assertEquals(savingsGoal1, financialTracker.findSavingsGoal("vacation fund"));
        assertEquals(savingsGoal2, financialTracker.findSavingsGoal("emergency fund"));
    }

    @Test
    public void testFindSavingsGoalNonExisting() {
        Savings savingsGoal1 = new Savings("slayer", 1000.0);
        Savings savingsGoal2 = new Savings("aquarian", 500.0);
        financialTracker.addSavingsGoal(savingsGoal1);
        financialTracker.addSavingsGoal(savingsGoal2);

        assertNull(financialTracker.findSavingsGoal("retirement fund"));
    }

    @Test
    public void testUpdateBalance() {
        financialTracker.updateBalance(100.0);
        financialTracker.updateBalance(-50.0);

        assertEquals(50.0, financialTracker.getBalance());
    }

    @Test
    public void testToJson() {
        FinancialTracker financialTracker = new FinancialTracker();


        financialTracker.addExpense(new Expense("aquarian", 100.0));
        financialTracker.addExpense(new Expense("slaya", 200.0));

        financialTracker.addIncome(new Income("Income 1", 500.0));
        financialTracker.addIncome(new Income("Income 2", 300.0));

        financialTracker.addSavingsGoal(new Savings("Goal 1", 1000.0));
        financialTracker.addSavingsGoal(new Savings("Goal 2", 2000.0));

        JSONObject json = financialTracker.toJson();

        assertEquals(0, json.getDouble("balance"));

        JSONArray expensesJsonArray = json.getJSONArray("expenses");
        assertEquals(2, expensesJsonArray.length());

        JSONArray incomesJsonArray = json.getJSONArray("incomes");
        assertEquals(2, incomesJsonArray.length());

        JSONArray savingsGoalsJsonArray = json.getJSONArray("savingsGoals");
        assertEquals(2, savingsGoalsJsonArray.length());
    }
}

