package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

class AddContactToMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validInputs_success() throws CommandException {
        Contact validContact = model.getFilteredContactList().get(INDEX_FIRST.getZeroBased());
        Meeting validMeeting = model.getFilteredMeetingList().get(INDEX_FIRST.getZeroBased());
        AddContactToMeetingCommand command = new AddContactToMeetingCommand(validMeeting.getTitleString(),
            validContact.getNameString());
        CommandResult expectedCommandResult = new CommandResult(String.format("Added contact '%s' to Meeting '%s'",
            validContact.getNameString(), validMeeting.getTitleString()));
        CommandResult result = command.execute(model);
        assertEquals(expectedCommandResult, result);

    }
}
