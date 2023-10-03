package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandParserTest {

    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting validMeeting = new MeetingBuilder().build();
        AddMeetingCommand expectedCommand = new AddMeetingCommand(validMeeting);
        String userInput = " title/" + MeetingBuilder.DEFAULT_TITLE
                + " time/" + MeetingBuilder.DEFAULT_TIME
                + " location/" + MeetingBuilder.DEFAULT_LOCATION
                + " desc/" + MeetingBuilder.DEFAULT_DESCRIPTION;

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_titleMissing_failure() {
        String userInput = " time/" + MeetingBuilder.DEFAULT_TIME
                + " location/" + MeetingBuilder.DEFAULT_LOCATION
                + " desc/" + MeetingBuilder.DEFAULT_DESCRIPTION;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_repeatedFields_failure() {
        String userInput = " title/" + MeetingBuilder.DEFAULT_TITLE
                + " time/" + MeetingBuilder.DEFAULT_TIME
                + " location/" + MeetingBuilder.DEFAULT_LOCATION
                + " desc/" + MeetingBuilder.DEFAULT_DESCRIPTION;

        String duplicateTitle = userInput + " title/" + MeetingBuilder.DEFAULT_TITLE;
        String duplicateTime = userInput + " time/" + MeetingBuilder.DEFAULT_TIME;
        String duplicateLocation = userInput + " location/" + MeetingBuilder.DEFAULT_LOCATION;
        String duplicateDescription = userInput + " desc/" + MeetingBuilder.DEFAULT_DESCRIPTION;

        assertParseFailure(parser, duplicateTitle, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE));
        assertParseFailure(parser, duplicateTime, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));
        assertParseFailure(parser, duplicateLocation, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));
        assertParseFailure(parser, duplicateDescription,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }
}
