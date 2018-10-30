package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReviewCommand object
 */
public class ReviewCommandParser implements ParserInterface<ReviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReviewCommand
     * and returns an ReviewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReviewCommand.MESSAGE_USAGE), pe);
        }

        return new ReviewCommand(index);
    }
}
