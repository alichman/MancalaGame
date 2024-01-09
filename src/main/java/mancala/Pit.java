package mancala;

import java.io.Serializable;

class Pit implements Countable, Serializable{
    private int stones;
    private static final long serialVersionUID = -1427959254908519773L;

    /* default */ Pit(){
        this(0);
    }
    Pit(int initValue){
        stones = initValue;
    }

    /* default */ public void addStones(final int amount){
        stones += amount;
    }
    /* default */ public void addStone(){
        stones++;
    }
    /* default */ public int getStoneCount(){
        return stones;
    }
    /* default */ public int removeStones(){
        final int val = stones;
        stones = 0;
        return val;
    }
    @Override
    public String toString(){
        return String.format("%2d", stones);
    }
}
