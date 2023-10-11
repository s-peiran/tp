package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.exceptions.ContactNotFoundException;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;


/**
 * A list of meetings that does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class MeetingList implements Iterable<Meeting> {
    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();

    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a meeting to the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Checks if a given meeting exists in the list.
     */
    public boolean contains(Meeting toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Replaces the meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The contact identity of {@code editedMeeting} must not be the same as another existing meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ContactNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateContactException();
        }

        internalList.set(index, editedMeeting);
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateContactException();
        }

        internalList.setAll(meetings);
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingList)) {
            return false;
        }

        MeetingList otherMeetingList = (MeetingList) other;
        return internalList.equals(otherMeetingList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code meetings} contains only unique meetings.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).isSameMeeting(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
