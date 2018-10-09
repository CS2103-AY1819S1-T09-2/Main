package seedu.address.logic.AnakinCommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.AnakinMessages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinCard;


/**
 * Edits the details of an existing card in a deck.
 */
public class AnakinDelCardCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "delcard";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the card identified by the index number in the current deck"
            + "Parameters: INDEX (must be a positive integer)"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CARD_SUCCESS = "Deleted card: %1$s";

    private final Index targetIndex;

    /**
     * Creates a delcard command to delete the {@code AnakinCard} at the specified index
     *
     * @param targetIndex
     */
    public AnakinDelCardCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<AnakinCard> lastShownList = model.getFilteredCardList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(AnakinMessages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }
        AnakinCard cardToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCard(cardToDelete);
        model.commitAnakin();
        return new CommandResult(String.format(MESSAGE_DELETE_CARD_SUCCESS, cardToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinDelCardCommand // instanceof handles nulls
                && targetIndex.equals(((AnakinDelCardCommand) other).targetIndex));
    }
}
