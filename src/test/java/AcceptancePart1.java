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

    @Test
    public void Row56()
    {
        d = g.getGameDices();
        System.out.println("ROW 56: 4 coins, 2 skulls , 2 swords");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice coin  = new Dice("Coin");

        d[0]=d[1]=d[2]=d[3]= coin;
        d[4]=d[5] = sword;
        d[6]=d[7]=skull;

        c = new Card("Diamond",1,0);
        Assertions.assertEquals(700,g.regularPts(d,c));
        System.out.println("        Scored 700 pts");
    }


    @Test
    public void Row57()
    {
        d = g.getGameDices();
        System.out.println("ROW 57: 3 swords, 4 parrots , 1 skull");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot  = new Dice("Parrot");


        d[0]=d[1]=d[2] = sword;
        d[3]=d[4]=d[5]=d[6] = parrot;
        d[7] = skull;
        c = new Card("Coin",1,0);
        Assertions.assertEquals(400,g.regularPts(d,c));
        System.out.println("        Scored 400 pts");
    }

    @Test
    public void Row58()
    {
        d = g.getGameDices();
        System.out.println("ROW 58: 2 coin and parrots, 3 swords , 1 skull");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot  = new Dice("Parrot");
        Dice coin = new Dice("Coin");
        c = new Card("Coin",1,0);

        d[0]=d[1] = coin;
        d[2]=d[3] =parrot;
        d[4]=d[5]=d[6]= sword;
        d[7] = skull;

        System.out.println("        Re-rolling parrots");
        int[] pos = {3,4};
        d = g.shuffleDice(d,pos);
        d[2]= coin;
        d[3] = sword;

        Assertions.assertEquals(800,g.regularPts(d,c));
        System.out.println("        Scored 800 pts");
    }


    @Test
    public void Row59()
    {
        d = g.getGameDices();
        System.out.println("ROW 59: 2 coin and parrots, 3 swords , 1 skull (Captain FC)");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot  = new Dice("Parrot");
        Dice coin = new Dice("Coin");
        c = new Card("Captain",1,0);

        d[0]=d[1] = coin;
        d[2]=d[3] =parrot;
        d[4]=d[5]=d[6]= sword;
        d[7] = skull;

        System.out.println("        Re-rolling parrots");
        int[] pos = {3,4};
        d = g.shuffleDice(d,pos);
        d[2]= coin;
        d[3] = sword;

        Assertions.assertEquals(1200,g.regularPts(d,c));
        System.out.println("        Scored 1200 pts");

    }


    @Test
    public void Row60()
    {
        d = g.getGameDices();
        System.out.println("ROW 60: 1 skull , 2 monkeys and parrots, 3 swords");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot  = new Dice("Parrot");
        Dice monkey = new Dice("Monkey");
        c = new Card("Coin",1,0);

        d[0] =skull;
        d[1]=d[2] = parrot;
        d[3]=d[4] = monkey;
        d[5]=d[6]=d[7] = sword;
        System.out.println("        Re-rolling 2 monkeys");
        int[] pos={4,5};
        d = g.shuffleDice(d,pos);
        d[3]= skull;
        d[4] = sword;

        System.out.println("        Re-rolling 2 parrots");
        int[] pos2 = {2,3};
        d = g.shuffleDice(d,pos2);
        d[2] = sword;
        d[1] = new Dice("Monkey");


        Assertions.assertEquals(600,g.regularPts(d,c));
        System.out.println("        Scored 600 pts");

    }


    @Test
    public void Row62()
    {
        d = g.getGameDices();
        System.out.println("ROW 62: 6 monkeys and 2 skulls");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        c = new Card("Coin",1,0);

        d[0]=d[1]=d[2]=d[3]=d[4]=d[5] = monkey;
        d[6]=d[7] = skull;

        Assertions.assertEquals(1100,g.regularPts(d,c));
        System.out.println("        Scored 1100 pts");
    }

    @Test
    public void Row63()
    {
        d = g.getGameDices();
        System.out.println("ROW 63: 7 parrots and 1 skull");
        Dice skull = new Dice("Skull");
        Dice parrot = new Dice("Parrot");
        c = new Card("Coin",1,0);

        d[0]=d[1]=d[2]=d[3]=d[4]=d[5]=d[6] = parrot;
        d[7] = skull;

        Assertions.assertEquals(2100,g.regularPts(d,c));
        System.out.println("        Scored 2100 pts");
    }

    @Test
    public void Row64()
    {
        d = g.getGameDices();
        System.out.println("ROW 64: 8 coins");
        Dice coin = new Dice("Coin");
        c = new Card("Coin",1,0);

        for(int i=0;i<8;i++)
        {
            d[i] = coin;
        }

        Assertions.assertEquals(5400,g.regularPts(d,c));
        System.out.println("        Scored 5400 pts");

    }

    @Test
    public void Row65()
    {
        d = g.getGameDices();
        System.out.println("ROW 65: 8 coins + Diamond FC");
        Dice coin = new Dice("Coin");
        c = new Card("Diamond",1,0);

        for(int i=0;i<8;i++)
        {
            d[i] = coin;
        }
        Assertions.assertEquals(5400,g.regularPts(d,c));
        System.out.println("        Scored 5400 pts");
    }


    @Test
    public void Row66()
    {
        d = g.getGameDices();
        System.out.println("ROW 66: 8 swords + Captain FC");
        Dice sword = new Dice("Sword");
        c = new Card("Captain",1,0);

        for(int i=0;i<8;i++)
        {
            d[i] = sword;
        }

        Assertions.assertEquals(9000,g.regularPts(d,c));
        System.out.println("        Scored 9000 pts");
    }


    @Test
    public void Row67()
    {
        d = g.getGameDices();
        System.out.println("ROW 66: 8 swords + Captain FC");
        Dice sword = new Dice("Sword");
        Dice monkey = new Dice("Monkey");
        c = new Card("Coin",1,0);

        for(int i=0;i<6;i++)
        {
            d[i] = monkey;
        }
        d[6]=d[7] = sword;
        System.out.println("        Re-roll swords");
        int[] pos = {7,8};
        d = g.shuffleDice(d,pos);
        d[6]=d[7]=monkey;
        Assertions.assertEquals(4600,g.regularPts(d,c));
        System.out.println("        Scored 4600 pts");
    }
}
