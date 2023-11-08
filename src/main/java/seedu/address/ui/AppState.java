package seedu.address.ui;

import java.util.Optional;

import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * Represents the state of the application, which includes the mode the application
 * is currently in (Contacts or Meetings),the type of list to be displayed on the UI (Contacts or Meetings),
 * and the contact/meeting viewed.
 */
public class AppState {

    private static AppState instance;

    private ListType listType;
    private ModeType mode;
    private Optional<Contact> contactToView;
    private Optional<Meeting> meetingToView;

    /**
     * List of modes that the application can be in.
     */
    public enum ModeType {
        CONTACTS, MEETINGS
    }

    /**
     * Type of list to be displayed on the GUI.
     */
    public enum ListType {
        CONTACTS, MEETINGS
    }

    /**
     * Private constructor to prevent creating instance externally. Initializes the listType and mode to CONTACTS.
     */
    private AppState() {
        this.listType = ListType.CONTACTS;
        this.mode = ModeType.CONTACTS;
        this.contactToView = Optional.empty();
        this.meetingToView = Optional.empty();
    }

    /**
     * Gets the instance of AppState, creating one if it doesn't exist.
     *
     * @return The instance of AppState.
     */
    public static AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    /**
     * Gets the current list type of the application.
     *
     * @return The current list type of the application.
     */
    public ListType getListType() {
        return listType;
    }

    /**
     * Sets the list type of the application.
     *
     * @param listType The list type to be set.
     */
    public void setListType(ListType listType) {
        this.listType = listType;
    }

    /**
     * Gets the contact currently being viewed.
     *
     * @return The contact currently being viewed.
     * @throws IllegalStateException If no contact is currently being viewed.
     */
    public Contact getContact() {
        return contactToView.orElseThrow(() -> new IllegalStateException("No contact is currently being viewed."));
    }

    /**
     * Gets the meeting currently being viewed.
     *
     * @return The meeting currently being viewed.
     * @throws IllegalStateException If no meeting is currently being viewed.
     */
    public Meeting getMeeting() {
        return meetingToView.orElseThrow(() -> new IllegalStateException("No meeting is currently being viewed."));
    }

    /**
     * Sets the contact to be viewed. This will clear the meeting being viewed.
     *
     * @param contact The contact to be viewed.
     */
    public void setContact(Contact contact) {
        if (contact != null) {
            this.contactToView = Optional.of(contact);
        } else {
            this.contactToView = Optional.empty();
        }

        this.meetingToView = Optional.empty();
    }

    /**
     * Sets the meeting to be viewed. This will clear the contact being viewed.
     *
     * @param meeting The meeting to be viewed.
     */
    public void setMeeting(Meeting meeting) {
        if (meeting != null) {
            this.meetingToView = Optional.of(meeting);
        } else {
            this.meetingToView = Optional.empty();
        }

        this.contactToView = Optional.empty();
    }

    /**
     * Deletes the contact currently being viewed if it is the same as the given contact.
     *
     * @param contact The contact to be deleted.
     */
    public void deleteContact(Contact contact) {
        if (contactToView.map(c -> c.equals(contact)).orElse(false)) {
            contactToView = Optional.empty();
        }
    }

    /**
     * Deletes the meeting currently being viewed if it is the same as the given meeting.
     *
     * @param meeting The meeting to be deleted.
     */
    public void deleteMeeting(Meeting meeting) {
        if (meetingToView.map(m -> m.equals(meeting)).orElse(false)) {
            meetingToView = Optional.empty();
        }
    }

    /**
     * Checks if there is a contact currently being viewed.
     *
     * @return True if there is a contact currently being viewed, false otherwise.
     */
    public boolean isContactPresent() {
        return contactToView.isPresent();
    }

    /**
     * Checks if there is a meeting currently being viewed.
     *
     * @return True if there is a meeting currently being viewed, false otherwise.
     */
    public boolean isMeetingPresent() {
        return meetingToView.isPresent();
    }

    /**
     * Gets the current mode of the application.
     *
     * @return The current mode of the application.
     */
    public ModeType getModeType() {
        return mode;
    }

    /**
     * Sets the mode of the application.
     *
     * @param mode The mode to be set.
     */
    public void setModeType(ModeType mode) {
        this.mode = mode;
    }

    /**
     * Toggles the mode of the application between Contacts and Meetings.
     */
    public void changeMode() {
        if (mode == ModeType.CONTACTS) {
            mode = ModeType.MEETINGS;
        } else {
            mode = ModeType.CONTACTS;
        }
    }

    /**
     * Gets the current mode of the application. This method is a duplicate of getModeType.
     *
     * @return The current mode of the application.
     */
    public ModeType getMode() {
        return mode;
    }
}
