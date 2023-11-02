package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TITLE = new Prefix("title/");
    public static final Prefix PREFIX_TIME = new Prefix("time/");
    public static final Prefix PREFIX_TIME_START = new Prefix("ts/");
    public static final Prefix PREFIX_TIME_END = new Prefix("te/");
    public static final Prefix PREFIX_PLACE = new Prefix("place/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");
    public static final Prefix PREFIX_INDEX = new Prefix("id/");
    public static final Prefix PREFIX_KEYWORD = new Prefix("k/");
    public static final Prefix PREFIX_NOTE = new Prefix("z/");
    public static final Prefix PREFIX_NOTE_CONTACT = new Prefix("c/");
    public static final Prefix PREFIX_NOTE_MEETING = new Prefix("m/");
    public static final Prefix PREFIX_NOTE_ID = new Prefix("noteid/");
}
