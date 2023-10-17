package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewContactCommand;

public class ViewContactCommandParserTest {

    private ViewContactCommandParser parser = new ViewContactCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
             ViewContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewMeetingCommand() {
        // no leading and trailing whitespaces
        ViewContactCommand expectedViewContactCommand = new ViewContactCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, " -id1", expectedViewContactCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -id \n 1 \n", expectedViewContactCommand);
    }
}
