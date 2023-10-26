package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult.ListType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Model.ModeType;

/**
 * Toggles the mode of Notenote between contacts and meetings mode.
 */
public class ModeCommand extends Command {

    public static final String COMMAND_WORD = "mode";

    public static final String MESSAGE_SUCCESS = "Application mode set: %1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.changeMode();
        ModeType mode = model.getMode();

        if (mode == ModeType.CONTACTS) {
            model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, mode.toString()), ListType.CONTACTS);
        } else {
            model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, mode.toString()), ListType.MEETINGS);
        }
    }
}
