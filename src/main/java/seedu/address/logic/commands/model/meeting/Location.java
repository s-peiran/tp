package seedu.address.logic.commands.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's location in Notenote.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Locations should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullLocation;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        fullLocation = location;
    }

    /**
     * Returns true if a given string is a valid location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullLocation;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return fullLocation.equals(otherLocation.fullLocation);
    }

    @Override
    public int hashCode() {
        return fullLocation.hashCode();
    }
}
