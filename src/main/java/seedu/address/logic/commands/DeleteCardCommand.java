package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CARD_LEVEL_OPERATION;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_INSIDE_DECK;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.anakinexceptions.DeckNotFoundException;


/**
 * Edits the details of an existing card in a deck.
 */
public class DeleteCardCommand extends Command {

    public static final String COMMAND_WORD = "delcard";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the card identified by the index number in the current deck"
        + "Parameters: INDEX (must be a positive integer)"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CARD_SUCCESS = "Deleted card: %1$s";

    private final Index targetIndex;

    /**
     * Creates a delcard command to delete the {@code Card} at the specified index
     *
     * @param targetIndex
     */
    public DeleteCardCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.isInsideDeck()) {
            throw new CommandException(MESSAGE_INVALID_CARD_LEVEL_OPERATION);
        }

        List<Card> lastShownList = model.getFilteredCardList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }
        Card cardToDelete = lastShownList.get(targetIndex.getZeroBased());
        try {
            model.deleteCard(cardToDelete);
            model.commitAnakin();
        } catch (DeckNotFoundException e) {
            throw new CommandException(MESSAGE_NOT_INSIDE_DECK);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_CARD_SUCCESS, cardToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCardCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteCardCommand) other).targetIndex));
    }
}
