package handler;

import app.EventBus;
import app.Main;
import event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//This class controls the content displayed to the user such as:
//Current Prompt
//Letters typed (letterIndex)
//Words Typed (wordIndex)
//Accuracy
//WPM
public class DisplayPanel implements EventHandler{
    FXMLLoader fxmlLoader;
    private int wordIndex = 0;
    private ArrayList<String> promptWords;
    NewPromptLoaded newPromptLoaded = new NewPromptLoaded();
    KeyPress keyPress = new KeyPress();
    @FXML
    private Label welcomeText;
    @FXML
    private Text displayText;
    @FXML
    private Text userText;
    @FXML
    private Text timer;
    @FXML
    private Text speed;
    @FXML
    public Text completionMessage;
    @FXML
    public Text accuracy;
    @FXML
    public Text currWord;
    public DisplayPanel(HashMap<String, Text> sceneTexts){
        this.displayText = sceneTexts.get("displayText");
        this.userText = sceneTexts.get("userText");
        this.timer = sceneTexts.get("timer");
        this.speed = sceneTexts.get("speed");
        this.completionMessage = sceneTexts.get("completionMessage");
        this.accuracy = sceneTexts.get("accuracy");
        this.currWord = sceneTexts.get("currWord");
    }
    public void receiveEvent(EventBus eventBus, Event event) {
//        System.out.println("DisplayPanel Received");
//        System.out.println(event);
        if(event.getClass()== NewPromptLoaded.class){
            displayText.setText(((NewPromptLoaded) event).getPrompt());
            promptWords = new ArrayList<String>(List.of(((NewPromptLoaded) event).getPrompt().split(" ")));
            currWord.setText(promptWords.get(0));
        }
        //RECEIVE NEW KEYSTROKE
        else if (event.getClass() == KeyPress.class) {
            keyPress = (KeyPress) event;
            userText.setText(keyPress.newValue);
        }
        //RECEIVE NEW CLOCK TICK
        else if(event.getClass() == NewTick.class){
            int newTick = ((NewTick) event).getTick();
            timer.setText("Time: " + Integer.toString(newTick));
        }
        //RECEIVE NEW WPM
        else if(event.getClass() == WPM.class){
            double currWPM = ((WPM)event).getSpeed();
            speed.setText("Speed: " + Math.floor((currWPM) * 100)/100 + " WPM");
        }
        //RECEIVE TEST COMPLETE
        else if(event.getClass() == TestComplete.class){
            completionMessage.setText("Test Complete!");
            displayText.setText("");
        }
        //UPDATE DISPLAY BETWEEN WORDS
        else if(event.getClass() == NewWord.class){
            userText.setText("");
            wordIndex = wordIndex + 1;
            if(wordIndex < promptWords.size()){
                currWord.setText(promptWords.get(wordIndex));
            }
        }
        //UPDATE ACCURACY WHEN IT CHANGES
        else if(event.getClass() == Accuracy.class){
            System.out.println(((Accuracy) event).getAccuracy());
            double acc = ((Accuracy) event).getAccuracy();
            accuracy.setText("Accuracy: " + Double.toString((Math.floor(acc * 1000) / 1000)));
        }
        //CHANGE FONT COLOR IF CORRECT/INCORRECT
        else if(event.getClass() == SpellingError.class){
            currWord.setFill(Color.RED);
        }
        else if(event.getClass() == CorrectSpelling.class){
            currWord.setFill(Color.GREEN);
        }
    }


}
