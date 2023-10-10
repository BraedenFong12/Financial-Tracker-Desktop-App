package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingsTest {
    Savings testSavings;

    @BeforeEach
    public void runBefore() {
        testSavings = new Savings("Tesla Model 3", 40000.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Tesla Model 3", testSavings.getGoalName());
        assertEquals(40000.0, testSavings.getTargetAmount());
    }

    @Test
    public void testSetGoalName() {
        testSavings.setGoalName("Tesla");
        assertEquals("Tesla", testSavings.getGoalName());
    }

    @Test
    public void testSetTargetAmount() {
        testSavings.setTargetAmount(4000.0);
        assertEquals(4000.0, testSavings.getTargetAmount());
    }

    @Test
    public void testAddContribution() {
        testSavings.addContribution(2000.0);
        assertEquals(2000.0, testSavings.getTotalContributions());
        testSavings.addContribution(3000.0);
        assertEquals(5000.0, testSavings.getTotalContributions());
    }

    @Test
    public void testCalculateProgress() {

        testSavings.addContribution(2000.0);
        assertEquals(5.0, testSavings.calculateProgress());

        // Goal amount: 40000.0, Contributions: 0.0, Progress: 0%
        Savings savingsWithoutContributions = new Savings("New Goal", 40000.0);
        assertEquals(0.0, savingsWithoutContributions.calculateProgress());
    }

    @Test
    public void testCalculateProgress2() {
        testSavings.setTargetAmount(0.0);
        assertEquals(0.0, testSavings.calculateProgress());
    }

    @Test
    public void testToString() {
        assertEquals("Goal: Tesla Model 3, Target Amount: $40000.0, Total Contributions: $0.0", testSavings.toString());
    }

    @Test
    public void testSavingsToJson() {
        Savings savingsGoal = new Savings("Vacation Fund", 1000.0);
        JSONObject json = savingsGoal.toJson();

        assertEquals("Vacation Fund", json.getString("goalName"));
        assertEquals(1000.0, json.getDouble("targetAmount"));
        assertEquals(0.0, json.getDouble("totalContributions"));
    }
}
