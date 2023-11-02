package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_NOTEID;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteMeetingNoteCommand.
 */
public class DeleteNoteCommandTest {

    private static final String NOTE_STUB = "Some note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Contact firstContact = model.getFilteredContactList().get(INDEX_THIRD.getZeroBased());
        Contact editedContact = new ContactBuilder(firstContact).withNotes().build();

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_THIRD, VALID_CONTACT_NOTEID);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(firstContact, editedContact);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(deleteNoteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST);

        Contact firstContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Contact editedContact = new ContactBuilder(model.getFilteredContactList()
                .get(INDEX_FIRST.getZeroBased()))
                .withNotes().build();

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST, VALID_CONTACT_NOTEID);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_FAILURE, editedContact);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(firstContact, editedContact);

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false);

        assertCommandSuccess(deleteNoteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex, VALID_CONTACT_NOTEID);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidContactIndexFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfBoundIndex, VALID_CONTACT_NOTEID);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteNoteCommand standardCommand = new DeleteNoteCommand(INDEX_FIRST,
                VALID_CONTACT_NOTEID);

        // same values -> returns true
        DeleteNoteCommand commandWithSameValues = new DeleteNoteCommand(INDEX_FIRST,
                VALID_CONTACT_NOTEID);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteNoteCommand(INDEX_SECOND,
                VALID_CONTACT_NOTEID)));

        // same noteID -> returns true
        assertTrue(standardCommand.equals(new DeleteNoteCommand(INDEX_FIRST,
                VALID_CONTACT_NOTEID)));
    }
}
