package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.AnakinCdCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AnakinCdCommandParser implements AnakinParserInterface<AnakinCdCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AnakinCdCommand
     * and returns an AnakinCdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public AnakinCdCommand parse(String args) throws ParseException {


        try {
            if (args.equals(AnakinCdCommand.EXIT_DECK_ARGS)) {
                return new AnakinCdCommand();
            } else {
                Index index = AnakinParserUtil.parseIndex(args);
                return new AnakinCdCommand(index);
            }


        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinCdCommand.MESSAGE_USAGE), pe);
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
