package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PlaceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Place(null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidPlace = "";
        assertThrows(IllegalArgumentException.class, () -> new Place(invalidPlace));
    }

    @Test
    public void isValidPlace() {
        // null place
        assertThrows(NullPointerException.class, () -> Place.isValidPlace(null));

        // invalid place
        assertFalse(Place.isValidPlace("")); // empty string
        assertFalse(Place.isValidPlace(" ")); // spaces only
        assertFalse(Place.isValidPlace("^")); // only non-alphanumeric characters
        assertFalse(Place.isValidPlace("peter*")); // contains non-alphanumeric characters

        // valid place
        assertTrue(Place.isValidPlace("peter jack")); // alphabets only
        assertTrue(Place.isValidPlace("12345")); // numbers only
        assertTrue(Place.isValidPlace("peter the 2nd")); // alphanumeric characters
        assertTrue(Place.isValidPlace("Capital Tan")); // with capital letters
        assertTrue(Place.isValidPlace("David Roger Jackson Ray Jr 2nd")); // long places
    }

    @Test
    public void toString_returnsString() {
        String expectedPlace = "test place";
        String actualPlace = new Place(expectedPlace).toString();
        assertEquals(expectedPlace, actualPlace);
    }

    @Test
    public void equals() {
        Place place = new Place("Valid Place");

        // same values -> returns true
        assertTrue(place.equals(new Place("Valid Place")));

        // same object -> returns true
        assertTrue(place.equals(place));

        // null -> returns false
        assertFalse(place.equals(null));

        // different types -> returns false
        assertFalse(place.equals(5.0f));

        // different values -> returns false
        assertFalse(place.equals(new Place("Other Valid Place")));
    }
}
