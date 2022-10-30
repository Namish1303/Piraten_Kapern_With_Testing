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

    @Test
    public void Row46()
    {
        //d = g.getGameDices();
        System.out.println("ROW 46: 1 Skull, 4 Parrots, 3 Swords");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        Dice sword = new Dice("Sword");

        d[0] = skull;
        d[1] = d[2] = d[3] = d[4] = parrot;
        d[5]=d[6]=d[7] = sword;

        System.out.println("        Shuffling 3 swords");
        int[] pos = {6,7,8};
        d = g.shuffleDice(d,pos);

        d[5] =d[6]= skull;
        d[7] = sword;
        c = new Card("Coin",1,0);
        Assertions.assertTrue(g.endTurn(d,c));
        System.out.println("        Dead");


    }


    @Test
    public void Row47()
    {
        System.out.println("ROW 47: 2 Skull, 4 Parrots, 2 Swords");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        Dice sword = new Dice("Sword");

        d[0]=d[7] = skull;
        d[1] = d[2] = d[3] = d[4] = parrot;
        d[5]=d[6]= sword;

        System.out.println("        Shuffling 2 swords");
        int[] pos = {6,7};
        d = g.shuffleDice(d,pos);

        d[5]= skull;
        d[6] = sword;
        c = new Card("Coin",1,0);
        Assertions.assertTrue(g.endTurn(d,c));
        System.out.println("        Dead");
    }






}
