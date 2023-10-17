package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Shows a Contact identified using its id from the address book.
 */
public class ViewContactCommand extends Command {

    public static final String COMMAND_WORD = "view contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the details of the contact identified by its id in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -id1";

    public static final String MESSAGE_VIEW_CONTACT_SUCCESS = "Showing Contact Note: %1$s";

    private final Index targetIndex;

    public ViewContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToDisplay = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_VIEW_CONTACT_SUCCESS, Messages.format(contactToDisplay)),
                contactToDisplay.getNotes().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewContactCommand)) {
            return false;
        }

        ViewContactCommand otherViewContactCommand = (ViewContactCommand) other;
        return targetIndex.equals(otherViewContactCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
        .add("targetIndex", targetIndex)
        .toString();
    }
}
