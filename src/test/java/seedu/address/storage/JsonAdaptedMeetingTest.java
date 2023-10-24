package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.CS2103;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Place;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Title;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_TITLE = "@ssignment Discussion";
    private static final String INVALID_TIME = "1/2//2023 18;00";
    private static final String INVALID_PLACE = "$chool";

    private static final String VALID_TITLE = CS2103.getTitle().toString();
    private static final String VALID_TIME = CS2103.getTime().toString();
    private static final String VALID_PLACE = CS2103.getPlace().toString();
    private static final String VALID_DESCRIPTION = CS2103.getDescription().toString();
    private static final List<JsonAdaptedNote> VALID_NOTES = CS2103.getNotes().stream()
            .map(JsonAdaptedNote::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(CS2103);
        assertEquals(CS2103, meeting.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(INVALID_TITLE, VALID_TIME, VALID_PLACE, VALID_DESCRIPTION, VALID_NOTES);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(null, VALID_TIME, VALID_PLACE, VALID_DESCRIPTION,
                VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, INVALID_TIME, VALID_PLACE, VALID_DESCRIPTION, VALID_NOTES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, null, VALID_PLACE, VALID_DESCRIPTION, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_invalidPlace_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_TIME, INVALID_PLACE, VALID_DESCRIPTION, VALID_NOTES);
        String expectedMessage = Place.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullPlace_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_TIME, null, VALID_DESCRIPTION, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Place.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(VALID_TITLE, VALID_TIME, VALID_PLACE, null, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, meeting::toModelType);
    }

}
