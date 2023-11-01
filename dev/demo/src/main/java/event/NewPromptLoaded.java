package event;

public class NewPromptLoaded implements Event{
    private String prompt;
    public NewPromptLoaded(){};
    public NewPromptLoaded(String prompt){
        this.prompt = prompt;
    };
    public String getPrompt(){
        return prompt;
    }
}
