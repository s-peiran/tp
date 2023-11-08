package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ViewContactCommand object
 */
public class ViewContactCommandParser implements Parser<ViewContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewContactCommand
     * and returns a ViewContactCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX), pe);
        }
    }
}
