package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.meeting.MeetingFilterPredicate;

/**
 * Lists all meetings in the address book to the user.
 */
public class ListMeetingCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all meetings.";

    private final MeetingFilterPredicate predicate;

    public ListMeetingCommand(MeetingFilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW,
            model.getFilteredMeetingList().size()));
    }
}
