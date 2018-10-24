package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.anakindeck.Deck;

/**
 * Represents a selection change in the Deck List Panel
 */
public class DeckPanelSelectionChangedEvent extends BaseEvent {


    private final Deck newSelection;

    public DeckPanelSelectionChangedEvent(Deck newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Deck getNewSelection() {
        return newSelection;
    }
}
