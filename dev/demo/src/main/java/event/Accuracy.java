package event;

public class Accuracy implements Event{
    private double accuracy;
    public Accuracy(){};
    public Accuracy(double accuracy){
        this.accuracy = accuracy;
    }
    public double getAccuracy(){
        return accuracy;
    }
}
