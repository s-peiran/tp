package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ViewMeetingCommand;

public class ViewMeetingCommandParserTest {

    private ViewMeetingCommandParser parser = new ViewMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void parse_validArgs_returnsViewMeetingCommand() {
        // no leading and trailing whitespaces
        ViewMeetingCommand expectedViewMeetingCommand = new ViewMeetingCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, " 1", expectedViewMeetingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \n", expectedViewMeetingCommand);
    }
}
