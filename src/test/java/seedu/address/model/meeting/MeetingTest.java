package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {

    @Test
    public void equals() {
        Meeting m1 = new MeetingBuilder().build();
        Meeting m2 = new MeetingBuilder().build();
        Meeting m3 = new MeetingBuilder().withTitle("test title").build();

        // same values -> returns false because id is different
        assertFalse(m1.equals(m2));

        // same object -> returns true
        assertTrue(m1.equals(m1));

        // null -> returns false
        assertFalse(m1.equals(null));

        // different type -> returns false
        assertFalse(m1.equals(5));

        // different meeting -> returns false
        assertFalse(m1.equals(m3));
    }

    @Test
    public void toStringMethod() {
        Meeting meeting = new MeetingBuilder().build();
        //Meeting is a singleton. It could be more than 0 here as we create multiple meeting objects above.
        int id = meeting.getId();
        String expected = Meeting.class.getCanonicalName() + "{id=" + id + ", title=" + MeetingBuilder.DEFAULT_TITLE
                + ", time=" + MeetingBuilder.DEFAULT_TIME + ", location=" + MeetingBuilder.DEFAULT_LOCATION
                + ", description=" + MeetingBuilder.DEFAULT_DESCRIPTION + "}";

        assertEquals(expected, meeting.toString());
    }
}
