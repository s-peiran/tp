package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.model.meeting.Location;

public class LocationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // invalid location
        assertFalse(Location.isValidLocation("")); // empty string
        assertFalse(Location.isValidLocation(" ")); // spaces only
        assertFalse(Location.isValidLocation("^")); // only non-alphanumeric characters
        assertFalse(Location.isValidLocation("peter*")); // contains non-alphanumeric characters

        // valid location
        assertTrue(Location.isValidLocation("peter jack")); // alphabets only
        assertTrue(Location.isValidLocation("12345")); // numbers only
        assertTrue(Location.isValidLocation("peter the 2nd")); // alphanumeric characters
        assertTrue(Location.isValidLocation("Capital Tan")); // with capital letters
        assertTrue(Location.isValidLocation("David Roger Jackson Ray Jr 2nd")); // long locations
    }

    @Test
    public void toString_returnsString() {
        String expectedLocation = "test location";
        String actualLocation = new Location(expectedLocation).toString();
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void equals() {
        Location location = new Location("Valid Location");

        // same values -> returns true
        assertTrue(location.equals(new Location("Valid Location")));

        // same object -> returns true
        assertTrue(location.equals(location));

        // null -> returns false
        assertFalse(location.equals(null));

        // different types -> returns false
        assertFalse(location.equals(5.0f));

        // different values -> returns false
        assertFalse(location.equals(new Location("Other Valid Location")));
    }
}
