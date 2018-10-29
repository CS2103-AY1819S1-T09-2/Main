package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Paths;
import java.util.Arrays;

import seedu.address.logic.commands.ImportDeckCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.CardQuestionContainsKeywordsPredicate;
import seedu.address.model.deck.DeckNameContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportDeckCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        String targetpath = keywords[0];

        return new ImportDeckCommand(targetpath);
    }

}
