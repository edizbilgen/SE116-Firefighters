package src.objectvillie.cells;

public abstract class ServiceProvider extends Cell {
    protected int radius;

    public ServiceProvider(int x, int y, char symbol, int radius) {
        super(x, y, symbol);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}