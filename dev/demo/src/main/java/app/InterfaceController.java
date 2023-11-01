package app;

import event.*;
import handler.DisplayPanel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.text.Text;

import java.util.HashMap;

public class InterfaceController {
    EventBus eventBus = Main.getEventBus();
    public DisplayPanel displayPanel;
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
    NewPromptLoaded newPromptLoaded = new NewPromptLoaded();
    KeyPress keyPress = new KeyPress();
    NewTick newTick = new NewTick();
    private HashMap<String, Text> sceneTexts = new HashMap<String, Text>();
    private String oldValue = "";
    @FXML
    public Text displayText;
    @FXML
    public Text userText;
    @FXML
    public Button loadPromptButton;
    @FXML
    public TextField textField;
    @FXML
    public Text timer;
    @FXML
    public Text speed;
    @FXML
    public Text completionMessage;
    @FXML
    public Text accuracy;
    @FXML
    public Text currWord;
    @FXML
    protected void beginTest(){
        eventBus.publish(new TestStart());
        eventBus.publish(new NewPromptRequested());
    }
    public void initialize() {
        //initialize displayPanel and subscribe to appropriate event types
        sceneTexts.put("displayText", displayText);
        sceneTexts.put("userText", userText);
        sceneTexts.put("timer", timer);
        sceneTexts.put("speed", speed);
        sceneTexts.put("completionMessage", completionMessage);
        sceneTexts.put("accuracy", accuracy);
        sceneTexts.put("currWord", currWord);
        displayPanel = new DisplayPanel(sceneTexts);

        eventBus.subscribe(displayPanel, new NewPromptLoaded());
        eventBus.subscribe(displayPanel, new KeyPress());
        eventBus.subscribe(displayPanel, new NewTick());
        eventBus.subscribe(displayPanel, new WPM());
        eventBus.subscribe(displayPanel, new TestComplete());
        eventBus.subscribe(displayPanel, new Accuracy());
        eventBus.subscribe(displayPanel, new NewWord());
        eventBus.subscribe(displayPanel, new SpellingError());
        eventBus.subscribe(displayPanel, new CorrectSpelling());
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                try{
                    if(newValue.endsWith(" ")){
                        NewWord newWord = new NewWord();
                        eventBus.publish(newWord);
                        Platform.runLater(()->{
                            textField.clear();
                        });
                    }
                    else{
                        KeyPress keyPress = new KeyPress(oldValue, newValue);
                        eventBus.publish(keyPress);
                    }
                }catch (IllegalArgumentException e){

                }

            }
        });
    }
}