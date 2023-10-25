package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.contact.Contact;
import seedu.address.model.note.Note;

/**
 * Represents a Meeting in Notenote.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Meeting {

    private Title title;

    private Time time;

    private Place place;

    private Description description;
    private Set<Note> notes = new HashSet<>();
    private ArrayList<Contact> contacts = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Meeting(Title title, Time time, Place place, Description description,
                   Set<Note> notes, ArrayList<Contact> contacts) {
        requireAllNonNull(title, time, place, description, notes, contacts);
        this.title = title;
        this.time = time;
        this.place = place;
        this.description = description;
        this.notes.addAll(notes);
        this.contacts.addAll(contacts);
    }

    public Title getTitle() {
        return title;
    }

    public String getTitleString() {
        return title.toString();
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

    public Set<Note> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    public ArrayList<Contact> getContacts() {
        return new ArrayList<>(Collections.unmodifiableList(contacts));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("title", title)
            .add("time", time)
            .add("place", place)
            .add("description", description)
            .add("notes", notes)
            .add("contacts", contacts)
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
        return title.equals(otherMeeting.title)
            && time.equals(otherMeeting.time)
            && place.equals(otherMeeting.place)
            && description.equals(otherMeeting.description)
            && notes.equals(otherMeeting.notes)
            && contacts.equals(otherMeeting.contacts);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, time, place, description);
    }

}
