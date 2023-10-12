package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.model.meeting.Meeting;
import seedu.address.logic.commands.model.meeting.MeetingList;
import seedu.address.logic.commands.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.testutil.MeetingBuilder;

public class MeetingListTest {

    private MeetingList meetingList;

    @BeforeEach
    public void init() {
        meetingList = new MeetingList();
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        Meeting meeting = new MeetingBuilder().build();
        meetingList.add(meeting);
        assertThrows(DuplicateMeetingException.class, () -> meetingList.add(meeting));
    }

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        Meeting meeting = new MeetingBuilder().build();
        assertFalse(meetingList.contains(meeting));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        Meeting meeting = new MeetingBuilder().build();
        meetingList.add(meeting);
        assertTrue(meetingList.contains(meeting));
    }

    @Test
    public void toStringMethod() {
        assertEquals(meetingList.asUnmodifiableObservableList().toString(), meetingList.toString());
    }
}
