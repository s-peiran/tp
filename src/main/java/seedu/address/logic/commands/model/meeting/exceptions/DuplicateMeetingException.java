package seedu.address.logic.commands.model.meeting.exceptions;

/**
 * Signals that the operation will result in duplicate Meetings (Meetings are considered duplicates
 * if they have the same id).
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super("Operation would result in duplicate meetings");
    }
}
