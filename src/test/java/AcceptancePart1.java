import org.junit.jupiter.api.Assertions;
import org.junit.Test;

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
        System.out.println("        Dead");
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


    @Test
    public void Row48()
    {
        System.out.println("ROW 48: 1 Skull, 4 Parrots, 3 Swords");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        Dice sword = new Dice("Sword");

        d[0] = skull;
        d[1] = d[2] = d[3] = d[4] = parrot;
        d[5]=d[6]=d[7]= sword;

        System.out.println("        Shuffling 3 swords");
        int[] pos = {6,7,8};
        d = g.shuffleDice(d,pos);

        Dice monkey = new Dice("Monkey");
        d[5]= skull;
        d[6] =d[7]= monkey;

        int[] pos2 = {7,8};
        System.out.println("        Shuffling 2 monkeys");
        d = g.shuffleDice(d,pos2);

        d[6] = skull;
        d[7] = monkey;
        c = new Card("Coin",1,0);
        Assertions.assertTrue(g.endTurn(d,c));
        System.out.println("        Dead");
    }


    @Test
    public void Row50()
    {
        System.out.println("ROW 50: 1 Skull, 2 Parrot, 3 Swords, 2 Coins");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        Dice sword = new Dice("Sword");
        Dice coin = new Dice("Coin");

        d = g.getGameDices();
        d[0]= skull;
        d[1]=d[2] = parrot;
        d[3]=d[4]=d[5] = sword;
        d[6]=d[7] = coin;

        System.out.println("        Re-rolling parrots");
        int[] pos = {2,3};
        d = g.shuffleDice(d,pos);
        d[1]=d[2] = coin;

        System.out.println("        Re-rolling swords");
        int[] pos2 = {4,5,6};
        d = g.shuffleDice(d,pos2);
        d[3]=d[4]=d[5] = coin;
        c = new Card("Coin",1,0);
        System.out.println("        1 Skull, 7 Coins with a Coin FC");
        Assertions.assertEquals(4800,g.regularPts(d,c));
        System.out.println("        Scored 4800 pts");

    }

    @Test
    public void Row52()
    {
        System.out.println("ROW 52: 2 (monkey,parrot,diamond,coin with Captain FC");
        Dice monkey = new Dice("Monkey");
        Dice parrot = new Dice("Parrot");
        Dice diamond = new Dice("Diamond");
        Dice coin = new Dice("Coin");

        d[0]=d[1]= monkey;
        d[2]=d[3] = parrot;
        d[4]=d[5] = diamond;
        d[6]=d[7]=coin;
        c = new Card("Captain",1,0);

        Assertions.assertEquals(800,g.regularPts(d,c));
        System.out.println("        Scored 800 pts");
    }

    @Test
    public void Row53()
    {
        System.out.println("ROW 53: 2(monkey/skull/sword/parrot)");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        Dice sword = new Dice("Sword");
        Dice monkey = new Dice("Monkey");

        d[0]=d[1]= monkey;
        d[2]=d[3] = parrot;
        d[4]=d[5] = sword;
        d[6]=d[7]=skull;

        System.out.println("        Re-rolling parrots");
        int[] pos = {3,4};
        d = g.shuffleDice(d,pos);
        d[2] = monkey;
        d[3] = sword;
        c = new Card("Coin",1,0);
        Assertions.assertEquals(300,g.regularPts(d,c));
        System.out.println("        Scored 300 pts");


    }

    @Test
    public void Row54()
    {
        System.out.println("ROW 54: 3(monkey/sword) + 2 Skulls");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice monkey = new Dice("Monkey");

        d[0]=d[1]=skull;
        d[2]=d[3]=d[4]= sword;
        d[5]=d[6]=d[7] = monkey;
        c = new Card("Coin",1,0);
        Assertions.assertEquals(300,g.regularPts(d,c));
        System.out.println("        Scored 300 pts");

    }

    @Test
    public void Row55()
    {
        d = g.getGameDices();
        System.out.println("ROW 55: 3 diamonds, 2 Skulls, 1(monkey/sword/parrot)");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        Dice sword = new Dice("Sword");
        Dice monkey = new Dice("Monkey");
        Dice diamond = new Dice("Diamond");

        d[0]=d[1]=d[2] = diamond;
        d[3]=d[4] = skull;
        d[5] = monkey;
        d[6]= parrot;
        d[7] = sword;
        c = new Card("Coin",1,0);
        Assertions.assertEquals(500,g.regularPts(d,c));
        System.out.println("        Scored 500 pts");

    }
}
