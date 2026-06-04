package src.objectvillie.cells;

public class IndustrialZone extends Zone {

    public IndustrialZone(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public void processTick() {
        if (electricity == 0 || water == 0) {
            level = 0;
            output = 0;
            return;
        }

        if (level < 3 && canUpgrade()) {
            level++;
        }

        generateResources();

    }

    @Override
    public void generateResources() {
        int m = Math.min(electricity, water);

        if (level == 0) {
            output = 0;
        }

        else if (level == 1) {
            output = m;
        }

        else if (level == 2) {
            output = 2 * m;
        }

        else if (level == 3) {
            output = (2 * m) + population;
        }

    }

    @Override
    public boolean canUpgrade() {
        if (level == 0) {
            if (population > 0 && electricity > 0 && water > 0) {
                return true;
            }
            return false;
        }
        else if (level == 1) {

            if (population > 0 && electricity > 0 && water > 0 && hasSecurity) {
                return true;
            }
            return false;
        }
        else if (level == 2) {
            if (population > 1 && electricity > 0 && water > 0 && hasSecurity) {
                return true;
            }
            return false;
        }
        return false;
    }
}
