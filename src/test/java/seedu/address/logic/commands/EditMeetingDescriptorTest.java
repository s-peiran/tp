package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHI;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ENG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CHI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_CHI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CHI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CHI;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(DESC_ENG);
        assertTrue(DESC_ENG.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ENG.equals(DESC_ENG));

        // null -> returns false
        assertFalse(DESC_ENG.equals(null));

        // different types -> returns false
        assertFalse(DESC_ENG.equals(5));

        // different values -> returns false
        assertFalse(DESC_ENG.equals(DESC_CHI));

        // different title -> returns false
        EditMeetingDescriptor editedEng = new EditMeetingDescriptorBuilder(DESC_ENG).withTitle(VALID_TITLE_CHI).build();
        assertFalse(DESC_ENG.equals(editedEng));

        // different Time -> returns false
        editedEng = new EditMeetingDescriptorBuilder(DESC_ENG).withTime(VALID_TIME_CHI).build();
        assertFalse(DESC_ENG.equals(editedEng));

        // different place -> returns false
        editedEng = new EditMeetingDescriptorBuilder(DESC_ENG).withPlace(VALID_PLACE_CHI).build();
        assertFalse(DESC_ENG.equals(editedEng));

        // different description -> returns false
        editedEng = new EditMeetingDescriptorBuilder(DESC_ENG).withDescription(VALID_DESCRIPTION_CHI).build();
        assertFalse(DESC_ENG.equals(editedEng));
    }

    @Test
    public void toStringMethod() {
        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        String expected = EditMeetingDescriptor.class.getCanonicalName() + "{title="
                + editMeetingDescriptor.getTitle().orElse(null) + ", time="
                + editMeetingDescriptor.getTime().orElse(null) + ", place="
                + editMeetingDescriptor.getPlace().orElse(null) + ", description="
                + editMeetingDescriptor.getDescription().orElse(null) + "}";
        assertEquals(expected, editMeetingDescriptor.toString());
    }
}
