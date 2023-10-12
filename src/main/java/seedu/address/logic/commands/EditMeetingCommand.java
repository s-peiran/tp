package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand {
    public static final String COMMAND_WORD = "edit meeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "blahblah fill in later";
    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";

    public EditMeetingCommand() {
        // constructor...
    }

    /**
     * Execute method
     *
     * @param model
     * @return CommandResult
     * @throws CommandException
     */
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        EditContactCommand otherEditContactCommand = (EditContactCommand) other;
        return true; // to be replaced
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
