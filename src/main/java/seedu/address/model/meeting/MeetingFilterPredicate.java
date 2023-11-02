package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Tests that a {@code Meeting}'s {@code all values} matches any of the keywords given.
 */
public class MeetingFilterPredicate implements Predicate<Meeting> {
    private final List<String> titleKeywords;
    private final List<String> time;
    private final List<String> placeKeywords;
    private final List<String> descriptionKeywords;
    private final List<String> noteListKeywords;

    /**
     * Constructor for the MeetinFilterPredicate class
     */
    public MeetingFilterPredicate(List<String> titleKeywords, List<String> time,
                                  List<String> placeKeywords, List<String> descriptionKeywords,
                                  List<String> noteKeywords) {
        this.titleKeywords = titleKeywords;
        this.time = time;
        this.placeKeywords = placeKeywords;
        this.descriptionKeywords = descriptionKeywords;
        this.noteListKeywords = noteKeywords;
    }


    @Override
    public boolean test(Meeting meeting) {
        boolean title = (titleKeywords.size() == 1 && titleKeywords.get(0)
            .isEmpty()) || titleKeywords.stream().anyMatch(keyword -> meeting.getTitle()
            .fullTitle.toLowerCase().contains(keyword.toLowerCase()));
        // writing of stream assisted by chatGPT
        boolean chrono = false;
        Time meetingTime = meeting.getTime();
        Supplier<Boolean> case2 = () -> LocalDateTime.parse(time.get(0), Time.FORMATTER)
            .isBefore(meetingTime.getValue());
        Supplier<Boolean> case3 = () -> LocalDateTime.parse(time.get(1), Time.FORMATTER)
            .isAfter(meetingTime.getValue());
        Supplier<Boolean> case4 = () -> {
            LocalDateTime startTime = LocalDateTime.parse(time.get(0), Time.FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(time.get(1), Time.FORMATTER);
            return startTime.isBefore(meetingTime.getValue()) && endTime.isAfter(meetingTime.getValue());
        };
        // use of supplier suggested by chatGPT. In original code the logic is nested in if-else statements
        String timeStart = time.get(0);
        String timeEnd = time.get(1);
        if (timeStart.equals("") && timeEnd.equals("")) {
            chrono = true;
        } else if (!timeStart.equals("") && timeEnd.equals("")) {
            chrono = case2.get();
        } else if (timeStart.equals("") && !timeEnd.equals("")) {
            chrono = case3.get();
        } else if (!timeStart.equals("") && !timeEnd.equals("")) {
            chrono = case4.get();
        }
        boolean place = (placeKeywords.size() == 1 && placeKeywords.get(0).isEmpty()) || placeKeywords.stream()
            .anyMatch(keyword -> meeting.getPlace().fullPlace.toLowerCase().contains(keyword.toLowerCase()));
        boolean description = (descriptionKeywords.size() == 1 && descriptionKeywords.get(0)
            .isEmpty()) || descriptionKeywords.stream().anyMatch(keyword -> meeting.getDescription()
            .fullDescription.toLowerCase().contains(keyword.toLowerCase()));
        boolean note = (noteListKeywords.size() == 1 && noteListKeywords.get(0).isEmpty())
            || noteListKeywords.stream().anyMatch(keyword -> meeting.getNoteString()
            .toLowerCase().contains(keyword.toLowerCase()));
        return title && chrono && place && description && note;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingFilterPredicate)) {
            return false;
        }

        MeetingFilterPredicate otherMeetingFilterPredicate = (MeetingFilterPredicate) other;
        return titleKeywords.equals(otherMeetingFilterPredicate.titleKeywords)
            && time.equals(otherMeetingFilterPredicate.time)
            && placeKeywords.equals(otherMeetingFilterPredicate.placeKeywords)
            && descriptionKeywords.equals(otherMeetingFilterPredicate.descriptionKeywords)
            && noteListKeywords.equals(otherMeetingFilterPredicate.noteListKeywords);
    }
}
