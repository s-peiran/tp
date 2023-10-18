package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the note area that is displayed at the beside the contact/meeting list.
 */
public class NoteDisplay extends UiPart<Region> {

    private static final String FXML = "NoteArea.fxml";

    @FXML
    private TextArea noteDisplay;

    public NoteDisplay() {
        super(FXML);
    }

    public void setNoteToDisplay(String noteToDisplay) {
        if (noteToDisplay != null) {
            noteDisplay.setText(noteToDisplay);
        }
    }
}
