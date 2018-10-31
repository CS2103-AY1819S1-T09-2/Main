package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CURRENTLY_REVIEWING_DECK;

import java.util.List;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Card;

/**
 * Selects a card identified using it's displayed index from the address book.
 */
public class SelectCardCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Selects the card identified by the index number used in the displayed card list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_CARD_SUCCESS = "Selected Card: %1$s";

    private final Index targetIndex;

    public SelectCardCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (model.isReviewingDeck()) {
            throw new CommandException(MESSAGE_CURRENTLY_REVIEWING_DECK);
        }

        List<Card> filteredCardList = model.getFilteredCardList();

        if (targetIndex.getZeroBased() >= filteredCardList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
        return new CommandResult(String.format(MESSAGE_SELECT_CARD_SUCCESS, targetIndex.getOneBased()));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SelectCardCommand // instanceof handles nulls
            && targetIndex.equals(((SelectCardCommand) other).targetIndex)); // state check
    }
}
