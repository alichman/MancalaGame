package mancala;

public class GameNotOverException extends Exception{
    public GameNotOverException(final String erMsg){
        super(erMsg);
    }
}
