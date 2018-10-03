package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.AnakinReadOnlyAnakin;

/** Indicates Anakin in the model has changed*/
public class AnakinChangedEvent extends BaseEvent {

    public final AnakinReadOnlyAnakin data;

    public AnakinChangedEvent(AnakinReadOnlyAnakin data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of decks " + data.getDeckList().size();
    }
}
