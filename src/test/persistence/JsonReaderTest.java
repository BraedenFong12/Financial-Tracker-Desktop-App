package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FinancialTracker tracker = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            FinancialTracker tracker = reader.read();
            assertEquals(0, tracker.getBalance());
            assertEquals(0, tracker.getExpenses().size());
            assertEquals(0, tracker.getSavingsGoals().size());
            assertEquals(0, tracker.getIncomes().size());
            // assertEquals(expenses, tracker.getExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testTracker.json");
        try {
            FinancialTracker tracker = reader.read();
            assertEquals(9500, tracker.getBalance());
            assertEquals(2, tracker.getExpenses().size());
            assertEquals(2, tracker.getSavingsGoals().size());
            assertEquals(1, tracker.getIncomes().size());
            System.out.println(tracker.getExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

