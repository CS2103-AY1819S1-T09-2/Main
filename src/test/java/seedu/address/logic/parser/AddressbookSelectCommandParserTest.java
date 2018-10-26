package seedu.address.logic.parser;

import static seedu.address.commons.core.AddressbookMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddressbookCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AddressbookCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.AddressbookTypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.Test;

import seedu.address.logic.commands.SelectCommand;

/**
 * Test scope: similar to {@code AddressbookDeleteCommandParserTest}.
 *
 * @see AddressbookDeleteCommandParserTest
 */
public class AddressbookSelectCommandParserTest {

    private SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, "1", new SelectCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }
}
