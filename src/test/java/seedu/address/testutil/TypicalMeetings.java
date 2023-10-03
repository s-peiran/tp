package seedu.address.testutil;

import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {
    public static final Meeting ALPHA = new MeetingBuilder()
            .withTitle("ALPHA")
            .withTime("23/10/2021 13:30")
            .withLocation("Blah blah")
            .withDescription("testDescriptini1234!@#$")
            .build();

    public static final Meeting BETA = new MeetingBuilder()
            .withTitle("BETA")
            .withTime("03/03/2003 03:30")
            .withLocation("Blah blaasdfh")
            .withDescription("tesgasdgadstDescriptini1234!@#$")
            .build();
}
