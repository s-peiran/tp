package seedu.address.testutil;

import seedu.address.logic.commands.model.meeting.Description;
import seedu.address.logic.commands.model.meeting.Location;
import seedu.address.logic.commands.model.meeting.Meeting;
import seedu.address.logic.commands.model.meeting.Time;
import seedu.address.logic.commands.model.meeting.Title;

/**
 * A utility class to help with building Person objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_TITLE = "CS2103 Weekly Meeting";

    public static final String DEFAULT_TIME = "03/10/2023 19:00";

    public static final String DEFAULT_LOCATION = "Zoom";

    public static final String DEFAULT_DESCRIPTION = "No description";

    private Title title;

    private Time time;

    private Location location;

    private Description description;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        title = new Title(DEFAULT_TITLE);
        time = new Time(DEFAULT_TIME);
        location = new Location(DEFAULT_LOCATION);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Sets the {@code Title} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Meeting build() {
        return new Meeting(title, time, location, description);
    }
}
