package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.INVALID_ANSWER;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.INVALID_QUESTION;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.VALID_CARD_A_ARGS;
import static seedu.address.logic.commands.anakincommands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import org.junit.Test;

import seedu.address.logic.anakincommands.AnakinNewCardCommand;
import seedu.address.logic.anakinparser.AnakinNewCardCommandParser;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.testutil.AnakinCardBuilder;

public class AnakinNewCardCommandParserTest {
    private AnakinNewCardCommandParser parser = new AnakinNewCardCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AnakinCard expectedAnakinCard = new AnakinCardBuilder().
                withQuestion(VALID_QUESTION_A).
                withAnswer(VALID_ANSWER_A).build();

        // clean
        System.out.println("Valid args: " + VALID_CARD_A_ARGS);
        assertParseSuccess(parser,  VALID_CARD_A_ARGS ,
                new AnakinNewCardCommand(expectedAnakinCard));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_CARD_A_ARGS ,
                new AnakinNewCardCommand(expectedAnakinCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AnakinNewCardCommand.MESSAGE_USAGE);

        // No question
        assertParseFailure(parser, PREFIX_ANSWER + VALID_ANSWER_A, expectedMessage);

        // No answer
        assertParseFailure(parser, PREFIX_QUESTION + VALID_QUESTION_A, expectedMessage);

        // No space between args
        assertParseFailure(parser, PREFIX_QUESTION + VALID_QUESTION_A +
                PREFIX_QUESTION + VALID_QUESTION_A, expectedMessage);

        // No argument
        assertParseFailure(parser, "", expectedMessage);

        // Blank
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AnakinNewCardCommand.MESSAGE_USAGE);

        // invalid question
        assertParseFailure(parser, PREFIX_QUESTION + INVALID_QUESTION, expectedMessage);

        // invalid answer
        assertParseFailure(parser, PREFIX_ANSWER + INVALID_ANSWER, expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_CARD_A_ARGS,
                expectedMessage);
    }
}
