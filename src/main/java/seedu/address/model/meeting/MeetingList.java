package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        internalList.add(toAdd);
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
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
