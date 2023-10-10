package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceTest {
    Balance testBalance;
    @BeforeEach
    public void runBefore() {
        testBalance = new Balance();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testBalance.getTotalBalance());
    }

    @Test
    public void testAddToBalance() {
        assertEquals(0, testBalance.getTotalBalance());
        testBalance.addToBalance(100);
        assertEquals(100, testBalance.getTotalBalance());
    }

    @Test
    public void testSubtractFromBalance() {
        assertEquals(0, testBalance.getTotalBalance());
        testBalance.addToBalance(100);
        assertEquals(100, testBalance.getTotalBalance());
        testBalance.subtractFromBalance(60);
        assertEquals(40, testBalance.getTotalBalance());
    }

    @Test
    public void testBalanceToJson() {
        Balance balance = new Balance();
        balance.addToBalance(500.0);
        JSONObject json = balance.toJson();

        assertEquals(500.0, json.getDouble("totalBalance"));
    }
}
