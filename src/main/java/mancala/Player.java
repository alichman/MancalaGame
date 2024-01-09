package mancala;
import java.io.Serializable;

class Player implements Serializable{
    private static final long serialVersionUID = 641305709769862380L;
    private String name;
    private Store store;
    private int playerID;
    private UserProfile profile;

    /* default */Player() {
        this("Unknown Player");
    }
    /* default */ Player(final String newName){
        setName(newName);
    }

    void linkProfile(final UserProfile newProf){
        profile = newProf;
        setName(profile.getName());
    }

    void createProfile(){
        profile = new UserProfile(name);
    }

    /* default */ String getName(){
        return name;
    }

    /* default */ int getID(){ 
        return playerID; 
    }
    /* default */ void setName(final String newName){ 
        name = newName; 
    }
    /* default */ void setID(final int newID){
        playerID=newID;
    }
    void addGame(int gameType, boolean winner){
        // 0 for Kalah, 1 for Ayo
        if(winner){
            profile.addWin(gameType);
        } else{
            profile.addLoss(gameType);
        }
    }
    void addTie(int gameType){
        profile.addTie(gameType);
    }
    int getWins(int gamemode){
        return profile.getWins(gamemode);
    }
    int getLosses(int gamemode){
        return profile.getLoss(gamemode);
    }

    void setStore(Store newStore){
        store = newStore;
    }

    int getStoreCount(){
        return store.getStoneCount();
    }

    Store getStore(){
        return store;
    }

    @Override
    public String toString(){
        return getName();
    }
}
