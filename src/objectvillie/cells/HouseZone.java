package src.objectvillie.cells;

public class HouseZone extends Zone {

    public HouseZone(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public void processTick() {
        int oldLevel = level;
        if (electricity == 0 || water == 0 || internet == 0) {
            level = 0;
            output = 0;
            if (oldLevel > 0) {
                System.out.println("House at (" + x + "," + y + ") levels down from " + oldLevel + " to 0");
            }
            generateResources();
            System.out.println("House at (" + x + "," + y + ") generated " + output + " population");
            return;
        }

        if (level < 3 && canUpgrade()) {
            level++;
            System.out.println("House at (" + x + "," + y + ") levels up from " + oldLevel + " to " + level);
        }

        generateResources();
        System.out.println("House at (" + x + "," + y + ") generated " + output + " population");
    }

    @Override
    public void generateResources() {
        int m = calculateM();

        if (level == 0) {
            if (electricity > 0 && water > 0 && internet > 0) {
                output = 1;
            } else {
                output = 0;
            }
        }

        else if (level == 1) {
            output = m;
        }

        else if (level == 2) {
            output = 2 * m;
        }

        else if (level == 3) {
            output = (2 * m) + lifestyle;
        }
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
            if (electricity > 0 && water > 0 && internet > 0 && hasSecurity && hasHealth && hasEducation) {
                return true;
            }
            return false;
        }
        else if (level == 2) {
            if (electricity > 0 && water > 0 && internet > 0 && hasSecurity && hasHealth && hasEducation && lifestyle > 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
