package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;
import seedu.address.ui.AppState;


/**
 * Adds a contact to a meeting.
 */
public class AddContactToMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the participants to the meeting identified "
            + "by the name of the contact. "
            + "Parameters: " + PREFIX_NAME
            + " [CONTACT NAME] "
            + PREFIX_TITLE + " [MEETING NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME
            + " Sarah Woo " + PREFIX_TITLE + " Project Discussion";

    public static final String MESSAGE_ADD_CONTACT_SUCCESS = "Added contact '%s' to Meeting '%s'";
    public static final String MESSAGE_CONTACT_NOT_FOUND = "The person specified is not created";
    public static final String MESSAGE_MEETING_NOT_FOUND = "The meeting specified is not created";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the meeting";

    private final String meetingTitle;
    private final String contactName;

    /**
     * @param meetingTitle String of the meeting which the contact is added to
     * @param contactName  String of the contact to be added in the meeting
     */
    public AddContactToMeetingCommand(String meetingTitle, String contactName) {
        requireAllNonNull(meetingTitle, contactName);

        this.meetingTitle = meetingTitle;
        this.contactName = contactName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Meeting> meetingList = model.getFilteredMeetingList();
        List<Contact> contactList = model.getFilteredContactList();
        Contact contact = null;
        Meeting meetingToEdit = null;
        boolean contactFound = false;
        for (Contact c : contactList) {
            if (c.getNameString().equals(contactName)) {
                contact = c;
                contactFound = true;
                break;
            }
        }
        if (!contactFound) {
            throw new CommandException(MESSAGE_CONTACT_NOT_FOUND);
        }
        boolean meetingFound = false;
        for (Meeting m : meetingList) {
            if (m.getTitleString().equals(meetingTitle)) {
                meetingToEdit = m;
                meetingFound = true;
                break;
            }
        }
        if (!meetingFound) {
            throw new CommandException(MESSAGE_MEETING_NOT_FOUND);
        }
        ArrayList<Contact> listOfContacts = new ArrayList<>(meetingToEdit.getContacts());
        if (listOfContacts.stream().anyMatch(contact::isSameContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }
        listOfContacts.add(contact);

        Meeting editedMeeting = new Meeting(
                meetingToEdit.getTitle(), meetingToEdit.getTime(), meetingToEdit.getPlace(),
                meetingToEdit.getDescription(), meetingToEdit.getNotes(), listOfContacts);

        model.setMeeting(meetingToEdit, editedMeeting);
        model.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);

        AppState appState = AppState.getInstance();
        appState.setMeeting(editedMeeting);

        return new CommandResult(String.format(MESSAGE_ADD_CONTACT_SUCCESS,
                contactName, meetingTitle), false, false);
    }

    public String getContactName() {
        return contactName;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddContactToMeetingCommand)) {
            return false;
        }

        // state check
        AddContactToMeetingCommand e = (AddContactToMeetingCommand) other;
        return this.meetingTitle.equals(e.getMeetingTitle()) && this.contactName.equals(e.getContactName());
    }
}
