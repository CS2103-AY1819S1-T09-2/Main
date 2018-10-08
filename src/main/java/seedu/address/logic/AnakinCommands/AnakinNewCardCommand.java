package seedu.address.logic.AnakinCommands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinCard;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Edits the details of an existing card in a deck.
 */
public class AnakinNewCardCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "newcard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new card to the current deck"
            + "Parameters: "
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_ANSWER + "ANSWER]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_QUESTION + "What does Socrates know?"
            + PREFIX_ANSWER + "Nothing";

    public static final String MESSAGE_NEW_CARD_SUCCESS = "New card added: %1$s";
    public static final String MESSAGE_DUPLICATE_CARD = "This card already exists in the deck.";

    private final AnakinCard toAdd;

    /**
     * Creates a newcard command to add the specified {@code AnakinCard}
     *
     * @param card
     */
    public AnakinNewCardCommand(AnakinCard card) {
        requireNonNull(card);
        this.toAdd = card;
    }

    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (model.hasCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }
        model.addCard(toAdd);
        model.commitAnakin();
        return new CommandResult(String.format(MESSAGE_NEW_CARD_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinNewCardCommand // instanceof handles nulls
                && toAdd.equals(((AnakinNewCardCommand) other).toAdd));
    }
}
