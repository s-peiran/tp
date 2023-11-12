package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.note.Note;

/**
 * Represents a Contact in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact implements Comparable<Contact> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final ArrayList<Note> notes = new ArrayList<>();

    // Observer
    private ArrayList<Meeting> observerList = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Contact(Name name, Phone phone, Email email, List<Note> notes, List<Meeting> observers) {
        requireAllNonNull(name, phone, email, notes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.notes.addAll(notes);
        this.observerList.addAll(observers);
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.toString();
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(Collections.unmodifiableList(notes));
    }

    public String getNoteString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < notes.size(); i++) {
            int index = i + 1;
            sb.append(index + ". " + notes.get(i).toString() + "\n");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        } else {
            return "";
        }
        return sb.toString();
    }

    public ArrayList<Meeting> getObservers() {
        return this.observerList;
    }

    /**
     * Removes the contact from all the meeting that observes it if this contact has been deleted via
     * DeleteContactCommand
     */
    public void notifyObservers(Model model) {
        for (Meeting observer : observerList) {
            observer.remove(this);
            model.update(observer, this);
        }
    }

    /**
     * Add a meeting to the observerList
     *
     * @param meeting the meeting to be added to the observerList
     */
    public void addObserver(Meeting meeting) {
        observerList.add(meeting);
    }

    /**
     * Returns true if both contacts have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
            && otherContact.getName().equals(getName());
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return name.equals(otherContact.name)
            && phone.equals(otherContact.phone)
            && email.equals(otherContact.email)
            && notes.equals(otherContact.notes);
    }

    @Override
    public int compareTo(Contact other) {
        Name otherName = other.name;
        return this.name.toString().compareTo(otherName.toString());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, notes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", name)
            .add("phone", phone)
            .add("email", email)
            .add("notes", notes)
            .toString();
    }

}
