package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Place;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Title;
import seedu.address.model.note.Note;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String title;
    private final String time;
    private final String place;
    private final String description;
    private final List<JsonAdaptedNote> notes = new ArrayList<>();
    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("title") String title, @JsonProperty("time") String time,
                              @JsonProperty("place") String place, @JsonProperty("description") String description,
                              @JsonProperty("notes") List<JsonAdaptedNote> notes,
                              @JsonProperty("contacts") List<JsonAdaptedContact> contacts) {
        this.title = title;
        this.time = time;
        this.place = place;
        this.description = description;
        if (notes != null) {
            this.notes.addAll(notes);
        }
        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        title = source.getTitle().fullTitle;
        time = source.getTime().toString();
        place = source.getPlace().fullPlace;
        description = source.getDescription().fullDescription;
        notes.addAll(source.getNotes().stream()
            .map(JsonAdaptedNote::new)
            .collect(Collectors.toList()));
        ;
        contacts.addAll(source.getContacts().stream()
            .map(JsonAdaptedContact::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        final List<Note> meetingNotes = new ArrayList<>();
        final List<Contact> contactList = new ArrayList<>();
        for (JsonAdaptedNote note : notes) {
            meetingNotes.add(note.toModelType());
        }
        for (JsonAdaptedContact c : contacts) {
            contactList.add(c.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = Time.fromString(time);


        if (place == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Place.class.getSimpleName()));
        }
        if (!Place.isValidPlace(place)) {
            throw new IllegalValueException(Place.MESSAGE_CONSTRAINTS);
        }
        final Place modelPlace = new Place(place);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        final Set<Note> modelNotes = new HashSet<>(meetingNotes);
        final ArrayList<Contact> modelContacts = new ArrayList<>(contactList);

        return new Meeting(modelTitle, modelTime, modelPlace, modelDescription, modelNotes, modelContacts);
    }

}
