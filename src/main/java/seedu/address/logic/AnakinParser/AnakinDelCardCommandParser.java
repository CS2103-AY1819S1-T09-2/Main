package seedu.address.logic.AnakinParser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnakinCommands.AnakinDelCardCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AnakinDelCardCommandParser implements AnakinParserInterface<AnakinDelCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AnakinDelDeckCommand
     * and returns an AnakinDelDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnakinDelCardCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AnakinDelCardCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinDelCardCommand.MESSAGE_USAGE), pe);
        }
    }

}
