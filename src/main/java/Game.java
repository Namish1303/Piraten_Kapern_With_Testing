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
        switch (num){
            case 3:
                return 100;
            case 4:
                return 200;
            case 5:
                return 500;
            case 6:
                return 1000;
            case 7:
                return 2000;
            case 8:
                return 4000;
        }
        return 0;
    }

    public Dice[] getGameDices(){
        for(int i=0;i<8;i++)
        {
            dice[i].randomize();
        }

        return dice;
    }

}