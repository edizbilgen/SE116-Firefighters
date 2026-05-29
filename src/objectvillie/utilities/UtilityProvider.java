package src.objectvillie.utilities;

import src.objectvillie.cells.Cell;

public abstract class UtilityProvider extends Cell {
    protected int maxCapacity; // 100
    protected int currentCapacity;


    public UtilityProvider(int x, int y, char symbol ) {
        super(x, y, symbol);
        this.maxCapacity = 100;
        this.currentCapacity = 100;

    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void resetCapacity() {//For generating 100 utilitiy every tick
        currentCapacity =maxCapacity;
    }

    public void consumeUtility(int amount){ // Conusmes utility
        if (currentCapacity >= amount){
            currentCapacity-=amount;
        }else {
            currentCapacity=0;
        }
    }

    public abstract char getSymbol();
}








