package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.MeetingBuilder;

class AddContactToMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Contact validContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
    private final Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());

    @Test
    void execute_validInputs_success() {
        AddContactToMeetingCommand addContactToMeetingCommand = new AddContactToMeetingCommand(
            firstMeeting.getTitleString(), validContact.getNameString());
        Meeting editedMeeting = new MeetingBuilder(firstMeeting).withContacts(validContact).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMeeting(firstMeeting, editedMeeting);
        CommandResult expectedCommandResult = new CommandResult(String.format("Added contact '%s' to Meeting '%s'",
            validContact.getNameString(), firstMeeting.getTitleString()), false, false);
        assertCommandSuccess(addContactToMeetingCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateContact_failure() {
        Meeting editedMeeting = new MeetingBuilder(firstMeeting).withContacts(validContact).build();
        AddContactToMeetingCommand addContactToMeetingCommand = new AddContactToMeetingCommand(
            editedMeeting.getTitleString(), validContact.getNameString());
        model.setMeeting(firstMeeting, editedMeeting);
        assertCommandFailure(addContactToMeetingCommand, model, AddContactToMeetingCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContact_failure() {
        Contact invalidContact = new ContactBuilder().build();
        AddContactToMeetingCommand addContactToMeetingCommand = new AddContactToMeetingCommand(
            firstMeeting.getTitleString(), invalidContact.getNameString());
        assertCommandFailure(addContactToMeetingCommand, model, AddContactToMeetingCommand.MESSAGE_CONTACT_NOT_FOUND);
    }

    @Test
    public void equals() {
        Meeting altMeeting = new MeetingBuilder().build();
        Contact altContact = new ContactBuilder().build();

        final AddContactToMeetingCommand command = new AddContactToMeetingCommand(firstMeeting.getTitleString(),
            validContact.getNameString());

        final AddContactToMeetingCommand commandCopy = new AddContactToMeetingCommand(firstMeeting.getTitleString(),
            validContact.getNameString());

        final AddContactToMeetingCommand commandDiffContact = new AddContactToMeetingCommand(
            firstMeeting.getTitleString(), altContact.getNameString());

        final AddContactToMeetingCommand commandDiffMeeting = new AddContactToMeetingCommand(
            altMeeting.getTitleString(), validContact.getNameString());

        assertTrue(command.equals(commandCopy));

        // same object -> returns true
        assertTrue(command.equals(command));

        // null -> returns false
        assertFalse(command.equals(null));

        // different types -> returns false
        assertFalse(command.equals(new ClearCommand()));

        // different Contact -> returns false
        assertFalse(command.equals(commandDiffContact));

        // different Meeting -> returns false
        assertFalse(command.equals(commandDiffMeeting));
    }
}
