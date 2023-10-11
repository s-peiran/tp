package seedu.address.model.meeting;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Meeting in Notenote.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Meeting {

    private static final AtomicInteger nextId = new AtomicInteger();

    private final int id;

    private Title title;

    private Time time;

    private Place place;

    private Description description;

    /**
     * Every field must be present and not null.
     */
    public Meeting(Title title, Time time, Place place, Description description) {
        this.id = nextId.getAndIncrement();
        this.title = title;
        this.time = time;
        this.place = place;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Time getTime() {
        return time;
    }

    public Place getPlace() {
        return place;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
        .add("id", id)
        .add("title", title)
        .add("time", time)
        .add("place", place)
        .add("description", description)
        .toString();
    }

    /**
     * Returns true if both meetings have the same title.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getTitle().equals(getTitle());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return id == otherMeeting.id;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, title, time, place, description);
    }

}
