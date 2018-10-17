package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.AnakinSelectCardCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectCommand object
 */
public class AnakinSelectCardCommandParser implements AnakinParserInterface<AnakinSelectCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AnakinSelectCommand
     * and returns an AnakinSelectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnakinSelectCardCommand parse(String args) throws ParseException {
        try {
            Index index = AnakinParserUtil.parseIndex(args);
            return new AnakinSelectCardCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinSelectCardCommand.MESSAGE_USAGE), pe);
        }
    }
}
