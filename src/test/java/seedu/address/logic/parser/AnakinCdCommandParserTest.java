package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_FIRST_DECK;

import org.junit.Test;

import seedu.address.logic.anakincommands.AnakinCdCommand;
import seedu.address.logic.anakinparser.AnakinCdCommandParser;

/**
 * Test scope: similar to {@code DeleteCommandParserTest}.
 * @see DeleteCommandParserTest
 */
public class AnakinCdCommandParserTest {

    private AnakinCdCommandParser parser = new AnakinCdCommandParser();

    @Test
    public void parse_validArgs_returnsCdCommand() {
        assertParseSuccess(parser, "1", new AnakinCdCommand(INDEX_FIRST_DECK));
    }

    @Test
    public void parse_validExit_returnsCdCommand() {
        assertParseSuccess(parser, AnakinCdCommand.EXIT_DECK_ARGS, new AnakinCdCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinCdCommand.MESSAGE_USAGE));
    }
}
