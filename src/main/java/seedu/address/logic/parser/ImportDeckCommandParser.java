package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ImportDeckCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportDeckCommand object
 */
public class ImportDeckCommandParser implements ParserInterface<ImportDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportDeckCommand
     * and returns an ImportDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportDeckCommand parse(String args) throws ParseException {
        String targetPath = args.trim();
        if (targetPath.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportDeckCommand.MESSAGE_USAGE));
        }

        return new ImportDeckCommand(targetPath);
    }

}
