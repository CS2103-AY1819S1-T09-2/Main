package seedu.address.logic.AnakinCommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AnakinModel;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Deletes a deck identified using it's displayed index from Anakin.
 */
public class AnakinDelDeckCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "deldeck";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the deck identified by the index number used in the displayed deck list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DECK_SUCCESS = "Deleted Deck: %1$s";

    private final Index targetIndex;

    public AnakinDelDeckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(AnakinModel anakinModel, CommandHistory history) throws CommandException {
        requireNonNull(anakinModel);
        List<AnakinDeck> lastShownList = anakinModel.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        AnakinDeck deckToDelete = lastShownList.get(targetIndex.getZeroBased());
        anakinModel.deleteDeck(deckToDelete);
        anakinModel.commitAnakin();
        return new CommandResult(String.format(MESSAGE_DELETE_DECK_SUCCESS, deckToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnakinDelDeckCommand // instanceof handles nulls
                && targetIndex.equals(((AnakinDelDeckCommand) other).targetIndex)); // state check
    }
}

