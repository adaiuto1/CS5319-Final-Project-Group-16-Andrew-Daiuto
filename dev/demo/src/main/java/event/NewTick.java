package event;

public class NewTick implements Event{
    private int tick;
    public NewTick(){
        this.tick = 0;
    }
    public NewTick(int tick){
        this.tick = tick;
    }
    public int getTick(){
        return tick;
    }
}
