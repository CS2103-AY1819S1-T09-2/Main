package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.anakindeck.AnakinCard;

/**
 * Represents a selection change in the Card List Panel
 */
public class CardPanelSelectionChangedEvent extends BaseEvent {


    private final AnakinCard newSelection;

    public CardPanelSelectionChangedEvent(AnakinCard newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public AnakinCard getNewSelection() {
        return newSelection;
    }
}
