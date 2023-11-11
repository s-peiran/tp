package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.note.Note;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public class ContactBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NOTE = "Some note";

    private Name name;
    private Phone phone;
    private Email email;
    private List<Note> notes;
    private ArrayList<Meeting> observerList;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        notes = new ArrayList<>();
        observerList = new ArrayList<>();
    }

    /**
     * Initializes the ContactBuilder with the data of {@code contactToCopy}.
     */
    public ContactBuilder(Contact contactToCopy) {
        name = contactToCopy.getName();
        phone = contactToCopy.getPhone();
        email = contactToCopy.getEmail();
        notes = new ArrayList<>(contactToCopy.getNotes());
        observerList = new ArrayList<>(contactToCopy.getObservers());
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Contact} that we are building.
     */
    public ContactBuilder withNotes(String... notes) {
        this.notes = SampleDataUtil.getNoteList(notes);
        return this;
    }


    public Contact build() {
        return new Contact(name, phone, email, notes, new ArrayList<Meeting>());
    }

}
