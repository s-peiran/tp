package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteContactFromMeetingCommand;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.MeetingBuilder;

class DeleteContactFromMeetingCommandParserTest {

    private DeleteContactFromMeetingCommandParser parser = new DeleteContactFromMeetingCommandParser();

    @Test
    void parse_invalidInputs_failure() {
        Meeting validMeeting = new MeetingBuilder().build();
        Contact validContact = new ContactBuilder().build();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteContactFromMeetingCommand.MESSAGE_USAGE);
        String userInput = DeleteContactFromMeetingCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + " "
                + validContact.getNameString() + CliSyntax.PREFIX_NOTE_MEETING + " " + validMeeting.getTitleString();

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
