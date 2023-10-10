package persistence;



import model.Expense;
import model.Income;
import model.Savings;
import model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {


    protected void checkThingy(List<Expense> expenses, List<Income> incomes, List<Savings> savingsGoals, double balance,
    FinancialTracker tracker) {
    assertEquals(expenses, tracker.getExpenses());
    assertEquals(incomes, tracker.getIncomes());
    assertEquals(savingsGoals, tracker.getSavingsGoals());
    assertEquals(balance, tracker.getBalance());
    }

}
