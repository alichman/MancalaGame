package mancala;

public class AyoRules extends GameRules{
    private MancalaDataStructure gameBoard;

    public AyoRules(){
        super();
        gameBoard = getDataStructure();
    }

    public int moveStones(int pitNum, int playerNum) throws InvalidMoveException{
        if(pitNum <= (playerNum-1)*6 || pitNum > playerNum*6){
            throw new InvalidMoveException("Pit out of range");
        }

        setPlayer(playerNum);

        int startAmount = gameBoard.getStoreCount(playerNum);
        
        distributeStones(pitNum);

        return gameBoard.getStoreCount(playerNum) - startAmount;

    }

    int distributeStones(int pitNum){
        int numStones = gameBoard.removeStones(pitNum);
        Countable pos = null;
        int movedStones = numStones;
        
        gameBoard.setIterator(pitNum, getPlayer(), true);

        while(numStones != 0){
            pos = gameBoard.next();
            pos.addStone();
            numStones --;

            if(numStones == 0 && pos.getStoneCount() > 1){
                numStones = pos.removeStones();
                movedStones += numStones;
            }
        }               

        return movedStones;
    }

    int captureStones(final int stoppingPoint){
        if(stoppingPoint > 12 || stoppingPoint < 1){
            throw new RuntimeException("Pit out of range");
        }
        int count = 0;
        count += gameBoard.removeStones(13-stoppingPoint);

        return count;
    }
    
}
