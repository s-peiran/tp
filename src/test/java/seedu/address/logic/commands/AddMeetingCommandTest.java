package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSucessful() throws CommandException {
        Meeting validMeeting = new MeetingBuilder().build();
        ModelStubEmpty modelStubEmpty = new ModelStubEmpty();

        CommandResult commandResult = new AddMeetingCommand(validMeeting).execute(modelStubEmpty);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, Messages.formatMeeting(validMeeting)),
                commandResult.getFeedbackToUser());
        assertEquals(validMeeting, modelStubEmpty.getMeeting());
    }

    @Test
    public void equals() {
        Meeting validMeeting1 = new MeetingBuilder().build();
        Meeting validMeeting2 = new MeetingBuilder().build();

        AddMeetingCommand addM1Command = new AddMeetingCommand(validMeeting1);
        AddMeetingCommand addM2Command = new AddMeetingCommand(validMeeting2);

        // same object -> returns true
        assertTrue(addM1Command.equals(addM1Command));

        // same fields except id -> returns true
        assertTrue(addM1Command.equals(addM2Command));

        // different type -> returns false
        assertFalse(addM1Command.equals(1));

        // null -> returns false
        assertFalse(addM1Command.equals(null));
    }

    @Test

    public void toStringMethod() {
        Meeting validMeeting = new MeetingBuilder().build();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(validMeeting);
        String expected = AddMeetingCommand.class.getCanonicalName() + "{toAdd=" + validMeeting + "}";

        String actual = addMeetingCommand.toString();

        assertEquals(expected, actual);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubEmpty extends AddMeetingCommandTest.ModelStub {
        private Meeting meeting;

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        public Meeting getMeeting() {
            return meeting;
        }
    }
}
