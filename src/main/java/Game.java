import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// FIX NETWORKING

// SEA BATTLES

//
public class Game implements Serializable {
    Dice[] dice;
    CardDeck deck;
    Card current;

    public Game() {
        dice = new Dice[8];
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Dice();
        }
        deck = new CardDeck();

    }

    public int collectionPts(int num)
    {

        return 0;
    }

}