package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalContactsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewContactCommand}.
 */
public class ViewContactCommandTest {
    private Model model = new ModelManager(getTypicalContactsAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToDisplay = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        ViewContactCommand viewContactCommand = new ViewContactCommand(INDEX_FIRST);
        String expectedMessage = String.format(ViewContactCommand.MESSAGE_VIEW_CONTACT_SUCCESS,
                Messages.format(contactToDisplay));

        assertCommandSuccess(viewContactCommand, model, expectedMessage, contactToDisplay.getNote().toString());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        ViewContactCommand viewContactCommand = new ViewContactCommand(outOfBoundIndex);

        assertCommandFailure(viewContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST);

        Contact contactToDisplay = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        ViewContactCommand viewContactCommand = new ViewContactCommand(INDEX_FIRST);
        String expectedMessage = String.format(ViewContactCommand.MESSAGE_VIEW_CONTACT_SUCCESS,
                Messages.format(contactToDisplay));

        assertCommandSuccess(viewContactCommand, model, expectedMessage, contactToDisplay.getNote().toString());
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        ViewContactCommand viewContactCommand = new ViewContactCommand(outOfBoundIndex);

        assertCommandFailure(viewContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewContactCommand viewContactFirstCommand = new ViewContactCommand(INDEX_FIRST);
        ViewContactCommand viewContactSecondCommand = new ViewContactCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(viewContactFirstCommand.equals(viewContactFirstCommand));

        // same values -> returns true
        ViewContactCommand viewContactFirstCommandCopy = new ViewContactCommand(INDEX_FIRST);
        assertTrue(viewContactFirstCommand.equals(viewContactFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewContactFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewContactFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(viewContactFirstCommand.equals(viewContactSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewContactCommand viewContactCommand = new ViewContactCommand(targetIndex);
        String expected = ViewContactCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewContactCommand.toString());
    }
}
