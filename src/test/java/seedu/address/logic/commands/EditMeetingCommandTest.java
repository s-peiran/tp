package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHI;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CHI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CHI;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalMeetingsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {

    private Model model = new ModelManager(getTypicalMeetingsAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(editMeetingCommand, model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withTitle(VALID_TITLE_CHI).withTime(VALID_TIME_CHI).build();

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_CHI)
                .withTime(VALID_TIME_CHI).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(editMeetingCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST,
                new EditMeetingDescriptor());
        Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(editMeetingCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withTitle(VALID_TITLE_CHI).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST,
                new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_CHI).build());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS,
                Messages.formatMeeting(editedMeeting));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(editMeetingCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST);

        // edit ,eeting in filtered list into a duplicate in address book
        Meeting meetingInList = model.getAddressBook().getMeetingList().get(INDEX_SECOND.getZeroBased());
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST,
                new EditMeetingDescriptorBuilder(meetingInList).build());

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_CHI).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
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

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_CHI).build());

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST, DESC_ENG);

        // same values -> returns true
        EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(DESC_ENG);
        EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND, DESC_ENG)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_FIRST, DESC_CHI)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(index, editMeetingDescriptor);
        String expected = EditMeetingCommand.class.getCanonicalName() + "{index=" + index + ", editMeetingDescriptor="
                + editMeetingDescriptor + "}";
        assertEquals(expected, editMeetingCommand.toString());
    }

}
