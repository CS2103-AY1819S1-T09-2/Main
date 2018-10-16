package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.AnakinDelDeckCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AnakinDelDeckCommandParser implements AnakinParserInterface<AnakinDelDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AnakinDelDeckCommand
     * and returns an AnakinDelDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnakinDelDeckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AnakinDelDeckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinDelDeckCommand.MESSAGE_USAGE), pe);
        }
    }

}
