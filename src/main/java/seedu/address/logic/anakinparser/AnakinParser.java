package seedu.address.logic.anakinparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.anakincommands.AnakinCdCommand;
import seedu.address.logic.anakincommands.AnakinCommand;
import seedu.address.logic.anakincommands.AnakinDelCardCommand;
import seedu.address.logic.anakincommands.AnakinDelDeckCommand;
import seedu.address.logic.anakincommands.AnakinEditCardCommand;
import seedu.address.logic.anakincommands.AnakinEditDeckCommand;
import seedu.address.logic.anakincommands.AnakinExitCommand;
import seedu.address.logic.anakincommands.AnakinHelpCommand;
import seedu.address.logic.anakincommands.AnakinNewCardCommand;
import seedu.address.logic.anakincommands.AnakinNewDeckCommand;
import seedu.address.logic.anakincommands.AnakinSortCommand;
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
    public AnakinCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnakinHelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AnakinNewDeckCommand.COMMAND_WORD:
            return new AnakinNewDeckCommandParser().parse(arguments);

        case AnakinEditDeckCommand.COMMAND_WORD:
            return new AnakinEditDeckCommandParser().parse(arguments);

        case AnakinDelDeckCommand.COMMAND_WORD:
            return new AnakinDelDeckCommandParser().parse(arguments);

        case AnakinNewCardCommand.COMMAND_WORD:
            return new AnakinNewCardCommandParser().parse(arguments);

        case AnakinEditCardCommand.COMMAND_WORD:
            return new AnakinEditCardCommandParser().parse(arguments);

        case AnakinDelCardCommand.COMMAND_WORD:
            return new AnakinDelCardCommandParser().parse(arguments);

        case AnakinCdCommand.COMMAND_WORD:
            return new AnakinCdCommandParser().parse(arguments);

        case AnakinSortCommand.COMMAND_WORD:
            return new AnakinSortCommand();

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

        case AnakinExitCommand.COMMAND_WORD:
            return new AnakinExitCommand();

        case AnakinHelpCommand.COMMAND_WORD:
            return new AnakinHelpCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
