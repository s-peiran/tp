package seedu.address.ui.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.note.Note;

/**
 * Jackson-friendly version of {@link Contact}.
 */
public class JsonAdaptedContact {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;

    private final List<JsonAdaptedNote> notes = new ArrayList<>();
    private final ArrayList<JsonAdaptedMeeting> observerList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("notes") List<JsonAdaptedNote> notes,
                              @JsonProperty("observerList") ArrayList<JsonAdaptedMeeting> observerList) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (notes != null) {
            this.notes.addAll(notes);
        }
        if (observerList != null) {
            this.observerList.addAll(observerList);
        }
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        notes.addAll(source.getNotes().stream()
            .map(JsonAdaptedNote::new)
            .collect(Collectors.toList()));
        observerList.addAll(source.getObservers().stream()
            .map(JsonAdaptedMeeting::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Note> contactNotes = new ArrayList<>();
        for (JsonAdaptedNote note : notes) {
            contactNotes.add(note.toModelType());
        }
        final ArrayList<Meeting> contactObservers = new ArrayList<>();
        for (JsonAdaptedMeeting observer : observerList) {
            contactObservers.add(observer.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final ArrayList<Note> modelNotes = new ArrayList<>(contactNotes);

        final ArrayList<Meeting> modelObservers = new ArrayList<>(contactObservers);

        return new Contact(modelName, modelPhone, modelEmail, modelNotes, modelObservers);
    }

}
