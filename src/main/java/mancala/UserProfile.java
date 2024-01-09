package mancala;
import java.io.Serializable;

public class UserProfile implements Serializable{
    private static final long serialVersionUID = 2132037563234879739L;
    private String username;
    private int numWins[] = new int[2];
    private int numLoss[] = new int[2];
    private int numTies[] = new int[2];
    private int totalNumGames[] = new int[2];

    public UserProfile(){
        this("Unknown player");
    }
    public UserProfile(final String name){
        username = name;
        numWins[0] = 0;
        numWins[1] = 0;
        numLoss[0] = 0;
        numLoss[1] = 0;
        totalNumGames[0] = 0;
        totalNumGames[1] = 0;
    }

    public void setName(final String newName){
        username = newName;
    }
    public String getName(){
        return username;
    }

    public void addWin(final int gamemode){
        numWins[gamemode] ++;
        totalNumGames[gamemode] ++;
    }
    public int getWins(final int gamemode){
        return numWins[gamemode];
    }

    public void addLoss(final int gamemode){
        numLoss[gamemode] ++;
        totalNumGames[gamemode] ++;
    }
    public int getLoss(final int gamemode){
        return numLoss[gamemode];
    }

    public void addTie(final int gamemode){
        numTies[gamemode] ++;
        totalNumGames[gamemode] ++;
    }
    public int getTies(final int gamemode){
        return numTies[gamemode];
    }

    public int getTotalGames(final int gamemode){
        return totalNumGames[gamemode];
    }
}
