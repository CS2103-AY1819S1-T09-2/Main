package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.deck.Card;

/**
 * Indicate a request to advance to next card in deck review mode.
 */
public class ReviewNextCardEvent extends BaseEvent {
    private final Card nextCard;

    public ReviewNextCardEvent(Card nextCard) {
        this.nextCard = nextCard;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Card getNextCard() {
        return nextCard;
    }
}
