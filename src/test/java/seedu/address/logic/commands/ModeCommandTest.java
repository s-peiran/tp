package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ModeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.AppState;
import seedu.address.ui.AppState.ListType;
import seedu.address.ui.AppState.ModeType;

public class ModeCommandTest {
    private Model model = new ModelManager();
    private AppState appState;

    @BeforeEach
    public void setUp() {
        appState = AppState.getInstance();
    }

    @Test
    public void execute_mode_success() {
        // Ensure that the AppState is initialized in contacts mode
        assertEquals(appState.getModeType(), ModeType.CONTACTS);
        assertEquals(appState.getListType(), ListType.CONTACTS);

        // Test toggling from contacts to meeting
        CommandResult expectedCommandResult =
                new CommandResult(String.format(MESSAGE_SUCCESS, "MEETINGS"));

        assertCommandSuccess(new ModeCommand(), model, expectedCommandResult, model);

        // Test toggling from meetings to contacts
        expectedCommandResult =
                new CommandResult(String.format(MESSAGE_SUCCESS, "CONTACTS"));

        assertCommandSuccess(new ModeCommand(), model, expectedCommandResult, model);
    }
}
