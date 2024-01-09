package mancala;

public class KalahRules extends GameRules{
    private MancalaDataStructure gameBoard;

    public KalahRules(){
        super();
        gameBoard = getDataStructure();
    }

    public int moveStones(int pitNum, int playerNum) throws InvalidMoveException{
        if(pitNum <= (playerNum-1)*6 || pitNum > playerNum*6){
            throw new InvalidMoveException("Pit" + pitNum + " out of range for player "+ playerNum);
        }

        setPlayer(playerNum);

        int startAmount = gameBoard.getStoreCount(playerNum);

        distributeStones(pitNum);
        
        int result = gameBoard.getStoreCount(playerNum) - startAmount;

        System.err.println(result);

        return result;
    }

    int distributeStones(int pitNum){
        System.out.println("Distribute Stones: " + pitNum);

        int numStones = gameBoard.removeStones(pitNum);
        Countable pos = null;
        int movedStones = numStones;
        
        gameBoard.setIterator(pitNum, getPlayer(), false);

        while(numStones != 0){
            pos = gameBoard.next();
            pos.addStone();
            numStones --;
        }

        // If inside own store, set repeat
        setRepeat(pos instanceof Store);

        // If on current side
        System.out.println(gameBoard.getCurrentSide() + " " + getPlayer() + " " + pos.getStoneCount());
        if(gameBoard.getCurrentSide() == getPlayer() && pos.getStoneCount() == 1){
            System.out.println(gameBoard.getCurrentPosition());
            movedStones += captureStones(gameBoard.getCurrentPosition()+1);
        }

        return movedStones;
    }
    
    int captureStones(int stoppingPoint){
        if(stoppingPoint > 7){
            stoppingPoint --;
        }
        if(stoppingPoint > 12 || stoppingPoint < 1){
            throw new RuntimeException("Pit out of range "+ stoppingPoint);
        }
        int count = 0;
        count += gameBoard.removeStones(stoppingPoint);
        count += gameBoard.removeStones(13-stoppingPoint);

        gameBoard.addToStore(stoppingPoint/7 + 1, count);

        return count;
    }
}
