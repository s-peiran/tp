package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.getTitle().fullTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TitleContainsKeywordsPredicate)) {
            return false;
        }

        TitleContainsKeywordsPredicate otherTitleContainsKeywordsPredicate = (TitleContainsKeywordsPredicate) other;
        return keywords.equals(otherTitleContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
