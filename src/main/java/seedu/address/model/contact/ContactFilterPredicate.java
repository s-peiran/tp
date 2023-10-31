package seedu.address.model.contact;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Contact}'s {@code all values} matches any of the keywords given.
 */
public class ContactFilterPredicate implements Predicate<Contact> {
    private final List<String> nameKeywords;
    private final String phoneNumber;
    private final String emailAddress;
    private final List<String> addressKeywords;
    private final List<String> tagKeywords;
    private final List<String> noteKeywords;

    /**
     * Contructs the ContactFilterPredicate class
     */
    public ContactFilterPredicate(List<String> nameKeywords, String phoneNumber,
                                  String emailAddress, List<String> addressKeywords,
                                  List<String> tagKeywords, List<String> noteKeywords) {
        this.nameKeywords = nameKeywords;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.addressKeywords = addressKeywords;
        this.tagKeywords = tagKeywords;
        this.noteKeywords = noteKeywords;
    }

    @Override
    public boolean test(Contact contact) {
        boolean name = (nameKeywords.size() == 1 && nameKeywords.get(0).isEmpty()) || nameKeywords.stream()
            .anyMatch(keyword -> Arrays.stream(contact.getName().fullName.split("\\s+")).anyMatch(word ->
                word.equalsIgnoreCase(keyword)));
        boolean email = emailAddress.equals("") || emailAddress.equals(contact.getEmail().value);
        boolean phone = phoneNumber.equals("") || phoneNumber.equals(contact.getPhone().value);
        boolean address = (addressKeywords.size() == 1 && addressKeywords.get(0).isEmpty()) || addressKeywords.stream()
            .anyMatch(keyword -> Arrays.stream(contact.getAddress().value.split("\\s+")).anyMatch(word ->
                word.equalsIgnoreCase(keyword)));
        boolean tag = (tagKeywords.size() == 1 && tagKeywords.get(0).isEmpty())
            || tagKeywords.stream().anyMatch(keyword -> Arrays.stream(contact.getTagString().split("\\s+"))
            .anyMatch(word -> word.equalsIgnoreCase(keyword)));
        boolean note = (noteKeywords.size() == 1 && noteKeywords.get(0).isEmpty())
            || noteKeywords.stream().anyMatch(keyword -> Arrays.stream(contact.getNoteString().split("\\s+"))
            .anyMatch(word -> word.equalsIgnoreCase(keyword)));
        return name && email && phone && address && tag && note;
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
            && addressKeywords.equals(otherContactFilterPredicate.addressKeywords)
            && tagKeywords.equals(otherContactFilterPredicate.tagKeywords)
            && noteKeywords.equals(otherContactFilterPredicate.noteKeywords);
    }
}
