package src.objectvillie.cells;

public class CommercialZone extends Zone{

    public CommercialZone(int x, int y, char symbol) {
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

        if (level == 0) {
            if (population > 0 &&
                    goods > 0 &&
                    electricity > 0 &&
                    water > 0 &&
                    internet > 0) {
                return true;
            }
            return false;
        }
        else if (level == 1) {
            if (hasSecurity) {
                return true;
            }
            return false;
        }
        else if (level == 2) {
            if (population > 1 && goods > 1) {
                return true;
            }
            return false;
        }
        return false;
    }
}

