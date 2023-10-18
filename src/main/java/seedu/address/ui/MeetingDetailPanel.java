package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * A UI component that displays the details of a {@code Meeting}.
 */
public class MeetingDetailPanel extends UiPart<Region> {

    private static final String FXML = "MeetingDetailPanel.fxml";

    @FXML
    private Label title;

    @FXML
    private Label time;

    @FXML
    private Label place;

    @FXML
    private Label description;

    public MeetingDetailPanel() {
        super(FXML);
    }

    public void setMeeting(Meeting meeting) {
        title.setText(meeting.getTitle().toString());
        time.setText(meeting.getTime().toString());
        place.setText(meeting.getPlace().toString());
        description.setText(meeting.getDescription().toString());
    }

}
