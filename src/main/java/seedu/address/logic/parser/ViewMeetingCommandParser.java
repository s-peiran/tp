package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ViewMeetingCommand object
 */
public class ViewMeetingCommandParser implements Parser<ViewMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewMeetingCommand
     * and returns a ViewMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX);
        Index index;
        try {
            if (Integer.parseInt(argMultimap.getValue(PREFIX_INDEX).get()) == 0) {
                throw new IndexOutOfBoundsException();
            }
            index = Index.fromOneBased(Integer.parseInt(argMultimap.getValue(PREFIX_INDEX).get()));
        } catch (NoSuchElementException e) {
            throw new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewMeetingCommand.MESSAGE_USAGE), e);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_MEETING_DISPLAYED_INDEX));
        }
        return new ViewMeetingCommand(index);
    }
}
