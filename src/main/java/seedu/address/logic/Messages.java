package seedu.address.logic;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command: %1$s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid";
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_MEETINGS_LISTED_OVERVIEW = "%1$d meetings listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code contact} for display to the user.
     */
    public static String formatContact(Contact contact) {
        final StringBuilder builder = new StringBuilder();
        builder.append(contact.getName())
                .append("; Phone: ")
                .append(contact.getPhone())
                .append("; Email: ")
                .append(contact.getEmail())
                .append("; Address: ")
                .append(contact.getAddress())
                .append("; Tags: ");
        contact.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code meeting} for display to the user.
     */
    public static String formatMeeting(Meeting meeting) {
        final StringBuilder builder = new StringBuilder();
        builder.append(meeting.getTitle())
                .append("; Time: ")
                .append(meeting.getTime())
                .append("; Place: ")
                .append(meeting.getPlace())
                .append("; Description: ")
                .append(meeting.getDescription());
        return builder.toString();
    }

    /**
     * Formats the {@code contacts in a meeting} for display to the user.
     */
    public static String formatMeetingContacts(Meeting meeting) {
        final StringBuilder builder = new StringBuilder();
        ArrayList<Contact> contactList = new ArrayList<>(meeting.getContacts());
        for (Contact c : contactList) {
            builder.append(c.getName().toString() + ", ");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 2);
        }
        return "Contacts: " + builder.toString();
    }
}
