package src.objectvillie.utilities;

public class InternetHub extends UtilityProvider{
    public InternetHub(int x, int y, char symbol) {
        super(x, y, symbol);
    }

    @Override
    public char getSymbol() {
        return 'T';
    }
}

