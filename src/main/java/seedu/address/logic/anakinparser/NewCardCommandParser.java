package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import java.util.stream.Stream;

import seedu.address.logic.anakincommands.NewCardCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.anakindeck.Answer;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Question;

/**
 * Parses input arguments and creates a new NewCardCommand object
 */
public class NewCardCommandParser implements ParserInterface<NewCardCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the NewCardCommand
     * and returns an NewCardCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewCardCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ANSWER, PREFIX_QUESTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ANSWER, PREFIX_QUESTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NewCardCommand.MESSAGE_USAGE));
        }
        Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get());
        Answer answer = ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get());

        Card card = new Card(question, answer);

        return new NewCardCommand(card);
    }

}
