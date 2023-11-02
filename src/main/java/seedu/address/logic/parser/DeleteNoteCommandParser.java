package seedu.address.logic.parser;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_ID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteMeetingNoteCommand} object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteMeetingNoteCommand}
     * and returns a {@code AddMeetingNoteCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_NOTE_ID);

        Index index;
        int noteID;
        try {
            index = Index.fromOneBased(parseInt(argMultimap.getValue(PREFIX_INDEX).get()));
            noteID = parseInt(argMultimap.getValue(PREFIX_NOTE_ID).orElse(""));
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteNoteCommand.MESSAGE_USAGE), e);
        }

        return new DeleteNoteCommand(index, noteID);
    }
}
