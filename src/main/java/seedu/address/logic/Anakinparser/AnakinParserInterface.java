package seedu.address.logic.Anakinparser;

import seedu.address.logic.Anakin_commands.Anakin_Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Anakin_Command} of type {@code T}.
 */
public interface AnakinParserInterface<T extends Anakin_Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
