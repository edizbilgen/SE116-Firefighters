package src.objectvillie.cells;

public class CommercialZone extends Zone{

    public CommercialZone(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public void processTick() {
        int oldLevel = level;
        if (electricity == 0 || water == 0 || internet == 0) {
            level = 0;
            output = 0;
            if (oldLevel > 0) {
                System.out.println("Commercial at (" + x + "," + y + ") levels down from " + oldLevel + " to 0");
            }
            generateResources();
            System.out.println("Commercial at (" + x + "," + y + ") generated " + output + " lifestyle");
            return;
        }

        if (level < 3 && canUpgrade()) {
            level++;
            System.out.println("Commercial at (" + x + "," + y + ") levels up from " + oldLevel + " to " + level);
        }

        generateResources();
        System.out.println("Commercial at (" + x + "," + y + ") generated " + output + " lifestyle");


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
            output = (2 * m) + Math.min(population, goods);
        }
    }

    @Override
    public boolean canUpgrade() {
        if (level == 0) {
            if ( electricity > 0 && water > 0 && internet > 0) {
                return true;
            }
            return false;
        }
        else if (level == 1) {
            if (population > 0 && goods > 0 && electricity > 0 && water > 0 && internet > 0 && hasSecurity) {
                return true;
            }
            return false;
        }
        else if (level == 2) {
            if (population > 1 && goods > 1 && electricity > 0 && water > 0 && internet > 0 && hasSecurity) {
                return true;
            }
            return false;
        }
        return false;
    }
}

