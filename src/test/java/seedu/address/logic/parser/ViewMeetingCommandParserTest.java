package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewMeetingCommand;

public class ViewMeetingCommandParserTest {

    private ViewMeetingCommandParser parser = new ViewMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewMeetingCommand() {
        // no leading and trailing whitespaces
        ViewMeetingCommand expectedViewMeetingCommand = new ViewMeetingCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, " " + CliSyntax.PREFIX_INDEX + "1", expectedViewMeetingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + CliSyntax.PREFIX_INDEX + " \n 1 \n", expectedViewMeetingCommand);
    }
}
