package event;

public class KeyPress implements Event{
    public String oldValue;
    public String newValue;
    public KeyPress(){
        this.oldValue="";
        this.newValue="";
    }
    public KeyPress(String oldValue, String newValue){
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

}
