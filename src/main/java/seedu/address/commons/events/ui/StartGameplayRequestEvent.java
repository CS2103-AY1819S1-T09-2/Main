package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.deck.Deck;

/**
 * Indicates a request to start playing deck.
 */
public class StartGameplayRequestEvent extends BaseEvent {


    private final Deck deckToPlay;

    public StartGameplayRequestEvent(Deck deckToPlay) {
        this.deckToPlay = deckToPlay;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Deck getDeckToPlay() {
        return deckToPlay;
    }
}
