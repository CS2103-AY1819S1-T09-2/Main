package seedu.address.commons.core;

import seedu.address.commons.events.BaseEvent;

/**
 * Base class for *Manager classes
 *
 * Registers the class' event handlers in eventsCenter
 */
public class AnakinComponentManager {
    protected EventsCenter eventsCenter;

    /**
     * Uses default {@link EventsCenter}
     */
    public AnakinComponentManager() {
        this(EventsCenter.getInstance());
    }

    public AnakinComponentManager(EventsCenter eventsCenter) {
        this.eventsCenter = eventsCenter;
        eventsCenter.registerHandler(this);
    }

    protected void raise(BaseEvent event) {
        eventsCenter.post(event);
    }
}
