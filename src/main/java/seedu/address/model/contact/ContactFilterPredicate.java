package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Contact}'s {@code all values} matches any of the keywords given.
 */
public class ContactFilterPredicate implements Predicate<Contact> {
    private final List<String> nameKeywords;
    private final String phoneNumber;
    private final String emailAddress;
    private final List<String> noteKeywords;

    /**
     * Contructs the ContactFilterPredicate class
     */
    public ContactFilterPredicate(List<String> nameKeywords, String phoneNumber,
                                  String emailAddress, List<String> noteKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.noteKeywords = noteKeywords;
    }

    @Override
    public boolean test(Contact contact) {
        boolean name = (nameKeywords.size() == 1 && nameKeywords.get(0).isEmpty()) || nameKeywords.stream()
                .anyMatch(keyword -> contact.getName().fullName.toLowerCase().contains(keyword.toLowerCase()));
        boolean email = emailAddress.isEmpty() || contact.getEmail().value.toLowerCase()
                .contains(emailAddress.toLowerCase());
        boolean phone = phoneNumber.isEmpty() || contact.getPhone().value.contains(phoneNumber);
        boolean note = (noteKeywords.size() == 1 && noteKeywords.get(0).isEmpty())
                || noteKeywords.stream().anyMatch(keyword -> contact.getNoteString().toLowerCase()
                .contains(keyword.toLowerCase()));
        return name && email && phone && note;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactFilterPredicate)) {
            return false;
        }

        ContactFilterPredicate otherContactFilterPredicate = (ContactFilterPredicate) other;
        return nameKeywords.equals(otherContactFilterPredicate.nameKeywords)
                && phoneNumber.equals(otherContactFilterPredicate.phoneNumber)
                && emailAddress.equals(otherContactFilterPredicate.emailAddress)
                && noteKeywords.equals(otherContactFilterPredicate.noteKeywords);
    }
}
