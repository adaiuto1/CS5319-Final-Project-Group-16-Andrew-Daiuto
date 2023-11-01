package handler;

import app.EventBus;
import event.Event;
import event.NewPromptLoaded;
import event.NewPromptRequested;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileHandler implements EventHandler{
    private NewPromptRequested newPrompt = new NewPromptRequested();

    List<String> prompts = new ArrayList<>();
    public FileHandler() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("unitype_prompts.txt"))) {
            String newPrompt;
            while ((newPrompt = reader.readLine()) != null) {
                prompts.add(newPrompt);
            }
        }
    };
    public void receiveEvent(EventBus eventBus, Event event){
        if(event.getClass() == newPrompt.getClass()){
            Random  rand = new Random();
            int randomIndex = rand.nextInt(prompts.size());
            String newPrompt = prompts.get(randomIndex);
            NewPromptLoaded newPromptLoaded = new NewPromptLoaded(newPrompt);
            eventBus.publish(newPromptLoaded);
        }
    }
}
