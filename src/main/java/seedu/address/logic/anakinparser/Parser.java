package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.anakincommands.ChangeDeckCommand;
import seedu.address.logic.anakincommands.Command;
import seedu.address.logic.anakincommands.DelCardCommand;
import seedu.address.logic.anakincommands.DelDeckCommand;
import seedu.address.logic.anakincommands.EditCardCommand;
import seedu.address.logic.anakincommands.EditDeckCommand;
import seedu.address.logic.anakincommands.ExitCommand;
import seedu.address.logic.anakincommands.HelpCommand;
import seedu.address.logic.anakincommands.NewCardCommand;
import seedu.address.logic.anakincommands.NewDeckCommand;
import seedu.address.logic.anakincommands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class AnakinParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case NewDeckCommand.COMMAND_WORD:
            return new AnakinNewDeckCommandParser().parse(arguments);

        case EditDeckCommand.COMMAND_WORD:
            return new AnakinEditDeckCommandParser().parse(arguments);

        case DelDeckCommand.COMMAND_WORD:
            return new AnakinDelDeckCommandParser().parse(arguments);

        case NewCardCommand.COMMAND_WORD:
            return new AnakinNewCardCommandParser().parse(arguments);

        case EditCardCommand.COMMAND_WORD:
            return new AnakinEditCardCommandParser().parse(arguments);

        case DelCardCommand.COMMAND_WORD:
            return new AnakinDelCardCommandParser().parse(arguments);

        case ChangeDeckCommand.COMMAND_WORD:
            return new AnakinCdCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommand();

        // TO DO
            /*
        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();
*/

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
