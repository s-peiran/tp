package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.PLACE_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ENG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
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
        String userInput = " " + PREFIX_TITLE + MeetingBuilder.DEFAULT_TITLE
                + " " + PREFIX_TIME + MeetingBuilder.DEFAULT_TIME
                + " " + PREFIX_PLACE + MeetingBuilder.DEFAULT_PLACE
                + " " + PREFIX_DESCRIPTION + MeetingBuilder.DEFAULT_DESCRIPTION;

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing time prefix
        assertParseFailure(parser, TITLE_DESC_ENG + PLACE_DESC_ENG + DESCRIPTION_DESC_ENG, expectedMessage);

        // missing place prefix
        assertParseFailure(parser, TITLE_DESC_ENG + TIME_DESC_ENG + DESCRIPTION_DESC_ENG, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, TITLE_DESC_ENG + TIME_DESC_ENG + PLACE_DESC_ENG, expectedMessage);
    }

    @Test
    public void parse_repeatedFields_failure() {
        String userInput = " " + PREFIX_TITLE + MeetingBuilder.DEFAULT_TITLE
                + " " + PREFIX_TIME + MeetingBuilder.DEFAULT_TIME
                + " " + PREFIX_PLACE + MeetingBuilder.DEFAULT_PLACE
                + " " + PREFIX_DESCRIPTION + MeetingBuilder.DEFAULT_DESCRIPTION;

        String duplicateTitle = userInput + " " + PREFIX_TITLE + MeetingBuilder.DEFAULT_TITLE;
        String duplicateTime = userInput + " " + PREFIX_TIME + MeetingBuilder.DEFAULT_TIME;
        String duplicatePlace = userInput + " " + PREFIX_PLACE + MeetingBuilder.DEFAULT_PLACE;
        String duplicateDescription = userInput + " " + PREFIX_DESCRIPTION + MeetingBuilder.DEFAULT_DESCRIPTION;

        assertParseFailure(parser, duplicateTitle, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE));
        assertParseFailure(parser, duplicateTime, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));
        assertParseFailure(parser, duplicatePlace, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PLACE));
        assertParseFailure(parser, duplicateDescription,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));
    }
}
