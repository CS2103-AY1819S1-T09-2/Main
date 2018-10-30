package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.StartReviewRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Deck;

/**
 * Starts a review for a deck.
 */
public class ReviewCommand extends Command {

    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reviews a deck inside Anakin. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Deck review started: %1$s";
    public static final String MESSAGE_NO_CARDS = "Cannot review deck with no cards";

    private final Index index;

    /**
     * Creates a ReviewCommand to play a selected deck.
     * @param index of the deck in the filtered deck list to review
     */
    public ReviewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Deck> lastShownList = model.getFilteredDeckList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        // TODO: If user is already playing selected deck, throw error
        // TODO: Else, switch gameplay to the new deck
        // TODO: for anakin, block cd commands when in gameplay

        Deck targetDeck = lastShownList.get(index.getZeroBased());
        model.getIntoDeck(targetDeck);

        ObservableList<Card> cardList = model.getFilteredCardList();
        if (cardList.size() == 0) {
            throw new CommandException(MESSAGE_NO_CARDS);
        }

        // TODO: Get last known card to resume from and pass into event
        EventsCenter.getInstance().post(new StartReviewRequestEvent(cardList, Index.fromZeroBased(0)));
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetDeck));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReviewCommand // instanceof handles nulls
                && this.index.equals(((ReviewCommand) other).index));
    }
}
