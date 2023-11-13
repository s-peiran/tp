package seedu.address.logic.commands;

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
import seedu.address.testutil.MeetingBuilder;

class DeleteContactFromMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validInputs_success() {
        Contact validContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(firstMeeting).withContacts(validContact).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setMeeting(firstMeeting, editedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        DeleteContactFromMeetingCommand deleteContactFromMeetingCommand = new DeleteContactFromMeetingCommand(
            editedMeeting.getTitle().toString(), validContact.getNameString());
        CommandResult expectedCommandResult = new CommandResult(String.format("Removed contact '%s' from Meeting '%s'",
            validContact.getNameString(), editedMeeting.getTitleString()), false, false);
        assertCommandSuccess(deleteContactFromMeetingCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_contactNotInMeeting_failure() {
        Contact validContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Meeting validMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        DeleteContactFromMeetingCommand deleteContactFromMeetingCommand = new DeleteContactFromMeetingCommand(
            validMeeting.getTitle().toString(), validContact.getNameString());
        assertCommandFailure(deleteContactFromMeetingCommand, model,
            DeleteContactFromMeetingCommand.MESSAGE_CONTACT_NOT_FOUND);
    }

    @Test
    public void execute_invalidMeeting_failure() {
        Contact validContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Meeting invalidMetting = new MeetingBuilder().build();
        DeleteContactFromMeetingCommand deleteContactFromMeetingCommand = new DeleteContactFromMeetingCommand(
            invalidMetting.getTitle().toString(), validContact.getNameString());
        assertCommandFailure(deleteContactFromMeetingCommand, model,
            DeleteContactFromMeetingCommand.MESSAGE_MEETING_NOT_FOUND);
    }
}
