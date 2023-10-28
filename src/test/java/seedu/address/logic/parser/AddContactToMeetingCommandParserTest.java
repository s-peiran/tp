package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddContactToMeetingCommand;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.MeetingBuilder;

class AddContactToMeetingCommandParserTest {

    private AddContactToMeetingCommandParser parser = new AddContactToMeetingCommandParser();


    @Test
    void parse_validInputs_success() {
        Meeting validMeeting = new MeetingBuilder().build();
        Contact validContact = new ContactBuilder().build();
        String userInput = AddContactToMeetingCommand.COMMAND_WORD + " " + PREFIX_NAME
                + " " + validContact.getName() + " " + PREFIX_TITLE + " " + validMeeting.getTitle();
        AddContactToMeetingCommand expectedCommand = new AddContactToMeetingCommand(validMeeting.getTitleString(),
                validContact.getNameString());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_invalidInputs_failure() {
        Meeting validMeeting = new MeetingBuilder().build();
        Contact validContact = new ContactBuilder().build();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddContactToMeetingCommand.MESSAGE_USAGE);
        String userInput = String.format("add contact -n %s -m %s", validContact.getNameString(),
                validMeeting.getTitleString());
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
