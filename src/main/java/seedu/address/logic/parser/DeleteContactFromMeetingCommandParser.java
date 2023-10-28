package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.DeleteContactFromMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteContactFromMeetingCommandParser object
 */
public class DeleteContactFromMeetingCommandParser implements Parser<DeleteContactFromMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteContactFromMeetingCommand}
     * and returns a {@code DeleteContactFromMeetingCommand} object for execution.
     *
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform to
     *                                                              the expected format
     */
    public DeleteContactFromMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TITLE);

        String contactName = argMultimap.getValue(PREFIX_NAME).orElse("");
        String meetingTitle = argMultimap.getValue(PREFIX_TITLE).orElse("");
        boolean raiseException = contactName.length() < 1 || meetingTitle.length() < 1;
        if (raiseException) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteContactFromMeetingCommand.MESSAGE_USAGE));
        }
        return new DeleteContactFromMeetingCommand(meetingTitle, contactName);
    }
}
