package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ModeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.ListType;
import seedu.address.model.Model;
import seedu.address.model.Model.ModeType;
import seedu.address.model.ModelManager;

public class ModeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_mode_success() {
        // Ensure that both models are initialized in contacts mode
        assertEquals(model.getMode(), ModeType.CONTACTS);
        assertEquals(expectedModel.getMode(), ModeType.CONTACTS);

        expectedModel.changeMode();
        CommandResult expectedCommandResult =
                new CommandResult(String.format(MESSAGE_SUCCESS, "MEETINGS"), ListType.MEETINGS);

        // Test toggling from contacts to meeting
        assertCommandSuccess(new ModeCommand(), model, expectedCommandResult, expectedModel);

        expectedModel.changeMode();
        expectedCommandResult =
                new CommandResult(String.format(MESSAGE_SUCCESS, "CONTACTS"), ListType.CONTACTS);

        // Test toggling from meetings to contacts
        assertCommandSuccess(new ModeCommand(), model, expectedCommandResult, expectedModel);
    }
}
