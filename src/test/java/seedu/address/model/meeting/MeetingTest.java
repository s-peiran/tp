package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void equals() {
        Meeting m1 = new MeetingBuilder().build();
        Meeting m2 = new MeetingBuilder().withTitle("test title").build();

        // same object -> returns true
        assertTrue(m1.equals(m1));

        // null -> returns false
        assertFalse(m1.equals(null));

        // different type -> returns false
        assertFalse(m1.equals(5));

        // different meeting -> returns false
        assertFalse(m1.equals(m2));
    }

    @Test
    public void toStringMethod() {
        Meeting meeting = new MeetingBuilder().build();

        String expected = Meeting.class.getCanonicalName() + "{title=" + MeetingBuilder.DEFAULT_TITLE
                + ", time=" + MeetingBuilder.DEFAULT_TIME + ", place=" + MeetingBuilder.DEFAULT_PLACE
                + ", description=" + MeetingBuilder.DEFAULT_DESCRIPTION + "}";

        assertEquals(expected, meeting.toString());
    }
}
