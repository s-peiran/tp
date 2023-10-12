package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

class MessagesTest {

    @Test
    public void execute_formatMeetingNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Messages.formatMeeting(null));
    }

    @Test
    public void execute_formatMeetingAllFieldsPresent_success() {
        Meeting meeting = new MeetingBuilder().build();
        String expectedMessage = MeetingBuilder.DEFAULT_TITLE
                + "; Time: " + MeetingBuilder.DEFAULT_TIME
                + "; Location: " + MeetingBuilder.DEFAULT_LOCATION
                + "; Description: " + MeetingBuilder.DEFAULT_DESCRIPTION;

        String actualMessage = Messages.formatMeeting(meeting);

        assertEquals(expectedMessage, actualMessage);
    }
}
