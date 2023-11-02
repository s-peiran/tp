package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.TitleContainsKeywordsPredicate;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final int VALID_CONTACT_NOTEID = 4;
    public static final int VALID_MEETING_NOTEID = 11;
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_NOTE_AMY = "Likes apples";
    public static final String VALID_NOTE_BOB = "Knee injury";
    public static final String VALID_NOTE_BENSON = "Hates tiramisu";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_TITLE_ENG = "English";
    public static final String VALID_TITLE_CHI = "Chinese";
    public static final String VALID_TIME_ENG = "01/01/2023 00:00";
    public static final String VALID_TIME_CHI = "01/01/2023 12:00";
    public static final String VALID_PLACE_ENG = "Home";
    public static final String VALID_PLACE_CHI = "School";
    public static final String VALID_DESCRIPTION_ENG = "";
    public static final String VALID_DESCRIPTION_CHI = "This is a description.";
    public static final String VALID_NOTE_ENG = "Project";
    public static final String VALID_NOTE_CHI = "Date";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NOTE_DESC_AMY = " " + PREFIX_NOTE + VALID_NOTE_AMY;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String TITLE_DESC_ENG = " " + PREFIX_TITLE + VALID_TITLE_ENG;
    public static final String TITLE_DESC_CHI = " " + PREFIX_TITLE + VALID_TITLE_CHI;
    public static final String TIME_DESC_ENG = " " + PREFIX_TIME + VALID_TIME_ENG;
    public static final String TIME_DESC_CHI = " " + PREFIX_TIME + VALID_TIME_CHI;
    public static final String PLACE_DESC_ENG = " " + PREFIX_PLACE + VALID_PLACE_ENG;
    public static final String PLACE_DESC_CHI = " " + PREFIX_PLACE + VALID_PLACE_CHI;
    public static final String DESCRIPTION_DESC_ENG = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_ENG;
    public static final String DESCRIPTION_DESC_CHI = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CHI;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "French&"; // '&' not allowed in titles
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "1/1/2023 14:00"; // wrong date format
    public static final String INVALID_PLACE_DESC = " " + PREFIX_PLACE + "Z00m@meeting"; // @ not allowed in place

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditContactCommand.EditContactDescriptor DESC_AMY;
    public static final EditContactCommand.EditContactDescriptor DESC_BOB;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_ENG;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_CHI;

    static {
        DESC_AMY = new EditContactDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_ENG = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_ENG)
                .withTime(VALID_TIME_ENG).withPlace(VALID_PLACE_ENG).withDescription(VALID_DESCRIPTION_ENG).build();
        DESC_CHI = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_CHI)
                .withTime(VALID_TIME_CHI).withPlace(VALID_PLACE_CHI).withDescription(VALID_DESCRIPTION_CHI).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            String expectedNote) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered contact list and selected contact in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredContactList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredContactList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the contact at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showContactAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredContactList().size());

        Contact contact = model.getFilteredContactList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().fullName.split("\\s+");
        model.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredContactList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the meeting at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        final String[] splitTitle = meeting.getTitle().fullTitle.split("\\s+");
        model.updateFilteredMeetingList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredMeetingList().size());
    }
}
