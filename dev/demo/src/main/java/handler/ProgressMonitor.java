package handler;

import app.EventBus;
import event.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ProgressMonitor implements EventHandler{
    //Subscribed to: KeyPress
    //Publishes: Index
    private Index index = new Index();
    private int letterIndex = 0;
    private int wordIndex = 0;
    private int numWords;
    public ProgressMonitor(){};
    public void receiveEvent(EventBus eventBus, Event e){
        if(e.getClass() == NewPromptLoaded.class){
            String newPrompt = ((NewPromptLoaded) e).getPrompt();
            numWords = Arrays.asList(newPrompt.split(" ")).size();
        }
        //if keystroke, update current index
        else if(e.getClass() == KeyPress.class){
            updateLetterIndex(((KeyPress) e).oldValue, ((KeyPress) e).newValue);
            index = new Index(letterIndex, wordIndex);
            eventBus.publish(index);
        }//handle index of new word
        else if(e.getClass() == NewWord.class){
            handleNewWord();
            if(wordIndex < numWords){
                index = new Index(letterIndex, wordIndex);
                eventBus.publish(index);
            }
            else{
                eventBus.publish(new TestComplete());
            }
        }
    }

    private void updateLetterIndex(String oldValue, String newValue){
        if(oldValue.length() < newValue.length()){
            letterIndex = letterIndex + 1;
        }
        else{
            letterIndex = letterIndex -1;
        }
    }
    private void handleNewWord(){
        //reset letter index to 0
        //increment word index
        wordIndex = wordIndex + 1;
        letterIndex = 0;
    }

}
