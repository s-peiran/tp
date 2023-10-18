package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CHI;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLACE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PLACE_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CHI;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_CHI;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CHI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_ENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CHI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_ENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_ENG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.Place;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Title;
import seedu.address.testutil.EditMeetingDescriptorBuilder;


public class EditMeetingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_ENG, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " -id 1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_ENG, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_ENG, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " -id 1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, " -id 1" + INVALID_TIME_DESC, Time.MESSAGE_CONSTRAINTS); // invalid time
        assertParseFailure(parser, " -id 1" + INVALID_PLACE_DESC, Place.MESSAGE_CONSTRAINTS); // invalid place

        // invalid time followed by valid place
        assertParseFailure(parser, " -id 1" + INVALID_TIME_DESC + INVALID_PLACE_DESC, Time.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " -id 1" + INVALID_TITLE_DESC + INVALID_TIME_DESC + VALID_PLACE_ENG
                + VALID_DESCRIPTION_ENG, Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = " -id " + targetIndex.getOneBased() + TIME_DESC_CHI
                + PLACE_DESC_ENG + DESCRIPTION_DESC_CHI + TITLE_DESC_ENG;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_ENG)
                .withTime(VALID_TIME_CHI).withPlace(VALID_PLACE_ENG).withDescription(VALID_DESCRIPTION_CHI).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD;
        String userInput = " -id " + targetIndex.getOneBased() + TITLE_DESC_ENG;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_ENG).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = " -id " + targetIndex.getOneBased() + TIME_DESC_ENG;
        descriptor = new EditMeetingDescriptorBuilder().withTime(VALID_TIME_ENG).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // place
        userInput = " -id " + targetIndex.getOneBased() + PLACE_DESC_ENG;
        descriptor = new EditMeetingDescriptorBuilder().withPlace(VALID_PLACE_ENG).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = " -id " + targetIndex.getOneBased() + DESCRIPTION_DESC_ENG;
        descriptor = new EditMeetingDescriptorBuilder().withDescription(VALID_DESCRIPTION_ENG).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST;
        String userInput = " -id " + targetIndex.getOneBased() + INVALID_TIME_DESC + TIME_DESC_CHI;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));

        // invalid followed by valid
        userInput = " -id " + targetIndex.getOneBased() + TIME_DESC_CHI + INVALID_TIME_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME));

        // mulltiple valid fields repeated
        userInput = " -id " + targetIndex.getOneBased() + TIME_DESC_CHI + DESCRIPTION_DESC_CHI + TITLE_DESC_CHI
                + TIME_DESC_ENG + TITLE_DESC_ENG;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TIME, PREFIX_TITLE));

        // multiple invalid values
        userInput = " -id " + targetIndex.getOneBased() + INVALID_TITLE_DESC + INVALID_TITLE_DESC
                + INVALID_PLACE_DESC + INVALID_PLACE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE, PREFIX_PLACE));
    }

}
