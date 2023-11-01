package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.ui.AppState;

/**
 * Shows a Meeting identified using its id from the address book.
 */
public class ViewMeetingCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the details of the meeting identified by its id in the displayed meeting list.\n"
            + "Parameters: " + PREFIX_INDEX + " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -id 1";

    public static final String MESSAGE_VIEW_MEETING_SUCCESS = "Showing Meeting: %1$s";

    private final Index targetIndex;

    public ViewMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToDisplay = lastShownList.get(targetIndex.getZeroBased());

        AppState appState = AppState.getInstance();
        appState.setMeeting(meetingToDisplay);

        //todo: change display to note when it is implemented
        return new CommandResult(String.format(MESSAGE_VIEW_MEETING_SUCCESS,
                Messages.formatMeeting(meetingToDisplay)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewMeetingCommand)) {
            return false;
        }

        ViewMeetingCommand otherViewMeetingCommand = (ViewMeetingCommand) other;
        return targetIndex.equals(otherViewMeetingCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
