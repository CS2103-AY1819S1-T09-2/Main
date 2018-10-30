package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DECK;

import org.junit.Test;

import seedu.address.logic.commands.ExportDeckCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ExportDeckCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ExportDeckCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ExportDeckCommandParserTest {

    private ExportDeckCommandParser parser = new ExportDeckCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ExportDeckCommand(INDEX_FIRST_DECK));
    }

    @Test
    public void parse_blankArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportDeckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportDeckCommand.MESSAGE_USAGE));
    }
}
