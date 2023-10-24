package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Add a meeting to Notenote.
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "add meeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "blahblah fill in later";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatMeeting(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand otherAddCommand = (AddMeetingCommand) other;
        boolean titleEquals = toAdd.getTitle().equals(otherAddCommand.toAdd.getTitle());
        boolean timeEquals = toAdd.getTime().equals(otherAddCommand.toAdd.getTime());
        boolean placeEquals = toAdd.getPlace().equals(otherAddCommand.toAdd.getPlace());
        boolean descriptionEquals = toAdd.getDescription().equals(otherAddCommand.toAdd.getDescription());

        return titleEquals && timeEquals && placeEquals && descriptionEquals;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
