package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    private List<String> commandHistory = new ArrayList<>();
    private int currentHistoryPointer = 0;
    private String initialInput;
    private boolean hasStartedHistoryNavigation = false;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() and handleTextChange whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> {
            setStyleToDefault();
            handleTextChange();
        });
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.isEmpty()) {
            return;
        }

        // Prevent accidental duplicate command entries
        if (commandHistory.isEmpty() || !commandHistory.get(commandHistory.size() - 1).equals(commandText)) {
            commandHistory.add(commandText);
        }

        currentHistoryPointer = commandHistory.size();
        hasStartedHistoryNavigation = false;

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles key press events in the command text field.
     *
     * @param event The key press event.
     */
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            if (!hasStartedHistoryNavigation) {
                initialInput = commandTextField.getText();
                hasStartedHistoryNavigation = true;
            }
            navigateCommandHistory(-1);
            event.consume();
            break;
        case DOWN:
            if (!hasStartedHistoryNavigation) {
                initialInput = commandTextField.getText();
                hasStartedHistoryNavigation = true;
            }
            navigateCommandHistory(1);
            event.consume();
            break;
        default:
            hasStartedHistoryNavigation = false;
            break;
        }
    }

    /**
     * Handles the current History pointer based on text change.
     */
    private void handleTextChange() {
        if (!hasStartedHistoryNavigation) {
            currentHistoryPointer = getFilteredHistory().size();
        }
    }

    /**
     * Navigates through the filtered command history based on the given offset.
     *
     * @param offset The offset used to navigate the command history.
     */
    private void navigateCommandHistory(int offset) {
        List<String> history = getFilteredHistory();

        if (history.isEmpty()) {
            return;
        }

        if (!hasStartedHistoryNavigation) {
            currentHistoryPointer = history.size();
        }

        int newHistoryPointer = currentHistoryPointer + offset;

        if (newHistoryPointer == history.size()) {
            commandTextField.setText(initialInput);
        } else if (newHistoryPointer < 0 || newHistoryPointer > history.size()) {
            return;
        }

        currentHistoryPointer = newHistoryPointer;
        if (newHistoryPointer == history.size()) {
            commandTextField.setText(initialInput);
        } else {
            commandTextField.setText(history.get(currentHistoryPointer));
        }

        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Returns a filtered list of command history based on the current input.
     *
     * @return Filtered list of command history.
     */
    private List<String> getFilteredHistory() {
        // If navigation has started, filter history based on initial input
        if (hasStartedHistoryNavigation) {
            return commandHistory.stream()
                    .filter(command -> command.startsWith(initialInput))
                    .collect(Collectors.toList());
        }

        // If text field is empty, return full command history
        if (commandTextField.getText().isEmpty()) {
            return new ArrayList<>(commandHistory);
        }

        // Otherwise, filter history based on text field input
        return commandHistory.stream()
                .filter(command -> command.startsWith(commandTextField.getText()))
                .collect(Collectors.toList());
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}
