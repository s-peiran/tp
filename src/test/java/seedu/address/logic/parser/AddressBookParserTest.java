package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.AddContactToMeetingCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ModeCommand;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.ContactUtil;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.ui.AppState;
import seedu.address.ui.AppState.ModeType;

public class AddressBookParserTest {

    private AppState appState = AppState.getInstance();

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        setModeToContacts();
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_viewContact() throws Exception {
        setModeToContacts();
        ViewContactCommand expectedCommand = new ViewContactCommand(Index.fromOneBased(1));
        String userInput = ViewContactCommand.COMMAND_WORD + " 1";
        ViewContactCommand actualCommand = (ViewContactCommand) parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_addMeeting() throws Exception {
        setModeToMeetings();
        Meeting meeting = new MeetingBuilder().build();
        AddMeetingCommand expectedCommand = new AddMeetingCommand(meeting);
        String userInput = AddMeetingCommand.COMMAND_WORD
            + " " + CliSyntax.PREFIX_TITLE + " " + MeetingBuilder.DEFAULT_TITLE
            + " " + CliSyntax.PREFIX_TIME + " " + MeetingBuilder.DEFAULT_TIME
            + " " + CliSyntax.PREFIX_PLACE + " " + MeetingBuilder.DEFAULT_PLACE
            + " " + CliSyntax.PREFIX_DESCRIPTION + " " + MeetingBuilder.DEFAULT_DESCRIPTION;
        AddMeetingCommand actualCommand = (AddMeetingCommand) parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_viewMeeting() throws Exception {
        setModeToMeetings();
        ViewMeetingCommand expectedCommand = new ViewMeetingCommand(Index.fromOneBased(1));
        String userInput = ViewMeetingCommand.COMMAND_WORD + " 1";
        ViewMeetingCommand actualCommand = (ViewMeetingCommand) parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_addContactToMeeting() throws Exception {
        setModeToMeetings();
        Meeting meeting = new MeetingBuilder().build();
        Contact contact = new ContactBuilder().build();
        String userInput = AddContactToMeetingCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME
                + " " + contact.getNameString() + " " + CliSyntax.PREFIX_TITLE + " " + meeting.getTitleString();
        AddContactToMeetingCommand expectedCommand = new AddContactToMeetingCommand(
            meeting.getTitleString(), contact.getNameString());
        AddContactToMeetingCommand actualCommand = (AddContactToMeetingCommand) parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(
            ClearCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        setModeToContacts();
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
            DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteMeeting() throws Exception {
        setModeToMeetings();
        DeleteMeetingCommand command = (DeleteMeetingCommand) parser.parseCommand(
            DeleteMeetingCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteMeetingCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editContact() throws Exception {
        setModeToContacts();
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        String input = EditContactCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased() + " "
            + ContactUtil.getEditContactDescriptorDetails(descriptor);
        EditContactCommand command = (EditContactCommand) parser.parseCommand(input);

        assertEquals(new EditContactCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(
                ExitCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(
                HelpCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD) instanceof ListContactCommand);
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + " 3")
                instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_mode() throws Exception {
        assertTrue(parser.parseCommand(ModeCommand.COMMAND_WORD) instanceof ModeCommand);
        assertTrue(parser.parseCommand(ModeCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + " 3")
                instanceof ModeCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_COMMAND, "unknownCommand"), ()
            -> parser.parseCommand("unknownCommand"));
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
                        AddContactCommand.MESSAGE_USAGE), () -> parser.parseCommand(userInput));
    }

    private void setModeToContacts() {
        if (appState.getMode() != ModeType.CONTACTS) {
            appState.changeMode();
        }
    }

    private void setModeToMeetings() {
        if (appState.getMode() != ModeType.MEETINGS) {
            appState.changeMode();
        }
    }
}
