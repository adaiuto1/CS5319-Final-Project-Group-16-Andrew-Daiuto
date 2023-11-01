package handler;

import app.EventBus;
import event.Event;
import event.Index;
import event.NewTick;
import event.WPM;
//5 words in 10 sec = 30wpm
//120 words in 60 sec = 120wpm
//120 words in 90 sec = 90wpm
public class SpeedCheck implements EventHandler{
    private int elapsedSeconds = 0;

    public void receiveEvent(EventBus eventBus, Event event){
        if(event.getClass() == NewTick.class){
            elapsedSeconds = elapsedSeconds + 1;
        }
        else if(event.getClass() == Index.class){
            double speedFactor = 60 / (double)elapsedSeconds;
            int wordsTyped = ((Index) event).getWordIndex();
            WPM wpm = new WPM(wordsTyped * speedFactor);
            eventBus.publish(wpm);
        }
    }
}
