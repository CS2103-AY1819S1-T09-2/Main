package seedu.address.commons.core;

import seedu.address.commons.events.BaseEvent;

/**
 * Base class for *Manager classes
 * <p>
 * Registers the class' event handlers in eventsCenter
 */
public abstract class AddressbookComponentManager {
    protected EventsCenter eventsCenter;

    /**
     * Uses default {@link EventsCenter}
     */
    public AddressbookComponentManager() {
        this(EventsCenter.getInstance());
    }

    public AddressbookComponentManager(EventsCenter eventsCenter) {
        this.eventsCenter = eventsCenter;
        eventsCenter.registerHandler(this);
    }

    protected void raise(BaseEvent event) {
        eventsCenter.post(event);
    }
}
