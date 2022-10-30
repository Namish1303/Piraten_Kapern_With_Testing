import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AcceptancePart1 {
    Dice[] d;
    Card c;
    Game g;
    public AcceptancePart1()
    {
        d = new Dice[8];
        c= new Card("Coin",1,0);
        g = new Game();
    }

    @Test
    public void Row45()
    {
        for(int i=0;i<8;i++)
        {
            d[i] = new Dice("Sword");
        }

        Dice skull = new Dice("Skull");
        d[0] = skull;
        d[1] = skull;
        d[2] = skull;

        // 3 skulls and 5 swords

        System.out.println("\n\nROW 45: 3 skulls and 5 swords");
        Assertions.assertTrue(g.endTurn(d,c));
        System.out.println("    Dead");
    }

    



}
