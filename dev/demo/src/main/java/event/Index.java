package event;

public class Index implements Event{
    private int letterIndex;
    private int wordIndex;
    public Index(){};

    public Index(int letterIndex, int wordIndex){
        this.letterIndex = letterIndex;
        this.wordIndex = wordIndex;
    }
    public int getletterIndex(){
        return letterIndex;
    }
    public int getWordIndex(){
        return wordIndex;
    }
}
