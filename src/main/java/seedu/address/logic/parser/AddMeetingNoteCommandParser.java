package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingNoteCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new {@code AddMeetingNoteCommand} object
 */
public class AddMeetingNoteCommandParser implements Parser<AddMeetingNoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddMeetingNoteCommand}
     * and returns a {@code AddMeetingNoteCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMeetingNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_NOTE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INDEX, PREFIX_NOTE);

        Index index;
        try {
            index = Index.fromOneBased(Integer.parseInt(argMultimap.getValue(PREFIX_INDEX).get()));
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMeetingNoteCommand.MESSAGE_USAGE), e);
        }

        String note = argMultimap.getValue(PREFIX_NOTE).orElse("");

        if (note.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        return new AddMeetingNoteCommand(index, new Note(note));
    }
}
