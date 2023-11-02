package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteMeetingNoteCommand.
 */
public class DeleteMeetingNoteCommandTest {

    private static final String NOTE_STUB = "Some note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(firstMeeting).withNotes().build();

        DeleteMeetingNoteCommand deleteMeetingNoteCommand = new DeleteMeetingNoteCommand(INDEX_FIRST, VALID_MEETING_NOTEID);

        String expectedMessage = String.format(DeleteMeetingNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(firstMeeting, editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(deleteMeetingNoteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST);

        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(model.getFilteredMeetingList()
                .get(INDEX_FIRST.getZeroBased()))
                .withNotes().build();

        DeleteMeetingNoteCommand deleteMeetingNoteCommand = new DeleteMeetingNoteCommand(INDEX_FIRST, VALID_MEETING_NOTEID);

        String expectedMessage = String.format(DeleteMeetingNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(firstMeeting, editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(deleteMeetingNoteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        DeleteMeetingNoteCommand deleteMeetingNoteCommand = new DeleteMeetingNoteCommand(outOfBoundIndex, VALID_MEETING_NOTEID);

        assertCommandFailure(deleteMeetingNoteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMeetingList().size());

        DeleteMeetingNoteCommand deleteMeetingNoteCommand = new DeleteMeetingNoteCommand(outOfBoundIndex, VALID_MEETING_NOTEID);

        assertCommandFailure(deleteMeetingNoteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteMeetingNoteCommand standardCommand = new DeleteMeetingNoteCommand(INDEX_FIRST,
                VALID_MEETING_NOTEID);

        // same values -> returns true
        DeleteMeetingNoteCommand commandWithSameValues = new DeleteMeetingNoteCommand(INDEX_FIRST,
                VALID_MEETING_NOTEID);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteMeetingNoteCommand(INDEX_SECOND,
                VALID_MEETING_NOTEID)));

        // same noteID -> returns true
        assertTrue(standardCommand.equals(new DeleteMeetingNoteCommand(INDEX_FIRST,
                VALID_MEETING_NOTEID)));
    }
}
