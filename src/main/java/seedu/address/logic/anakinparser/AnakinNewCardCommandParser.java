package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.AnakinMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import java.util.stream.Stream;

import seedu.address.logic.anakincommands.AnakinNewCardCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinQuestion;

/**
 * Parses input arguments and creates a new AnakinNewCardCommand object
 */
public class AnakinNewCardCommandParser implements AnakinParserInterface<AnakinNewCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AnakinNewCardCommand
     * and returns an AnakinNewCardCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnakinNewCardCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ANSWER, PREFIX_QUESTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ANSWER, PREFIX_QUESTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AnakinNewCardCommand.MESSAGE_USAGE));
        }
        AnakinQuestion question = AnakinParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        AnakinAnswer answer = AnakinParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());

        AnakinCard card = new AnakinCard(question, answer);

        return new AnakinNewCardCommand(card);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
