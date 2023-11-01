package event;
public class WPM implements Event{
    private double speed;
    public WPM(){};
    public WPM(double speed){
        this.speed = speed;
    }
    public double getSpeed(){
        return speed;
    };

}
