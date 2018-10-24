package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.anakincommands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.Deck;

/**
 * Adds a person to the address book.
 */
public class NewDeckCommand extends Command {

    public static final String COMMAND_WORD = "newdeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Create a new deck inside Anakin. "
            + "Parameters: "
            + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Deck 1";

    public static final String MESSAGE_SUCCESS = "New deck added: %1$s";
    public static final String MESSAGE_DUPLICATE_DECK = "This deck already exists in Anakin";

    private final Deck toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public NewDeckCommand(Deck Deck) {
        requireNonNull(Deck);
        toAdd = Deck;
    }

    @Override
    public CommandResult execute(AnakinModel anakinModel, CommandHistory history) throws CommandException {
        requireNonNull(anakinModel);

        if (anakinModel.hasDeck(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DECK);
        }

        anakinModel.addDeck(toAdd);
        anakinModel.commitAnakin();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NewDeckCommand // instanceof handles nulls
                && toAdd.equals(((NewDeckCommand) other).toAdd));
    }
}
