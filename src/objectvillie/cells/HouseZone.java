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
        if (level == 0) {
            if (electricity > 0 && water > 0 && internet > 0) {
                return true;
            }
            return false;
        }
        else if (level == 1) {
            if (hasSecurity && hasHealth && hasEducation) {
                return true;
            }
            return false;
        }
        else if (level == 2) {

            if (lifestyle > 0) {
                return true;
            }
            return false;
        }
        return false;

    }
}
