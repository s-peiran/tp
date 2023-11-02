package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactFilterPredicate;

/**
 * Lists all contacts in the address book to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all contacts.";

    private final ContactFilterPredicate predicate;

    public ListContactCommand(ContactFilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);

        return new CommandResult(String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
                model.getFilteredContactList().size()));
    }
}
