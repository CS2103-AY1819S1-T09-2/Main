package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.AddressbookMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.anakincommands.CommandTestUtil.INVALID_ANSWER;
import static seedu.address.logic.anakincommands.CommandTestUtil.INVALID_QUESTION;
import static seedu.address.logic.anakincommands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.anakincommands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.anakincommands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.address.logic.anakincommands.CommandTestUtil.VALID_CARD_A_ARGS;
import static seedu.address.logic.anakincommands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.address.logic.anakinparser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.anakinparser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import org.junit.Test;

import seedu.address.logic.anakincommands.NewCardCommand;
import seedu.address.model.anakindeck.Card;
import seedu.address.testutil.CardBuilder;

public class NewCardCommandParserTest {
    private NewCardCommandParser parser = new NewCardCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Card expectedCard = new CardBuilder().withQuestion(VALID_QUESTION_A).withAnswer(VALID_ANSWER_A).build();

        // clean
        System.out.println("Valid args: " + VALID_CARD_A_ARGS);
        assertParseSuccess(parser, VALID_CARD_A_ARGS,
            new NewCardCommand(expectedCard));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_CARD_A_ARGS,
            new NewCardCommand(expectedCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            NewCardCommand.MESSAGE_USAGE);

        // No question
        assertParseFailure(parser, PREFIX_ANSWER + VALID_ANSWER_A, expectedMessage);

        // No answer
        assertParseFailure(parser, PREFIX_QUESTION + VALID_QUESTION_A, expectedMessage);

        // No space between args
        assertParseFailure(parser, PREFIX_QUESTION + VALID_QUESTION_A + PREFIX_QUESTION + VALID_QUESTION_A,
            expectedMessage);

        // No argument
        assertParseFailure(parser, "", expectedMessage);

        // Blank
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            NewCardCommand.MESSAGE_USAGE);

        // invalid question
        assertParseFailure(parser, PREFIX_QUESTION + INVALID_QUESTION, expectedMessage);

        // invalid answer
        assertParseFailure(parser, PREFIX_ANSWER + INVALID_ANSWER, expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_CARD_A_ARGS, expectedMessage);
    }
}
