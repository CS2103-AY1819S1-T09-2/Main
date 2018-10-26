package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.AddressbookMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.ExportDeckCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportDeckCommand object
 */
public class ExportDeckCommandParser implements ParserInterface<ExportDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportDeckCommand
     * and returns an ExportDeckCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportDeckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ExportDeckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ExportDeckCommand.MESSAGE_USAGE), pe);
        }
    }

}
