package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PlayCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PlayCommand object
 */
public class PlayCommandParser implements ParserInterface<PlayCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PlayCommand
     * and returns an PlayCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PlayCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PlayCommand.MESSAGE_USAGE), pe);
        }

        return new PlayCommand(index);
    }
}
