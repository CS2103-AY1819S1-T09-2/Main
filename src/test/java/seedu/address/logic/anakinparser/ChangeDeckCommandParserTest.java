package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.AddressbookMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.anakinparser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.anakinparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DECK;

import org.junit.Test;

import seedu.address.logic.anakincommands.ChangeDeckCommand;
import seedu.address.logic.parser.AddressbookDeleteCommandParserTest;

/**
 * Test scope: similar to {@code AddressbookDeleteCommandParserTest}.
 *
 * @see AddressbookDeleteCommandParserTest
 */
public class ChangeDeckCommandParserTest {

    private ChangeDeckCommandParser parser = new ChangeDeckCommandParser();

    @Test
    public void parse_validArgs_returnsCdCommand() {
        assertParseSuccess(parser, "1", new ChangeDeckCommand(INDEX_FIRST_DECK));
    }

    @Test
    public void parse_validExit_returnsCdCommand() {
        assertParseSuccess(parser, ChangeDeckCommand.EXIT_DECK_ARGS, new ChangeDeckCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeDeckCommand.MESSAGE_USAGE));
    }
}
