package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class incomeTest {
    Income incomeTest;
    
    @BeforeEach
    public void runBefore() {
        incomeTest = new Income("Salary", 2000.0);
    }
    
    @Test
    public void testConstructor() {
        assertEquals("Salary", incomeTest.getSource());
        assertEquals(2000.0, incomeTest.getAmount());
        
        
    }

    @Test
    public void testSetSource() {

        incomeTest.setSource("Aquarian");
        assertEquals("Aquarian", incomeTest.getSource());
    }

    @Test
    public void testSetAmount() {
        incomeTest.setAmount(5000.0);
        assertEquals(5000.0, incomeTest.getAmount());
    }

    @Test
    public void testIncomeToJson() {
        Income income = new Income("Salary", 2000.0);
        JSONObject json = income.toJson();

        assertEquals("Salary", json.getString("source"));
        assertEquals(2000.0, json.getDouble("amount"));
    }



}
