package mancala;

public class InvalidMoveException extends Exception{
    public InvalidMoveException(final String erMsg){
        super(erMsg);
    }
}
