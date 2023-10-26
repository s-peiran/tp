package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.AddContactToMeetingCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.Model.ModeType;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.ContactUtil;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;


public class AddressBookParserTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        setModeToContacts();
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact), model);
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_viewContact() throws Exception {
        setModeToContacts();
        ViewContactCommand expectedCommand = new ViewContactCommand(Index.fromOneBased(1));
        String userInput = ViewContactCommand.COMMAND_WORD + " -id1";
        ViewContactCommand actualCommand = (ViewContactCommand) parser.parseCommand(userInput, model);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_addMeeting() throws Exception {
        setModeToMeetings();
        Meeting meeting = new MeetingBuilder().build();
        AddMeetingCommand expectedCommand = new AddMeetingCommand(meeting);
        String userInput = AddMeetingCommand.COMMAND_WORD
            + " -title" + MeetingBuilder.DEFAULT_TITLE
            + " -time" + MeetingBuilder.DEFAULT_TIME
            + " -place" + MeetingBuilder.DEFAULT_PLACE
            + " -desc" + MeetingBuilder.DEFAULT_DESCRIPTION;
        AddMeetingCommand actualCommand = (AddMeetingCommand) parser.parseCommand(userInput, model);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_viewMeeting() throws Exception {
        setModeToMeetings();
        ViewMeetingCommand expectedCommand = new ViewMeetingCommand(Index.fromOneBased(1));
        String userInput = ViewMeetingCommand.COMMAND_WORD + " -id1";
        ViewMeetingCommand actualCommand = (ViewMeetingCommand) parser.parseCommand(userInput, model);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_addContactToMeeting() throws Exception {
        setModeToMeetings();
        Meeting meeting = new MeetingBuilder().build();
        Contact contact = new ContactBuilder().build();
        String userInput = "add contact -n " + contact.getNameString()
            + " -title" + meeting.getTitleString();
        AddContactToMeetingCommand expectedCommand = new AddContactToMeetingCommand(
            meeting.getTitleString(), contact.getNameString());
        AddContactToMeetingCommand actualCommand = (AddContactToMeetingCommand) parser.parseCommand(userInput, model);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, model) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " -id 3", model) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        setModeToContacts();
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
            DeleteContactCommand.COMMAND_WORD + " -id " + INDEX_FIRST.getOneBased(), model);
        assertEquals(new DeleteContactCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editContact() throws Exception {
        setModeToContacts();
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        String input = EditContactCommand.COMMAND_WORD + " -id " + INDEX_FIRST.getOneBased() + " "
            + ContactUtil.getEditContactDescriptorDetails(descriptor);
        EditContactCommand command = (EditContactCommand) parser.parseCommand(input, model);

        assertEquals(new EditContactCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, model) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " -id 3", model) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findContact() throws Exception {
        setModeToContacts();
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String input = FindContactCommand.COMMAND_WORD + " -k " + keywords.stream().collect(Collectors.joining(" "));
        FindContactCommand command = (FindContactCommand) parser.parseCommand(input, model);

        assertEquals(new FindContactCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, model) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " -id 3", model) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD, model) instanceof ListContactCommand);
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD + " -id 3", model)
                instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_mode() throws Exception {
        assertTrue(parser.parseCommand(ModeCommand.COMMAND_WORD, model) instanceof ModeCommand);
        assertTrue(parser.parseCommand(ModeCommand.COMMAND_WORD + " -id 3", model)
                instanceof ModeCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", model));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand", model));
    }

    @Test
    public void parseCommand_wrongMode_throwsParseException() {
        setModeToContacts();
        String userInput = AddMeetingCommand.COMMAND_WORD
                + " -title" + MeetingBuilder.DEFAULT_TITLE
                + " -time" + MeetingBuilder.DEFAULT_TIME
                + " -place" + MeetingBuilder.DEFAULT_PLACE
                + " -desc" + MeetingBuilder.DEFAULT_DESCRIPTION;
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddContactCommand.MESSAGE_USAGE), () -> parser.parseCommand(userInput, model));
    }

    private void setModeToContacts() {
        if (model.getMode() != ModeType.CONTACTS) {
            model.changeMode();
        }
    }

    private void setModeToMeetings() {
        if (model.getMode() != ModeType.MEETINGS) {
            model.changeMode();
        }
    }
}
