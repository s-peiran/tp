package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactFilterPredicate;


/**
 * Parses input arguments and creates a new ListMeetingCommand object
 */
public class ListContactCommandParser implements Parser<ListContactCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListContactCommand
     * and returns an ListContactCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_NOTE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_NOTE);

        String name = argMultimap.getValue(PREFIX_NAME).orElse("").trim();
        List<String> nameKeywords = Arrays.asList(name.split("\\s+"));
        String phone = argMultimap.getValue(PREFIX_PHONE).orElse("");
        String email = argMultimap.getValue(PREFIX_EMAIL).orElse("").trim();
        String address = argMultimap.getValue(PREFIX_ADDRESS).orElse("").trim();
        List<String> addressKeywords = Arrays.asList(address.split("\\s+"));
        String tag = argMultimap.getValue(PREFIX_TAG).orElse("").trim();
        List<String> tagKeywords = Arrays.asList(tag.split("\\s+"));
        String note = argMultimap.getValue(PREFIX_NOTE).orElse("");
        List<String> noteKeywords = Arrays.asList(note.split("\\s+"));
        return new ListContactCommand(new ContactFilterPredicate(nameKeywords,
            phone, email, addressKeywords, tagKeywords, noteKeywords));
    }
}

