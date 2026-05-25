package src.objectvillie.utilities;

public abstract class UtilityProvider {
    protected int maxCapacity; // 100
    protected int currentCapacity;
    protected int x;
    protected int y;

    public UtilityProvider(  int x, int y) {
        this.maxCapacity = 100;
        this.currentCapacity = 100;
        this.x = x;
        this.y = y;
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








