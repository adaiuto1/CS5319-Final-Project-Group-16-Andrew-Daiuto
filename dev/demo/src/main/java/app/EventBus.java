package app;

import event.Event;
import event.Index;
import handler.EventHandler;
import handler.ProgressMonitor;
import javafx.util.Pair;

import java.util.ArrayList;
public class EventBus {

    Index idx = new Index();
    ProgressMonitor pm;
    public ArrayList<Pair<EventHandler, Event>> assigments;
    public EventBus(){
        assigments = new ArrayList<Pair<EventHandler, Event>>();
    }
    public void subscribe(EventHandler eventHandler, Event event){
        assigments.add(new Pair<EventHandler, Event>(eventHandler, event));
    }
    public void publish(Event event){
        assigments.forEach(pair ->{
            if(event.getClass() == pair.getValue().getClass()){
                pair.getKey().receiveEvent(this, event);
            }
        });
    }
}
