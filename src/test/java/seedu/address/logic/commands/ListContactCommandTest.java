package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalContactsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.ListType;
import seedu.address.logic.parser.ListContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactCommand.
 */
public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() throws ParseException {
        CommandResult expectedCommandResult = new CommandResult(ListContactCommand.MESSAGE_SUCCESS,
            ListType.CONTACTS);
        ListContactCommand actualCommand = new ListContactCommandParser().parse("list");
        assertCommandSuccess(actualCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() throws ParseException {
        CommandResult expectedCommandResult = new CommandResult(ListContactCommand.MESSAGE_SUCCESS,
            ListType.CONTACTS);
        ListContactCommand actualCommand = new ListContactCommandParser().parse("list");
        showContactAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(actualCommand, model, expectedCommandResult, expectedModel);
    }
}
