package src.objectvillie.utilities;

class WaterPumpingStation extends UtilityProvider{
    public WaterPumpingStation( int x, int y) {
        super( x, y);
    }

    @Override
    public char getSymbol(){
        return 'W';
    }

}
