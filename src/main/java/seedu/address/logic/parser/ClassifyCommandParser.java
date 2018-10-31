package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ClassifyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.Performance;

/**
 * Parses input arguments and create a ClassifyCommand object
 */
public class ClassifyCommandParser implements ParserInterface<ClassifyCommand> {
    public ClassifyCommand parse(String args) throws ParseException {
        try {
            Performance difficulty = ParserUtil.parsePerformance(args);
            return new ClassifyCommand(difficulty);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassifyCommand.MESSAGE_USAGE), pe);
        }
    }
}
