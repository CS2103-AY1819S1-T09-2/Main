package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DECK_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.ImportDeckCommand;

public class ImportDeckCommandParserTest {
    private ImportDeckCommandParser parser = new ImportDeckCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // clean
        assertParseSuccess(parser, VALID_NAME_DECK_A,
            new ImportDeckCommand(VALID_NAME_DECK_A));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_NAME_DECK_A,
            new ImportDeckCommand(VALID_NAME_DECK_A));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ImportDeckCommand.MESSAGE_USAGE);

        // No argument
        assertParseFailure(parser, "", expectedMessage);
    }

    //    @Test
    //    public void parse_invalidValue_failure() {
    //        // invalid name
    //        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
    //            ImportDeckCommand.MESSAGE_USAGE);
    //        assertParseFailure(parser, "n/" + INVALID_DECK_NAME_ARGS, expectedMessage);
    //
    //        // non-empty preamble
    //        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_NAME_DECK_A,
    //            String.format(MESSAGE_INVALID_COMMAND_FORMAT,
    //                ImportDeckCommand.MESSAGE_USAGE));
    //    }
}
