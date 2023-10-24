package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalMeetingsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewMeetingCommand}.
 */
public class ViewMeetingCommandTest {
    private Model model = new ModelManager(getTypicalMeetingsAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToDisplay = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(INDEX_FIRST);
        String expectedMessage = String.format(ViewMeetingCommand.MESSAGE_VIEW_MEETING_SUCCESS,
                Messages.formatMeeting(meetingToDisplay));
        String expectedNote = meetingToDisplay.getNotes().toString().replace("[", "").replace("]", "");

        assertCommandSuccess(viewMeetingCommand, model, expectedMessage, expectedNote);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(outOfBoundIndex);

        assertCommandFailure(viewMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST);

        Meeting meetingToDisplay = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(INDEX_FIRST);
        String expectedMessage = String.format(ViewMeetingCommand.MESSAGE_VIEW_MEETING_SUCCESS,
                Messages.formatMeeting(meetingToDisplay));
        String expectedNote = meetingToDisplay.getNotes().toString().replace("[", "").replace("]", "");

        assertCommandSuccess(viewMeetingCommand, model, expectedMessage, expectedNote);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(outOfBoundIndex);

        assertCommandFailure(viewMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewMeetingCommand viewMeetingFirstCommand = new ViewMeetingCommand(INDEX_FIRST);
        ViewMeetingCommand viewMeetingSecondCommand = new ViewMeetingCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(viewMeetingFirstCommand.equals(viewMeetingFirstCommand));

        // same values -> returns true
        ViewMeetingCommand viewMeetingFirstCommandCopy = new ViewMeetingCommand(INDEX_FIRST);
        assertTrue(viewMeetingFirstCommand.equals(viewMeetingFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewMeetingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewMeetingFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(viewMeetingFirstCommand.equals(viewMeetingSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(targetIndex);
        String expected = ViewMeetingCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewMeetingCommand.toString());
    }
}
