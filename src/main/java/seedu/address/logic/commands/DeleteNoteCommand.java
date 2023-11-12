package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_NOTEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_ID;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.note.Note;
import seedu.address.ui.AppState;

/**
 * Deletes a note of an existing Contact in the address book.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the note of the meeting identified "
        + "by the index number used in the last meeting listing. "
        + "Parameters: " + PREFIX_INDEX + " (must be a positive integer) "
        + PREFIX_NOTE + " [NOTE]\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
        + PREFIX_NOTE_ID + " 1.";


    public static final String MESSAGE_DELETE_NOTE_FAILURE = "Failed to remove note from Contact: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Contact: %1$s";

    private final Index index;
    private final int noteID;
    private boolean isSuccessful = false;

    /**
     * @param index  of the person in the filtered person list to edit the note
     * @param noteID of the note to be deleted
     */
    public DeleteNoteCommand(Index index, int noteID) {
        requireAllNonNull(index, noteID);

        this.index = index;
        this.noteID = noteID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());

        ArrayList<Note> mutableNotesList = new ArrayList<>(contactToEdit.getNotes());

        if (noteID > mutableNotesList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_NOTEID));
        }

        mutableNotesList.remove(noteID - 1);
        isSuccessful = true;

        Contact editedContact = new Contact(
            contactToEdit.getName(), contactToEdit.getPhone(), contactToEdit.getEmail(),
            mutableNotesList, contactToEdit.getObservers());

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);

        AppState appState = AppState.getInstance();
        appState.setContact(editedContact);

        return new CommandResult(generateSuccessMessage(editedContact), false, false);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code meetingToEdit}.
     */
    private String generateSuccessMessage(Contact contactToEdit) {
        String message = isSuccessful ? MESSAGE_DELETE_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_FAILURE;
        return String.format(message, Messages.formatContact(contactToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteNoteCommand)) {
            return false;
        }

        // state check
        DeleteNoteCommand e = (DeleteNoteCommand) other;
        return index.equals(e.index)
            && noteID == e.noteID;
    }
}
