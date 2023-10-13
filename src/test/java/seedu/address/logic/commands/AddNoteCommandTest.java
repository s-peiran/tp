package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Note;
import seedu.address.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class AddNoteCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Contact firstPerson = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedPerson = new ContactBuilder(firstPerson).withNote(REMARK_STUB).build();

        AddNoteCommand remarkCommand = new AddNoteCommand(INDEX_FIRST_CONTACT, new Note(editedPerson.getNote().value));

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Contact firstPerson = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedPerson = new ContactBuilder(firstPerson).withNote("").build();

        AddNoteCommand remarkCommand = new AddNoteCommand(INDEX_FIRST_CONTACT,
                new Note(editedPerson.getNote().toString()));

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact firstPerson = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedPerson = new ContactBuilder(model.getFilteredContactList()
                .get(INDEX_FIRST_CONTACT.getZeroBased()))
                .withNote(REMARK_STUB).build();

        AddNoteCommand remarkCommand = new AddNoteCommand(INDEX_FIRST_CONTACT, new Note(editedPerson.getNote().value));

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        AddNoteCommand remarkCommand = new AddNoteCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

        AddNoteCommand remarkCommand = new AddNoteCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddNoteCommand standardCommand = new AddNoteCommand(INDEX_FIRST_CONTACT,
                new Note(VALID_NOTE_AMY));

        // same values -> returns true
        AddNoteCommand commandWithSameValues = new AddNoteCommand(INDEX_FIRST_CONTACT,
                new Note(VALID_NOTE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(INDEX_SECOND_CONTACT,
                new Note(VALID_NOTE_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(INDEX_FIRST_CONTACT,
                new Note(VALID_NOTE_BOB))));
    }
}
