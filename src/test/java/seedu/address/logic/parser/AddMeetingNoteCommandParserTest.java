package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingNoteCommand;

class AddMeetingNoteCommandParserTest {

    private AddMeetingNoteCommandParser parser = new AddMeetingNoteCommandParser();

    @Test
    void parse_invalidInputs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingNoteCommand.MESSAGE_USAGE);
        String userInput = "add note -title j";
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
