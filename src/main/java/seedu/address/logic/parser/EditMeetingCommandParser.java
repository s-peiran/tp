package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMeetingCommandParser object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingCommandParser
     * and returns an EditMeetingCommandParser object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;
        Index index;

        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split("\\s+");

        if (splitArgs.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE));
        }

        try {
            // Check if the index is zero or not a number
            int indexValue = Integer.parseInt(splitArgs[0]);
            if (indexValue <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE));
            }
            index = Index.fromOneBased(indexValue);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditMeetingCommand.MESSAGE_USAGE), e);
        }

        String argsWithoutIndex = " " + trimmedArgs.substring(splitArgs[0].length()).trim();
        argMultimap = ArgumentTokenizer.tokenize(argsWithoutIndex, PREFIX_TITLE, PREFIX_TIME,
                        PREFIX_PLACE, PREFIX_DESCRIPTION);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_TIME, PREFIX_PLACE, PREFIX_DESCRIPTION);
        if (!argMultimap.isAnyPrefixPresent(PREFIX_TITLE, PREFIX_TIME, PREFIX_PLACE, PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE));
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editMeetingDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editMeetingDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_PLACE).isPresent()) {
            editMeetingDescriptor.setPlace(ParserUtil.parsePlace(argMultimap.getValue(PREFIX_PLACE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editMeetingDescriptor.setDescription(
                ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }
}
