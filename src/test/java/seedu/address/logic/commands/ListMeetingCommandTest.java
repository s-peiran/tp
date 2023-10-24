package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalMeetingsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.ListType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMeetingCommand.
 */
public class ListMeetingCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMeetingsAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                ListType.MEETINGS);
        assertCommandSuccess(new ListMeetingCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandResult expectedCommandResult = new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                ListType.MEETINGS);
        showMeetingAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListMeetingCommand(), model, expectedCommandResult, expectedModel);
    }
}
