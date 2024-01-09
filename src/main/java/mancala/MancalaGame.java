package mancala;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MancalaGame implements Serializable{
    private static final long serialVersionUID = -4155147770585076890L;
    private GameRules rules;
    private Player cPlayer;
    private int gameMode;
    final private ArrayList<Player> pList = new ArrayList<>(); 

    public MancalaGame(final int gamemode){
        gameMode = gamemode;
        if(gamemode == 1){
            rules = new AyoRules();
        } else{
            rules = new KalahRules();
        }
         
        setPlayers(new Player(), new Player());
        setCurrentPlayer(1);
    }
    /* Default */ public MancalaGame(){
        this(0);
    }

    public void setPlayers(final Player onePlayer, final Player twoPlayer){
        
        onePlayer.setName("Player One");
        onePlayer.setID(1);
        onePlayer.createProfile();
        pList.add(onePlayer);

        twoPlayer.setName("Player Two");
        twoPlayer.setID(2);
        twoPlayer.createProfile();
        pList.add(twoPlayer);
    }

    public void setPlayer(final Serializable one, final int playerNum){
        pList.set(playerNum -1, (Player)one);
    }
    public void setPlayer(final String one, final int playerNum){
        pList.set(playerNum -1, new Player(one));
    }
    
    public void setCurrentPlayer(final int playerID){
        cPlayer = pList.get(playerID-1);
    }
    
    public ArrayList<Player> getPlayers(){
        return pList;
    }
    public int getCurrentPlayer(){
        return cPlayer.getID();
    }
    public String getCurrentName(){
        return cPlayer.getName();
    }
    public String getPlayerName(int playerNum){
        Player player = pList.get(playerNum-1);
        return player.getName();
    }
    
    public int getNumStones(int pitNum){
        if(pitNum < 1 || pitNum > 14){
            throw new RuntimeException("Pit number out of range");
        }
        if(pitNum == 7 || pitNum == 14){
            return rules.getStoreAmount(pitNum/7);
        }
        if(pitNum > 7){
            pitNum -= 1;
        }
        return rules.getNumStones(pitNum);        
    }
     
    public boolean isGameOver(){
        return rules.isSideEmpty(1) || rules.isSideEmpty(8);
    }

    public Player getWinner() throws GameNotOverException{
        if(!isGameOver()){
            throw new GameNotOverException("The game isn't over yet.");
        }

        rules.cleanUpBoard();
        final int diff = rules.getPlayerScore(1) - rules.getPlayerScore(2);
        Player winner = null;
        Player loser = null;
        if(diff > 0){
            winner = pList.get(0);
            loser = pList.get(1);
        }
        else if(diff < 0){
            winner = pList.get(1);
            loser = pList.get(0);
        }

        // code to manage save score;
        if(winner != null){
            winner.addGame(gameMode, true);
            loser.addGame(gameMode, false);
        } else{
            pList.get(0).addTie(gameMode);
            pList.get(1).addTie(gameMode);
        }
        
        return winner;
    }

    public String getWinningName(){
        Player winner;
        try{
            winner = getWinner();
        } catch(GameNotOverException e){
            winner = null;
        }
        String result = null;

        if(winner != null){
            result = winner.getName();
        }
        return result;
    }

    
    public void move(final int startPit) throws InvalidMoveException{
        System.out.println("Game: " + startPit + " " + cPlayer.getID());
        rules.moveStones(startPit, cPlayer.getID());

        if(!rules.repeatTurn()){
            setCurrentPlayer(3 - getCurrentPlayer());
        }
    }

    public void startNewGame(){
        rules.resetBoard();
    }
}
