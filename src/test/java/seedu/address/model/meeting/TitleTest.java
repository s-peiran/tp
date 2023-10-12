package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.model.meeting.Title;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid title
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(Title.isValidTitle("peter*")); // contains non-alphanumeric characters

        // valid title
        assertTrue(Title.isValidTitle("peter jack")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("peter the 2nd")); // alphanumeric characters
        assertTrue(Title.isValidTitle("Capital Tan")); // with capital letters
        assertTrue(Title.isValidTitle("David Roger Jackson Ray Jr 2nd")); // long titles
    }

    @Test
    public void toString_returnsString() {
        String expectedTitle = "test title";
        String actualTitle = new Title(expectedTitle).toString();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void equals() {
        Title title = new Title("Valid Title");

        // same values -> returns true
        assertTrue(title.equals(new Title("Valid Title")));

        // same object -> returns true
        assertTrue(title.equals(title));

        // null -> returns false
        assertFalse(title.equals(null));

        // different types -> returns false
        assertFalse(title.equals(5.0f));

        // different values -> returns false
        assertFalse(title.equals(new Title("Other Valid Title")));
    }
}
