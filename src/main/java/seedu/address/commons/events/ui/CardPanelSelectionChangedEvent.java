package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.anakindeck.Card;

/**
 * Represents a selection change in the Card List Panel
 */
public class CardPanelSelectionChangedEvent extends BaseEvent {


    private final Card newSelection;

    public CardPanelSelectionChangedEvent(Card newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Card getNewSelection() {
        return newSelection;
    }
}
