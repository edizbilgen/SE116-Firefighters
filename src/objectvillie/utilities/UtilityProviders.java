package src.objectvillie.utilities;

public abstract class UtilityProviders {
    protected int maxCapacity; // 100
    protected int currentCapacity;
    protected int x;
    protected int y;

    public UtilityProviders(int maxCapacity, int currentCapacity, int x, int y) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.x = x;
        this.y = y;
    }

    protected abstract void resetCapacity(); //For generating 100 utilitiy every tick
    
}


