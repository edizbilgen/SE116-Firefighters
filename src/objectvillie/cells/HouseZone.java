package src.objectvillie.cells;

public class HouseZone extends Zone {

    public HouseZone(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public void processTick() {

    }

    @Override
    public void generateResources() {

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }
}
