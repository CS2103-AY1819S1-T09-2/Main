package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyAnakin;

/** Indicates Anakin in the model has changed*/
public class AnakinChangedEvent extends BaseEvent {

    public final ReadOnlyAnakin data;

    public AnakinChangedEvent(ReadOnlyAnakin data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of decks " + data.getDeckList().size();
    }
}
