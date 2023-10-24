package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final String noteToDisplay;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Type of list to be displayed on the GUI. */
    public enum ListType {
        CONTACTS, MEETINGS, NONE
    }

    private final ListType listType;

    /** The contact to be viewed on the GUI. */
    private final Optional<Contact> contactToView;

    /** The meeting to be viewed on the GUI. */
    private final Optional<Meeting> meetingToView;

    /**
     * Constructs a {@code CommandResult} with all specified fields.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param noteToDisplay The note associated with the command result.
     * @param showHelp Boolean indicating if help information should be shown.
     * @param exit Boolean indicating if the application should exit.
     * @param contactToView The contact associated with this command result.
     * @param meetingToView The meeting associated with this command result.
     * @param listType The type of list to be displayed on the GUI.
     */
    public CommandResult(String feedbackToUser, String noteToDisplay,
            boolean showHelp, boolean exit, Contact contactToView, Meeting meetingToView, ListType listType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.noteToDisplay = noteToDisplay;
        this.showHelp = showHelp;
        this.exit = exit;
        this.contactToView = Optional.ofNullable(contactToView);
        this.meetingToView = Optional.ofNullable(meetingToView);
        this.listType = listType;
    }

    /**
     * Constructs a {@code CommandResult} with specified fields, and sets the listType to NONE
     * and other fields to their default values.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param noteToDisplay The note associated with the command result.
     * @param showHelp Boolean indicating if help information should be shown.
     * @param exit Boolean indicating if the application should exit.
     */
    public CommandResult(String feedbackToUser, String noteToDisplay, boolean showHelp, boolean exit) {
        this(feedbackToUser, noteToDisplay, showHelp, exit, null, null, ListType.NONE);
    }

    /**
     * Constructs a {@code CommandResult} with the given feedback message,
     * and sets other fields to their default values.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the given feedback message and note,
     * and sets other fields to their default values.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param noteToDisplay The note associated with the command result.
     */
    public CommandResult(String feedbackToUser, String noteToDisplay) {
        this(feedbackToUser, noteToDisplay, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the given feedback message and contact,
     * sets the listType to CONTACTS, and other fields to their default values.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param contactToView The contact associated with this command result.
     */
    public CommandResult(String feedbackToUser, Contact contactToView, String noteToDisplay) {
        this(feedbackToUser, noteToDisplay, false, false, contactToView, null, ListType.NONE);
    }

    /**
     * Constructs a {@code CommandResult} with the given feedback message and meeting,
     * sets the listType to MEETINGS, and other fields to their default values.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param meetingToView The meeting associated with this command result.
     */
    public CommandResult(String feedbackToUser, Meeting meetingToView, String noteToDisplay) {
        this(feedbackToUser, noteToDisplay, false, false, null, meetingToView, ListType.NONE);
    }

    /**
     * Constructs a {@code CommandResult} with the given feedback message and listType,
     * sets other fields to their default values.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     * @param listType The listType associated with this command result.
     */
    public CommandResult(String feedbackToUser, ListType listType) {
        this(feedbackToUser, null, false, false, null, null, listType);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getNoteToDisplay() {
        return noteToDisplay;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public Optional<Contact> getContact() {
        return contactToView;
    }

    public Optional<Meeting> getMeeting() {
        return meetingToView;
    }

    public ListType getListType() {
        return listType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        boolean eq = feedbackToUser.equals(otherCommandResult.feedbackToUser)
                    && showHelp == otherCommandResult.showHelp
                    && exit == otherCommandResult.exit
                    && listType == otherCommandResult.listType;
        if (noteToDisplay != null) {
            eq = eq && noteToDisplay.equals(otherCommandResult.noteToDisplay);
        }
        return eq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, noteToDisplay, showHelp, exit, listType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("noteToDisplay", noteToDisplay)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("listType", listType)
                .toString();
    }

}
