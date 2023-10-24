package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ListType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ContactListPanel contactListPanel;
    private MeetingListPanel meetingListPanel;
    private ContactDetailPanel contactDetailPanel;
    private MeetingDetailPanel meetingDetailPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private NoteDisplay noteDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private VBox contactListPanelPlaceholder;

    @FXML
    private VBox meetingListPanelPlaceholder;

    @FXML
    private VBox contactDetailPanelPlaceholder;

    @FXML
    private VBox meetingDetailPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane noteDisplayPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        contactListPanel = new ContactListPanel(logic.getFilteredContactList());
        contactListPanelPlaceholder.getChildren().addAll(contactListPanel.getRoot());

        meetingListPanel = new MeetingListPanel(logic.getFilteredMeetingList());
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        contactDetailPanel = new ContactDetailPanel();
        contactDetailPanelPlaceholder.getChildren().add(contactDetailPanel.getRoot());

        meetingDetailPanel = new MeetingDetailPanel();
        meetingDetailPanelPlaceholder.getChildren().add(meetingDetailPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        noteDisplay = new NoteDisplay();
        noteDisplayPlaceholder.getChildren().add(noteDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Displays the contact list and hides the meeting list.
     */
    public void showContactList() {
        contactListPanelPlaceholder.setVisible(true);
        meetingListPanelPlaceholder.setVisible(false);
    }

    /**
     * Displays the meeting list and hides the contact list.
     */
    public void showMeetingList() {
        contactListPanelPlaceholder.setVisible(false);
        meetingListPanelPlaceholder.setVisible(true);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            logger.info("Note: " + commandResult.getNoteToDisplay());
            noteDisplay.setNoteToDisplay(commandResult.getNoteToDisplay());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.getListType() == ListType.CONTACTS) {
                showContactList();
            } else if (commandResult.getListType() == ListType.MEETINGS) {
                showMeetingList();
            }

            if (commandResult.getContact().isPresent()) {
                Contact displayedContact = commandResult.getContact().get();
                contactDetailPanel.setContact(displayedContact);

                contactDetailPanelPlaceholder.setVisible(true);
                contactDetailPanelPlaceholder.setManaged(true);
                meetingDetailPanelPlaceholder.setVisible(false);
                meetingDetailPanelPlaceholder.setManaged(false);
            } else if (commandResult.getMeeting().isPresent()) {
                Meeting displayedMeeting = commandResult.getMeeting().get();
                meetingDetailPanel.setMeeting(displayedMeeting);

                contactDetailPanelPlaceholder.setVisible(false);
                contactDetailPanelPlaceholder.setManaged(false);
                meetingDetailPanelPlaceholder.setVisible(true);
                meetingDetailPanelPlaceholder.setManaged(true);
            }

            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("An error occurred while executing command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
