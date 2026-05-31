package src.objectvillie.utilities;

public class WaterPumpingStation extends UtilityProvider{
    public WaterPumpingStation(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public char getSymbol(){
        return 'W';
    }

}
