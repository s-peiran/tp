package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.model.meeting.Time;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("^")); // only non-alphanumeric characters
        assertFalse(Time.isValidTime("peter*")); // contains non-alphanumeric characters
        assertFalse(Time.isValidTime("35/10/1999 23:59")); // impossible date
        assertFalse(Time.isValidTime("05/10/1999 25:59")); // impossible time

        // valid time
        assertTrue(Time.isValidTime("05/10/1999 23:59"));
        assertTrue(Time.isValidTime("05/10/2100 23:59"));
    }

    @Test
    public void toString_returnsString() {
        String expectedTime = "05/10/1999 23:59";
        String actualTime = new Time(expectedTime).toString();
        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void equals() {
        Time time = new Time("05/10/1999 23:59");

        // same values -> returns true
        assertTrue(time.equals(new Time("05/10/1999 23:59")));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals(5.0f));

        // different values -> returns false
        assertFalse(time.equals(new Time("06/11/2100 23:59")));
    }
}
