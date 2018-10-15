package seedu.address.commons.events.ui;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.BaseEvent;

/**
 * Indicates a request to switch panel from deck to card or vice versa.
 */
public class SwitchPanelRequestEvent extends BaseEvent {

//    public final int targetIndex;

    public SwitchPanelRequestEvent() {
//        this.targetIndex = targetIndex.getZeroBased();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
