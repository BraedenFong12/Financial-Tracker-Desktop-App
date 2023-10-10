package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseTest {

    Expense testExpense;

    @BeforeEach
    public void runBefore() {
        testExpense = new Expense("Groceries", 50.0);
    }

    @Test
    public void testConstructor(){
        assertEquals("Groceries", testExpense.getDescription());
        assertEquals(50.0, testExpense.getAmount());


    }

    @Test
    public void testsetAmount() {
        testExpense.setAmount(500.0);
        assertEquals(500.0, testExpense.getAmount());

    }

    @Test
    public void testSetDescription() {
        testExpense.setDescription("Aquarian Slayer");
        assertEquals("Aquarian Slayer", testExpense.getDescription());
    }


    @Test
    public void testExpenseToJson() {
        Expense expense = new Expense("Groceries", 50.0);
        JSONObject json = expense.toJson();

        assertEquals("Groceries", json.getString("description"));
        assertEquals(50.0, json.getDouble("amount"));
    }




}
