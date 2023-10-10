package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FinancialTracker tracker = new FinancialTracker();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTracker() {
        try {
            FinancialTracker tracker = new FinancialTracker();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.writeFinancialTracker(tracker);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            tracker = reader.read();
            assertEquals(0, tracker.getBalance());
            assertEquals(0, tracker.getBalance());
            assertEquals(0, tracker.getExpenses().size());
            assertEquals(0, tracker.getSavingsGoals().size());
            assertEquals(0, tracker.getIncomes().size());
            // assertEquals(expenses, tracker.getExpenses());
            //assertEquals(0, wr.numThingies());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTracker() {
        try {
            FinancialTracker tracker = new FinancialTracker();
            JsonWriter writer = new JsonWriter("./data/testWriterTracker.json");
            tracker.setBalance(9500);
            Expense e = new Expense("Tony", 100);
            Savings s = new Savings("Aquarian", 200, 0);
            Savings s2 = new Savings("Aquarian2", 200, 0);
            Income i = new Income("aqua", 100);
            tracker.addExpense(e);
            tracker.addSavingsGoal(s);
            tracker.addSavingsGoal(s2);
            tracker.addIncome(i);
            writer.open();
            writer.writeFinancialTracker(tracker);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTracker.json");
            tracker = reader.read();
            assertEquals(9500, tracker.getBalance());
            assertEquals(1, tracker.getExpenses().size());
            assertEquals(2, tracker.getSavingsGoals().size());
            assertEquals(1, tracker.getIncomes().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
