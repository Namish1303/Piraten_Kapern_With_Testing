import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTesting {
    Game g;
    Dice[] dice;
    Card c;
    public GameTesting()
    {
        g = new Game();
        dice = new Dice[8];
    }

    @Test
    public void collPts()
    {
        System.out.println("\n\nRUNNING TESTS FOR COLLECTIONS POINTS");
        System.out.println("Collection of 3 is 100 pts");
        Assertions.assertEquals(100,g.collectionPts(3));

        System.out.println("Collection of 6 is 1000 pts");
        Assertions.assertEquals(1000,g.collectionPts(6));

        System.out.println("Collection of 8 is 4000 pts");
        Assertions.assertEquals(4000,g.collectionPts(8));

    }

    @Test
    public void getDices()
    {
        dice = g.getGameDices();
        Assertions.assertEquals(dice,g.getGameDices());
        System.out.println("\n\nDICE TEST\n8 dices received from game class");
    }

    @Test
    public void getCard()
    {
        c = g.getGameCard();
        Assertions.assertEquals(c.getClass(),g.getGameCard().getClass());
        System.out.println("\n\nCARD TEST\nCard received from game class");
    }

    @Test
    public void ConvertToMap()
    {
        System.out.println("\n\nRUNNING TESTS FOR CONVERSTION OF DICE SET TO A MAP");
        Dice d = new Dice("Sword");
        Dice d2 = new Dice("Monkey");
        for(int i=0; i<8;i++)
        {
            if(i%2==0)
            {
                dice[i] = d;
            }
            else
            {
                dice[i] = d2;
            }
        }

        System.out.println("Map should have 4 swords");
        Assertions.assertEquals(4,g.DiceToCollection(dice).get("Sword"));

        System.out.println("Map should have 4 monkeys");
        Assertions.assertEquals(4,g.DiceToCollection(dice).get("Monkey"));

        Dice d3 = new Dice("Coin");
        dice[7] = d3;

        System.out.println("After 1 coin in dice set, map should have 1 coin");
        Assertions.assertEquals(1,g.DiceToCollection(dice).get("Coin"));

    }











}
