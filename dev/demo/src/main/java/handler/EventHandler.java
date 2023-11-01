package handler;

import event.Event;
import app.EventBus;
public interface EventHandler {
    public void receiveEvent(EventBus eventBus, Event event);
}
