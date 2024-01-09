package mancala;
import java.io.Serializable;

import javax.management.RuntimeErrorException;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)
    private boolean Repeat;

    private static final long serialVersionUID = 3138246236960627645L;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pit
     * Num The number of the pit.
     * @return The number of stones in the pit.
     */
    int getNumStones(int pitNum) {
        if(pitNum < 1 || pitNum > 12){
            throw new RuntimeException("Invalid Pit input "+ pitNum);
        }
        int value;
        value = gameBoard.getNumStones(pitNum);

        return value;
    }

    int getStoreAmount(int playerNum){
        return gameBoard.getStoreCount(playerNum);
    }

    int getPlayerScore(int playerNum){
        return gameBoard.getStoreCount(playerNum);
    }


    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(int pitNum) {
        // This method can be implemented in the abstract class.
        if(pitNum < 1 || pitNum > 14){
            throw new RuntimeException("Pit" + pitNum + " out of range");
        }
        try{
            int count = 0;
            for(int i= 1 + (pitNum/7)*7 ; i< 6 + (pitNum/7)*7; i++){
                count += getNumStones(i);
            }
            if (count <= 0){
                System.out.println("SIDE IS EMPTY");
            }
            return count <= 0;
        } catch(IndexOutOfBoundsException e){
            throw new RuntimeException("Index out of Bounds");
        }

    }

    void cleanUpBoard(){
        gameBoard.cleanUp();
        setRepeat(false);
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

    public int getPlayer(){
        return currentPlayer;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    boolean repeatTurn(){
        return Repeat;
    }
    void setRepeat(final boolean value){
        Repeat = value;
    }

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(Player one, Player two) {
        // this method can be implemented in the abstract class.
        one.setStore(new Store());
        gameBoard.setStore(one.getStore(), 1);

        two.setStore(new Store());
        gameBoard.setStore(two.getStore(), 2);



        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */


    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        // Implement toString() method logic here.
        return " ";
    }
}
