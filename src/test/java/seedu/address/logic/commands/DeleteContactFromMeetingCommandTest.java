package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

class DeleteContactFromMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute() throws CommandException {
        Contact validContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Meeting validMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        ArrayList<Contact> listOfContacts = new ArrayList<>();
        listOfContacts.add(validContact);
        Meeting editedMeeting = new Meeting(
            validMeeting.getTitle(), validMeeting.getTime(), validMeeting.getPlace(),
            validMeeting.getDescription(), validMeeting.getNotes(), listOfContacts);
        model.setMeeting(validMeeting, editedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        DeleteContactFromMeetingCommand command = new DeleteContactFromMeetingCommand(editedMeeting.getTitle()
            .toString(), validContact.getName().toString());
        CommandResult expectedCommandResult = new CommandResult(String.format("Removed contact '%s' from Meeting '%s'",
            validContact.getName().toString(), editedMeeting.getTitle().toString()));
        CommandResult result = command.execute(model);
        assertEquals(expectedCommandResult, result);
    }
}
