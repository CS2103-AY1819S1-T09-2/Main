package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Represents a selection change in the Deck List Panel
 */
public class DeckPanelSelectionChangedEvent extends BaseEvent {


    private final AnakinDeck newSelection;

    public DeckPanelSelectionChangedEvent(AnakinDeck newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public AnakinDeck getNewSelection() {
        return newSelection;
    }
}
