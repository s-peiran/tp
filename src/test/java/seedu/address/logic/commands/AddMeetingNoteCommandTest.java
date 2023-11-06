package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
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
import seedu.address.model.note.Note;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddMeetingNoteCommand.
 */
public class AddMeetingNoteCommandTest {

    private static final String NOTE_STUB = "Some note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNoteUnfilteredList_success() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(firstMeeting).withNotes(NOTE_STUB).build();

        AddMeetingNoteCommand addMeetingNoteCommand = new AddMeetingNoteCommand(INDEX_FIRST, new Note(NOTE_STUB));

        String expectedMessage = String.format(AddMeetingNoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(firstMeeting, editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(addMeetingNoteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST);

        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(model.getFilteredMeetingList()
                .get(INDEX_FIRST.getZeroBased()))
                .withNotes(NOTE_STUB).build();

        AddMeetingNoteCommand addMeetingNoteCommand = new AddMeetingNoteCommand(INDEX_FIRST, new Note(NOTE_STUB));

        String expectedMessage = String.format(AddMeetingNoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(firstMeeting, editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(addMeetingNoteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        AddMeetingNoteCommand addMeetingNoteCommand = new AddMeetingNoteCommand(outOfBoundIndex,
                new Note(VALID_NOTE_BOB));

        assertCommandFailure(addMeetingNoteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
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

        AddMeetingNoteCommand addMeetingNoteCommand = new AddMeetingNoteCommand(outOfBoundIndex,
                new Note(VALID_NOTE_BOB));

        assertCommandFailure(addMeetingNoteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddMeetingNoteCommand standardCommand = new AddMeetingNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_AMY));

        // same values -> returns true
        AddMeetingNoteCommand commandWithSameValues = new AddMeetingNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddMeetingNoteCommand(INDEX_SECOND,
                new Note(VALID_NOTE_AMY))));

        // different note -> returns false
        assertFalse(standardCommand.equals(new AddMeetingNoteCommand(INDEX_FIRST,
                new Note(VALID_NOTE_BOB))));
    }
}
