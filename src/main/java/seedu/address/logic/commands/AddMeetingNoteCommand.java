package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.note.Note;
import seedu.address.ui.AppState;

/**
 * Changes the note of an existing meeting in the address book.
 */
public class AddMeetingNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds note to the meeting identified "
            + "by the index number used in the last meeting listing. "
            + "Parameters: " + PREFIX_INDEX + " (must be a positive integer) "
            + PREFIX_NOTE + " [NOTE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
            + PREFIX_NOTE + " Likes to swim.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Meeting: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Meeting: %1$s";
    private static final Logger logger = LogsCenter.getLogger(AddMeetingNoteCommand.class);

    private final Index index;
    private final Note note;

    /**
     * @param index of the contact in the filtered contact list to edit the note
     * @param note  of the contact to be updated to
     */
    public AddMeetingNoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            logger.warning("The index is out of bounds: " + index.getZeroBased());
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());

        ArrayList<Note> mutableNotesList = new ArrayList<>(meetingToEdit.getNotes());

        if (mutableNotesList.contains(note)) {
            logger.warning("The note already exists: " + note);
            throw new CommandException(Messages.MESSAGE_DUPLICATE_NOTES);
        }

        mutableNotesList.add(note);

        Meeting editedMeeting = Meeting.editMeetingNotes(meetingToEdit, mutableNotesList);
        logger.fine("The meeting has been updated with a new list of notes: " + mutableNotesList);

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);

        AppState appState = AppState.getInstance();
        appState.setMeeting(editedMeeting);

        return new CommandResult(generateSuccessMessage(editedMeeting), false, false);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code meetingToEdit}.
     */
    private String generateSuccessMessage(Meeting meetingToEdit) {
        String message = !note.note.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, Messages.formatMeeting(meetingToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingNoteCommand)) {
            return false;
        }

        // state check
        AddMeetingNoteCommand e = (AddMeetingNoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
