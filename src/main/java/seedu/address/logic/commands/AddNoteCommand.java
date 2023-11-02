package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTACT;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.note.Note;
import seedu.address.ui.AppState;

/**
 * Changes the note of an existing contact in the address book.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the note of the person identified "
            + "by the index number used in the last contact listing. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: " + PREFIX_INDEX + " (must be a positive integer) "
            + PREFIX_NOTE_CONTACT + " [NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE_CONTACT + " Likes to swim.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: %1$s";

    private final Index index;
    private final Note note;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param note  of the person to be updated to
     */
    public AddNoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());

        Set<Note> mutableNotesList = new LinkedHashSet<>(contactToEdit.getNotes());

        if (mutableNotesList.contains(note)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_NOTES);
        }

        mutableNotesList.add(note);

        Contact editedContact = new Contact(
                contactToEdit.getName(), contactToEdit.getPhone(), contactToEdit.getEmail(),
                contactToEdit.getAddress(), contactToEdit.getTags(), mutableNotesList);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);

        AppState appState = AppState.getInstance();
        appState.setContact(editedContact);

        return new CommandResult(generateSuccessMessage(editedContact), false, false);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code contactToEdit}.
     */
    private String generateSuccessMessage(Contact contactToEdit) {
        String message = !note.note.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, contactToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddNoteCommand)) {
            return false;
        }

        // state check
        AddNoteCommand e = (AddNoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
