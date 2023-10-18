package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final String noteToDisplay;

    /** Type of list to be displayed on the GUI. */
    public enum ListType {
        CONTACTS, MEETINGS, NONE
    }

    private final ListType listType;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, String noteToDisplay,
            boolean showHelp, boolean exit, ListType listType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.noteToDisplay = noteToDisplay;
        this.showHelp = showHelp;
        this.exit = exit;
        this.listType = listType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields,
     * with listType set to its default value.
     */
    public CommandResult(String feedbackToUser, String noteToDisplay, boolean showHelp, boolean exit) {
        this(feedbackToUser, noteToDisplay, showHelp, exit, ListType.NONE);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code noteToDisplay},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, String noteToDisplay) {
        this(feedbackToUser, noteToDisplay, false, false);
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
