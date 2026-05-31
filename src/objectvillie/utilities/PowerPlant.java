package src.objectvillie.utilities;

public class PowerPlant extends UtilityProvider{
    public PowerPlant(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public char getSymbol(){
        return 'P';
    }
}
