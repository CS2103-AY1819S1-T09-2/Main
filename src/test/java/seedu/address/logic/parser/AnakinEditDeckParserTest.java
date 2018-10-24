package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AnakinCommandTestUtil.INVALID_DECK_NAME_ARGS;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_DECK_NAME_A_ARGS;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_DECK_NAME_B_ARGS;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_NAME_DECK_A;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_NAME_DECK_B;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnakinCommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_FIRST_DECK;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.AnakinEditDeckCommand;
import seedu.address.logic.anakincommands.AnakinEditDeckCommand.EditDeckDescriptor;
import seedu.address.logic.anakinparser.AnakinEditDeckCommandParser;
import seedu.address.model.person.Name;
import seedu.address.testutil.EditDeckDescriptorBuilder;

public class AnakinEditDeckParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinEditDeckCommand.MESSAGE_USAGE);

    private AnakinEditDeckCommandParser parser = new AnakinEditDeckCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_DECK_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AnakinEditDeckCommand.MESSAGE_DECK_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DECK_NAME_A_ARGS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DECK_NAME_A_ARGS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DECK_NAME_ARGS, Name.MESSAGE_NAME_CONSTRAINTS); // invalid name
    }

    @Test
    public void parse_repeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_DECK;
        String userInput = targetIndex.getOneBased() +
                VALID_DECK_NAME_A_ARGS + VALID_DECK_NAME_B_ARGS;

        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder()
                .withName(VALID_NAME_DECK_B).build();
        AnakinEditDeckCommand expectedCommand = new AnakinEditDeckCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_DECK;
        String userInput = targetIndex.getOneBased() +
                INVALID_DECK_NAME_ARGS + VALID_DECK_NAME_B_ARGS;
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder()
                .withName(VALID_NAME_DECK_B).build();
        AnakinEditDeckCommand expectedCommand = new AnakinEditDeckCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
