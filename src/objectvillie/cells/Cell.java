package src.objectvillie.cells;

public abstract class Cell {
    protected int x;
    protected int y;
    protected char symbol; //it's holding letter inside the map(A,B,C vb.)
    public Cell(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    // I wrote the all getter and setters
}
