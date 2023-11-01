package handler;

import app.EventBus;
import app.Main;
import event.Event;
import event.NewTick;
import event.TestComplete;
import event.TestStart;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Clock implements EventHandler{
    private int currTick = 0;
    private boolean testOver = false;
    public class TickTask extends TimerTask{
        EventBus eventBus;
        TickTask(EventBus eventBus){
            this.eventBus = eventBus;
        };
        @Override
        public void run() {
            currTick = currTick + 1;
            if(!testOver){
                eventBus.publish(new NewTick(currTick));
            }
        }
    }

    public Clock(){

    }
    public void receiveEvent(EventBus eventBus, Event event){
        if(event.getClass() == TestStart.class){
            Timer timer = new Timer();
            timer.schedule(new TickTask(eventBus), 0, 1000);
        }
        else if(event.getClass() == TestComplete.class){
            testOver = true;
        }
    }
}
