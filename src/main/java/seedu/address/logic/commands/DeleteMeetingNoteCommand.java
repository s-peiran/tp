package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_MEETING;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.note.Note;
import seedu.address.ui.AppState;

/**
 * Deletes a note of an existing meeting in the address book.
 */
public class DeleteMeetingNoteCommand extends Command {

    public static final String COMMAND_WORD = "delete note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the note of the meeting identified "
            + "by the index number used in the last meeting listing. "
            + "Parameters: " + PREFIX_INDEX + " (must be a positive integer) "
            + PREFIX_NOTE_MEETING + " [NOTE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
            + PREFIX_NOTE_ID + " 1.";

    public static final String MESSAGE_DELETE_NOTE_FAILURE = "Failed to remove note from Meeting: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Meeting: %1$s";

    private final Index index;
    private final int noteID;
    private boolean isSuccessful = false;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param noteID of the note to be deleted
     */
    public DeleteMeetingNoteCommand(Index index, int noteID) {
        requireAllNonNull(index, noteID);

        this.index = index;
        this.noteID = noteID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());

        Set<Note> mutableNotesList = new LinkedHashSet<>(meetingToEdit.getNotes());
        Iterator<Note> iterator = mutableNotesList.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getNoteID() == noteID) {
                iterator.remove();
                isSuccessful = true;
            }
        }

        LinkedHashSet<Note> newNoteSet = new LinkedHashSet<>();
        iterator = mutableNotesList.iterator();
        while (iterator.hasNext()) {
            newNoteSet.add(iterator.next());
        }

        Meeting editedMeeting = new Meeting(
                meetingToEdit.getTitle(), meetingToEdit.getTime(), meetingToEdit.getPlace(),
                meetingToEdit.getDescription(), newNoteSet, meetingToEdit.getContacts());

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
        String message = isSuccessful ? MESSAGE_DELETE_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_FAILURE;
        return String.format(message, meetingToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMeetingNoteCommand)) {
            return false;
        }

        // state check
        DeleteMeetingNoteCommand e = (DeleteMeetingNoteCommand) other;
        return index.equals(e.index)
                && noteID == e.noteID;
    }
}
