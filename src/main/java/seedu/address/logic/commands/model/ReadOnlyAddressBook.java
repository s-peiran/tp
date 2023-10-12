package seedu.address.logic.commands.model;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.model.contact.Contact;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

}
