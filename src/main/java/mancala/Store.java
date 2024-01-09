package mancala;

import java.io.Serializable;

class Store implements Countable, Serializable{
    private static final long serialVersionUID = -4370772003386395841L;
    private Player owner;
    private int stones;

    public Store(){
        stones = 0;
    }
    @Override
    public void addStone(){
        stones ++;
    }
    @Override
    public void addStones(int amount){
        stones += amount;
    }
    @Override
    public int removeStones(){
        final int val = stones;
        stones = 0;
        return val;
    }
    public Player getOwner(){
        return owner;
    }
    @Override
    public int getStoneCount(){
        return stones;
    }
    public void setOwner(final Player player){
        owner = player;
    }

    @Override
    public String toString(){
        return String.format("%2d", stones);
    }
}
