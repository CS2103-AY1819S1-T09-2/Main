package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AnakinCommandTestUtil.INVALID_DECK_NAME_ARGS;
import static seedu.address.logic.commands.AnakinCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.AnakinCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_DECK_NAME_A_ARGS;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_NAME_DECK_A;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.Test;

import seedu.address.logic.anakincommands.AnakinNewDeckCommand;
import seedu.address.logic.anakinparser.AnakinNewDeckCommandParser;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.testutil.AnakinDeckBuilder;

public class AnakinNewDeckCommandParserTest {
    private AnakinNewDeckCommandParser parser = new AnakinNewDeckCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AnakinDeck expectedAnakinDeck = new AnakinDeckBuilder().withName(VALID_NAME_DECK_A).build();

        // clean
        assertParseSuccess(parser, VALID_DECK_NAME_A_ARGS,
                new AnakinNewDeckCommand(expectedAnakinDeck));

        // whitespace only preamble
        assertParseSuccess(parser,  PREAMBLE_WHITESPACE + VALID_DECK_NAME_A_ARGS,
                new AnakinNewDeckCommand(expectedAnakinDeck));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AnakinNewDeckCommand.MESSAGE_USAGE);

        // No argument
        assertParseFailure(parser, "", expectedMessage);

        // Blank name
        assertParseFailure(parser, PREFIX_NAME + "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AnakinNewDeckCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "n/" + INVALID_DECK_NAME_ARGS, expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_NAME_DECK_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AnakinNewDeckCommand.MESSAGE_USAGE));
    }
}
