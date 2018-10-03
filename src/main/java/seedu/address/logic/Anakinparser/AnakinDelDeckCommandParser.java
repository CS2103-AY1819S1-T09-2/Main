package seedu.address.logic.Anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Anakin_commands.Anakin_DelDeckCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AnakinDelDeckCommandParser implements AnakinParserInterface<Anakin_DelDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the Anakin_DelDeckCommand
     * and returns an Anakin_DelDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Anakin_DelDeckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new Anakin_DelDeckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Anakin_DelDeckCommand.MESSAGE_USAGE), pe);
        }
    }

}
