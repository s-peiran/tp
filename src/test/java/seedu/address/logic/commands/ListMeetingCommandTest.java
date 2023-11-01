package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalMeetingsAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ListMeetingCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
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
    public void execute_listIsNotFiltered_showsSameList() throws ParseException {
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 4));
        ListMeetingCommand actualCommand = new ListMeetingCommandParser().parse("list");
        assertCommandSuccess(actualCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() throws ParseException {
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 4));
        ListMeetingCommand actualCommand = new ListMeetingCommandParser().parse("list");
        showMeetingAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(actualCommand, model, expectedCommandResult, expectedModel);
    }
}
