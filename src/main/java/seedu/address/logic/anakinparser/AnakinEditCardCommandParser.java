package seedu.address.logic.anakinparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.anakincommands.AnakinEditCardCommand;
import seedu.address.logic.anakincommands.AnakinEditCardCommand.EditCardDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCardCommand object
 */
public class AnakinEditCardCommandParser implements AnakinParserInterface<AnakinEditCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCardCommand
     * and returns an EditCardCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnakinEditCardCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_ANSWER);

        Index index;

        try {
            index = AnakinParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AnakinEditCardCommand.MESSAGE_USAGE), pe);
        }

        EditCardDescriptor editCardDescriptor = new EditCardDescriptor();
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            editCardDescriptor.setQuestion(AnakinParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            editCardDescriptor.setAnswer(AnakinParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get()));
        }
        if (!editCardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AnakinEditCardCommand.MESSAGE_NOT_EDITED);
        }

        return new AnakinEditCardCommand(index, editCardDescriptor);
    }

}

