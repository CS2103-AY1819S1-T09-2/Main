package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;

import seedu.address.logic.anakincommands.AnakinCommand;
import seedu.address.logic.anakinparser.AnakinParserInterface;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class AnakinCommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(AnakinParserInterface parser, String userInput, AnakinCommand expectedCommand) {
        try {
            AnakinCommand command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(AnakinParserInterface parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
