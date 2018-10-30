package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.deck.Card;

/**
 * Indicate a request to move to previous card in deck review mode.
 */
public class ReviewPreviousCardEvent extends BaseEvent {
    private final Card prevCard;

    public ReviewPreviousCardEvent(Card prevCard) {
        this.prevCard = prevCard;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Card getPreviousCard() {
        return prevCard;
    }
}
