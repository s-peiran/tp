package seedu.address.testutil;

import static seedu.address.model.util.SampleDataUtil.getNoteSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Place;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Title;
import seedu.address.model.note.Note;

/**
 * A utility class to help with building Person objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_TITLE = "CS2103 Weekly Meeting";

    public static final String DEFAULT_TIME = "03/10/2023 19:00";

    public static final String DEFAULT_PLACE = "Zoom";

    public static final String DEFAULT_DESCRIPTION = "No description";

    private Title title;

    private Time time;

    private Place place;

    private Description description;
    private Set<Note> notes;
    private ArrayList<Contact> contacts;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        title = new Title(DEFAULT_TITLE);
        time = new Time(DEFAULT_TIME);
        place = new Place(DEFAULT_PLACE);
        description = new Description(DEFAULT_DESCRIPTION);
        notes = new HashSet<Note>();
        contacts = new ArrayList<Contact>();
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        title = meetingToCopy.getTitle();
        time = meetingToCopy.getTime();
        place = meetingToCopy.getPlace();
        description = meetingToCopy.getDescription();
        notes = new HashSet<>(meetingToCopy.getNotes());
        contacts = new ArrayList<>(meetingToCopy.getContacts());
    }

    /**
     * Sets the {@code Title} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Place} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPlace(String place) {
        this.place = new Place(place);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withNotes(String... notes) {
        this.notes = getNoteSet(notes);
        return this;
    }

    public Meeting build() {
        return new Meeting(title, time, place, description, notes, contacts);
    }
}
