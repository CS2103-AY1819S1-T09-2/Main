package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.CdCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CdCommandParser implements ParserInterface<CdCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CdCommand
     * and returns an CdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public CdCommand parse(String args) throws ParseException {


        try {
            if (args.equals(CdCommand.EXIT_DECK_ARGS)) {
                return new CdCommand();
            } else {
                Index index = ParserUtil.parseIndex(args);
                return new CdCommand(index);
            }


        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
