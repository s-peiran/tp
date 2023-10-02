package seedu.address.model.meeting;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Meeting in Notenote.
 * Guarantees: field values are validated, id and title are guaranteed to be present and not null.
 */
public class Meeting {

    private static final AtomicInteger nextId = new AtomicInteger();

    private final int id;

    private Title title;

    private Time time;

    private Location location;

    private Description description;

    /**
     * Only the title must be present and not null. The remaining fields can be null.
     */
    public Meeting(Title title, Time time, Location location, Description description) {
        this.id = nextId.getAndIncrement();
        this.title = title;
        this.time = time;
        this.location = location;
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

    public Location getLocation() {
        return location;
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
        .add("location", location)
        .add("description", description)
        .toString();
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
        return Objects.hash(id, title, time, location, description);
    }

}
