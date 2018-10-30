package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.deck.Card;

/**
 * Indicates a request to start reviewing deck.
 */
public class StartReviewRequestEvent extends BaseEvent {
    private final Card card;

    public StartReviewRequestEvent(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Card getCard() {
        return card;
    }
}
