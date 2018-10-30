package seedu.address.commons.events.ui;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.BaseEvent;
import seedu.address.model.deck.Card;

/**
 * Indicates a request to start reviewing deck.
 */
public class StartReviewRequestEvent extends BaseEvent {
    private final ObservableList<Card> cardList;
    private final int startIndex;

    public StartReviewRequestEvent(ObservableList<Card> cardList, Index startIndex) {
        this.cardList = cardList;
        this.startIndex = startIndex.getZeroBased();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public ObservableList<Card> getCards() {
        return cardList;
    }

    public int getStartIndex() {
        return startIndex;
    }
}
