package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.contact.Contact;

/**
 * A UI component that displays the details of a {@code Contact}.
 */
public class ContactDetailPanel extends UiPart<Region> {

    private static final String FXML = "ContactDetailPanel.fxml";

    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private FlowPane tags;

    public ContactDetailPanel() {
        super(FXML);
    }

    public void setContact(Contact contact) {
        name.setText(contact.getName().toString());
        phone.setText(contact.getPhone().toString());
        address.setText(contact.getAddress().toString());
        email.setText(contact.getEmail().toString());

        tags.getChildren().clear();
        contact.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}