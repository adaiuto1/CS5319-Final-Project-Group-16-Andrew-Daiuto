package handler;

import app.EventBus;
import event.*;
import event.SpellingError;

import java.util.ArrayList;
import java.util.List;

public class AccuracyCheck implements EventHandler {
    private ArrayList<String> promptWords;
    private ArrayList<String> displayThree = new ArrayList<String>();
    private int wordIndex;
    private String correctPartial;
    private String currWord;
    private int correctWords = 0;
    private double accuracy;
    public void receiveEvent(EventBus eventBus, Event event) throws StringIndexOutOfBoundsException{
        try{
            if(event.getClass() == NewPromptLoaded.class){
                //handle when prompt loaded
                String prompt = ((NewPromptLoaded) event).getPrompt();
                promptWords = new ArrayList<String>(List.of(prompt.split(" ")));
                for(int i = 0; i < 5; i++){
                    displayThree.add(promptWords.get(i));
                }
            }
            else if(event.getClass() == Index.class){
                if(((Index) event).getWordIndex() > wordIndex){
                    wordIndex = ((Index) event).getWordIndex();
                    updateAccuracy(currWord, correctPartial);
                }
            }
            else if(event.getClass() == KeyPress.class){
                //observe the user's current input word
                //compare it to the correct word's substring of equal length to the input
                currWord = ((KeyPress) event).newValue;
                if(promptWords.get(wordIndex).length() >= currWord.length()){
                    correctPartial = promptWords.get(wordIndex).substring(0, currWord.length());
                }
                if(currWord.equals(correctPartial)){
                    eventBus.publish(new CorrectSpelling());
                }
                else{
                    eventBus.publish(new SpellingError());
                }
            }
            else if(event.getClass() == NewTick.class){
                eventBus.publish(new Accuracy(Math.floor(accuracy * 10000 / 100)));
            }
        }catch(IndexOutOfBoundsException e){
            eventBus.publish(new TestComplete());
        }

    }
    private void updateAccuracy(String currWord, String correctWord){
        //accuracy = correct words / total words typed
        if(currWord.equals(correctWord)){
            correctWords = correctWords + 1;
        }
        accuracy = (double)correctWords / (double)wordIndex;
    }
}
