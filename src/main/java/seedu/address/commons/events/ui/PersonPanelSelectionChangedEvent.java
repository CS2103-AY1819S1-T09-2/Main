package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.Deck;

/**
 * Represents a selection change in the Deck List Panel
 */
public class PersonPanelSelectionChangedEvent extends BaseEvent {


    private final Deck newSelection;

    public PersonPanelSelectionChangedEvent(Deck newSelection) {
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
