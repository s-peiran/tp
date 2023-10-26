package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_MEETING;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.note.Note;

/**
 * Changes the note of an existing meeting in the address book.
 */
public class AddMeetingNoteCommand extends Command {

    public static final String COMMAND_WORD = "add note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the note of the meeting identified "
            + "by the index number used in the last meeting listing. "
            + "Parameters: " + PREFIX_INDEX + " (must be a positive integer) "
            + PREFIX_NOTE_MEETING + " [NOTE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
            + PREFIX_NOTE_MEETING + " Likes to swim.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Meeting: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Meeting: %1$s";

    private final Index index;
    private final Note note;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param note  of the person to be updated to
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
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());

        Set<Note> mutableNotesList = new HashSet<>(meetingToEdit.getNotes());
        mutableNotesList.add(note);

        Meeting editedMeeting = new Meeting(
                meetingToEdit.getTitle(), meetingToEdit.getTime(), meetingToEdit.getPlace(),
                meetingToEdit.getDescription(), mutableNotesList, meetingToEdit.getContacts());

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);

        return new CommandResult(generateSuccessMessage(editedMeeting), editedMeeting.getNoteString(),
                false, false, null, editedMeeting, CommandResult.ListType.MEETINGS);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code meetingToEdit}.
     */
    private String generateSuccessMessage(Meeting meetingToEdit) {
        String message = !note.note.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, meetingToEdit);
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
