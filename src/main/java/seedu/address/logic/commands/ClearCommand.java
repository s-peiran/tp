package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.AppState;
import seedu.address.ui.AppState.ModeType;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    private static final Logger logger = LogsCenter.getLogger(ClearCommand.class);


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        logger.fine("Address book has been cleared.");

        AppState appState = AppState.getInstance();
        ModeType mode = appState.getModeType();

        if (mode == ModeType.CONTACTS) {
            appState.setContact(null);
        } else if (mode == ModeType.MEETINGS) {
            appState.setMeeting(null);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
