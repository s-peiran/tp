package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a Meeting identified using its displaced index from the address book.
 */
public class DeleteMeetingCommand extends Command {
    public static final String COMMAND_WORD = "delete meeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "blahblah fill in later";
    public static final String MESSAGE_SUCCESS = "meeting deleted: %1$s";

    public DeleteMeetingCommand() {
        // constructor... take in id, meeting name or both
    }

    /**
     * Executes the Delete Meeting command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult representing the outcome of the command execution.
     * @throws CommandException if an error occurs during command execution.
     */
    public CommandResult execute(Model model) throws CommandException {
        // fill in execute
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        DeleteContactCommand otherDeleteContactCommand = (DeleteContactCommand) other;
        return true; // XXX.equals(otherDeleteContactCommand.XXX);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
