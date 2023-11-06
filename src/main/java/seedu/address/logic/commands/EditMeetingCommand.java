package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Place;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Title;
import seedu.address.model.note.Note;
import seedu.address.ui.AppState;

/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
        + "by the index number used in the displayed meeting list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_TITLE + " TITLE] "
        + "[" + PREFIX_TIME + " TIME] "
        + "[" + PREFIX_PLACE + " PLACE] "
        + "[" + PREFIX_DESCRIPTION + " DESCRIPTION]...\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + " 1 "
        + PREFIX_PLACE + " Zoom "
        + PREFIX_DESCRIPTION + " Discuss Project Details";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists.";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * @param index                 of the meeting in the filtered meeting list to edit
     * @param editMeetingDescriptor details to edit the meeting with
     */
    public EditMeetingCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.equals(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);

        AppState appState = AppState.getInstance();
        appState.setMeeting(editedMeeting);

        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS,
            Messages.formatMeeting(editedMeeting)), false, false);
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingtToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit, EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        Title updatedTitle = editMeetingDescriptor.getTitle().orElse(meetingToEdit.getTitle());
        Time updatedTime = editMeetingDescriptor.getTime().orElse(meetingToEdit.getTime());
        Place updatedPlace = editMeetingDescriptor.getPlace().orElse(meetingToEdit.getPlace());
        Description updatedDescription = editMeetingDescriptor.getDescription().orElse(meetingToEdit.getDescription());
        ArrayList<Note> updatedNotes = editMeetingDescriptor.getNotes().orElse(meetingToEdit.getNotes());
        ArrayList<Contact> updatedContacts = editMeetingDescriptor.getContacts().orElse(meetingToEdit.getContacts());
        return new Meeting(updatedTitle, updatedTime, updatedPlace, updatedDescription, updatedNotes, updatedContacts);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        EditMeetingCommand otherEditMeetingCommand = (EditMeetingCommand) other;
        return index.equals(otherEditMeetingCommand.index)
            && editMeetingDescriptor.equals(otherEditMeetingCommand.editMeetingDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .add("editMeetingDescriptor", editMeetingDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Title title;
        private Time time;
        private Place place;
        private Description description;

        private ArrayList<Note> notes;
        private ArrayList<Contact> contacts;

        public EditMeetingDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            setTitle(toCopy.title);
            setTime(toCopy.time);
            setPlace(toCopy.place);
            setDescription(toCopy.description);
            setNotes(toCopy.notes);
            setContacts(toCopy.contacts);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, time, place, description);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
        }

        public void setPlace(Place place) {
            this.place = place;
        }

        public Optional<Place> getPlace() {
            return Optional.ofNullable(place);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code notes} to this object's {@code notes}.
         * A defensive copy of {@code notes} is used internally.
         */
        public void setNotes(ArrayList<Note> notes) {
            this.notes = notes;
        }

        /**
         * Returns an unmodifiable note set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code notes} is null.
         */
        public Optional<ArrayList<Note>> getNotes() {
            return (notes != null) ? Optional.of(new ArrayList<>(Collections.unmodifiableList(notes)))
                    : Optional.empty();
        }

        public void setContacts(ArrayList<Contact> contacts) {
            this.contacts = (contacts != null) ? new ArrayList<>(contacts) : null;
        }

        public Optional<ArrayList<Contact>> getContacts() {
            return (contacts != null)
                ? Optional.of(new ArrayList<Contact>(Collections.unmodifiableList(contacts)))
                : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            EditMeetingDescriptor otherEditMeetingDescriptor = (EditMeetingDescriptor) other;
            return Objects.equals(title, otherEditMeetingDescriptor.title)
                && Objects.equals(time, otherEditMeetingDescriptor.time)
                && Objects.equals(place, otherEditMeetingDescriptor.place)
                && Objects.equals(description, otherEditMeetingDescriptor.description);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("title", title)
                .add("time", time)
                .add("place", place)
                .add("description", description)
                .toString();
        }
    }
}
