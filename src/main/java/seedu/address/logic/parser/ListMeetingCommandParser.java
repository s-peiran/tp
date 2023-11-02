package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingFilterPredicate;

/**
 * Parses input arguments and creates a new ListMeetingCommand object
 */
public class ListMeetingCommandParser implements Parser<ListMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListMeetingCommand
     * and returns an ListMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TIME_START, PREFIX_TIME_END, PREFIX_PLACE,
                PREFIX_DESCRIPTION, PREFIX_NOTE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_TIME_START, PREFIX_TIME_END, PREFIX_PLACE,
                PREFIX_DESCRIPTION, PREFIX_NOTE);

        String title = argMultimap.getValue(PREFIX_TITLE).orElse("").trim();
        List<String> titleKeywords = Arrays.asList(title.split("\\s+"));
        String timeStart = argMultimap.getValue(PREFIX_TIME_START).orElse("");
        if (!timeStart.equals("")) { // check isValidTime
            ParserUtil.parseTime(timeStart);
        }
        String timeEnd = argMultimap.getValue(PREFIX_TIME_END).orElse("");
        if (!timeEnd.equals("")) { // check isValidTime
            ParserUtil.parseTime(timeStart);
        }
        String place = argMultimap.getValue(PREFIX_PLACE).orElse("").trim();
        List<String> placeKeywords = Arrays.asList(place.split("\\s+"));
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("").trim();
        List<String> descriptionKeywords = Arrays.asList(description.split("\\s+"));
        String note = argMultimap.getValue(PREFIX_NOTE).orElse("");
        List<String> noteKeywords = Arrays.asList(note.split("\\s+"));
        return new ListMeetingCommand(new MeetingFilterPredicate(titleKeywords,
            List.of(timeStart, timeEnd), placeKeywords, descriptionKeywords, noteKeywords));
    }
}
