package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


}
