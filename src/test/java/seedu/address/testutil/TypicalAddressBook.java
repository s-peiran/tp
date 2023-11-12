package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Contact} objects to be used in tests.
 */
public class TypicalAddressBook {

    public static final Contact ALICE = new ContactBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .build();
    public static final Contact BENSON = new ContactBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withNotes("Likes chicken", "Hates tiramisu").build();
    public static final Contact CARL = new ContactBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withNotes("Enjoys rom-coms").build();
    public static final Contact DANIEL = new ContactBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withNotes("Hates football").build();
    public static final Contact ELLE = new ContactBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withNotes("Keyboard geek").build();
    public static final Contact FIONA = new ContactBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withNotes("Gym rat").build();
    public static final Contact GEORGE = new ContactBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withNotes("Likes bread").build();

    // Manually added
    public static final Contact HOON = new ContactBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withNotes("Likes chicken").build();
    public static final Contact IDA = new ContactBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withNotes("Likes chicken").build();

    // Manually added - Contact's details found in {@code CommandTestUtil}
    public static final Contact AMY = new ContactBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withNotes(VALID_NOTE_AMY).build();
    public static final Contact BOB = new ContactBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    public static final Meeting CS2103 = new MeetingBuilder()
            .withTitle("CS2103 Meeting")
            .withTime("01/01/2023 00:00")
            .withPlace("Zoom")
            .withDescription("")
            .build();
    public static final Meeting GES2001 = new MeetingBuilder()
            .withTitle("GES2001 Meeting")
            .withTime("01/01/2023 20:30")
            .withPlace("Discord")
            .withDescription("Project details")
            .withNotes("Skeletal PPP")
            .build();
    public static final Meeting LAJ2101 = new MeetingBuilder()
            .withTitle("LAJ2101 Meeting")
            .withTime("01/01/2023 16:00")
            .withPlace("Classroom A")
            .withDescription("")
            .withNotes("Skeletal PPP")
            .build();
    public static final Meeting GF = new MeetingBuilder()
            .withTitle("Date with Girlfriend")
            .withTime("01/05/2023 18:00")
            .withPlace("Sentosa")
            .withDescription("Picnic with Sandwiches!")
            .build();

    private TypicalAddressBook() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical contacts.
     */
    public static AddressBook getTypicalContactsAddressBook() {
        AddressBook ab = new AddressBook();
        for (Contact contact : getTypicalContacts()) {
            ab.addContact(contact);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical meetings.
     */
    public static AddressBook getTypicalMeetingsAddressBook() {
        AddressBook ab = new AddressBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical contacts and meetings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Contact contact : getTypicalContacts()) {
            ab.addContact(contact);
        }
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }

    public static List<Contact> getTypicalContacts() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2103, GES2001, LAJ2101, GF));
    }
}
