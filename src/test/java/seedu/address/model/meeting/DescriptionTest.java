package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void toString_returnsString() {
        String expectedDescription = "test description";
        String actualDescription = new Description(expectedDescription).toString();
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void equals() {
        Description description = new Description("blah blah blah 05/10/1999 23:59");

        // same values -> returns true
        assertTrue(description.equals(new Description("blah blah blah 05/10/1999 23:59")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new Description("06/11/2100 23:59")));
    }
}
