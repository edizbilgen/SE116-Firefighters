package src.objectvillie.utilities;

class WaterPumpingStation extends UtilityProvider{
    public WaterPumpingStation(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public char getSymbol(){
        return 'W';
    }

}
