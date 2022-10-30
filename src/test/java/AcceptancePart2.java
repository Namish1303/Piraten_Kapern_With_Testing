import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AcceptancePart2 {

    Dice[] d;
    Card c;
    Game g;

    public AcceptancePart2()
    {
        d = new Dice[8];
        c= new Card("Coin",1,0);
        g = new Game();
        System.out.println("\n\n ACCEPTANCE TESTING PART 2");
    }

    @Test
    public void Row77()
    {
        d = g.getGameDices();
        System.out.println("ROW 77: 2 diamonds, 1 (sword/monkey/coin), 3 parrots");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot  = new Dice("Parrot");
        Dice monkey = new Dice("Monkey");
        Dice diamond = new Dice("Diamond");
        Dice coin = new Dice("Coin");
        c = new Card("Sorceress",1,0);

        d[0]=d[1] = diamond;
        d[2]=coin;
        d[3] = sword;
        d[4] =monkey;
        d[5]=d[6]=d[7] = parrot;

        int[] pos = {6,7,8};
        System.out.println("        Re-rolling parrots");
        d = g.shuffleDice(d,pos);
        d[5] = skull;
        d[6]=d[7]=monkey;

        System.out.println("        To check if skull can be rolled");
        int[] pos2 ={6};
        Assertions.assertEquals(2,g.isMoveValid(d,pos2,c));

        if(g.isMoveValid(d,pos2,c) == 2 )
        {
            c = new Card("null",1,0);
            d = g.shuffleDice(d,pos2);
            d[5] = monkey;
        }


        Assertions.assertEquals(500,g.regularPts(d,c));
        System.out.println("        Scored 500 pts;");



    }

    @Test
    public void Row78()
    {
        d = g.getGameDices();
        System.out.println("ROW 78: 3 skulls, 3 parrots, 2 swords ");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot  = new Dice("Parrot");

        c = new Card("Sorceress",1,0);

        d[0]=new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Skull");
        d[3]=d[4]=d[5] = parrot;
        d[6]=d[7] = sword;


        System.out.println("        To check if skull can be rolled then reroll");
        int[] pos2 ={3};
        Assertions.assertEquals(2,g.isMoveValid(d,pos2,c));

        if(g.isMoveValid(d,pos2,c) == 2 ) {
            c = new Card("null", 1, 0);
            d = g.shuffleDice(d, pos2);
            d[2] = parrot;
        }
        int[] pos = {7,8};
        System.out.println("        Re-rolling 2 swords");
        d = g.shuffleDice(d,pos);
        d[6]=d[7] = parrot;



        Assertions.assertEquals(1000,g.regularPts(d,c));
    }


    @Test
    public void Row79()
    {
        d = g.getGameDices();
        System.out.println("ROW 79: 1 skull, 4 parrots, 3 monkeys  ");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        Dice parrot  = new Dice("Parrot");

        c = new Card("Sorceress",1,0);
        d[0] = new Dice("Skull");
        d[1]=d[2]=d[3]=d[4] = parrot;
        d[5]=d[6]=d[7] = monkey;

        int[] pos = {6,7,8};
        System.out.println("        Re-rolling monkeys");
        d = g.shuffleDice(d,pos);
        d[5] = skull;
        d[6]=d[7]= parrot;

        System.out.println("        Check if the skull can be re-rolled then re-roll it");
        int[] pos2 = {6};
        Assertions.assertEquals(2,g.isMoveValid(d,pos2,c));

        if(g.isMoveValid(d,pos2,c) == 2 )
        {
            c = new Card("null",1,0);
            d = g.shuffleDice(d,pos2);
            d[5] = parrot;
        }
        for(int i=0;i<8;i++)
        {
            System.out.println(d[i].reveal());
        }
        Assertions.assertEquals(2000,g.regularPts(d,c));


    }


    @Test
    public void Row82()
    {
        d = g.getGameDices();
        System.out.println("ROW 82: 3 parrots, 3 monkeys , 1 coin, 1 skull + Monkey Business FC  ");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        Dice parrot  = new Dice("Parrot");
        Dice coin  = new Dice("Coin");

        c = new Card("Monkey Business",1,0);

        d[0]=d[1]=d[2] =monkey;
        d[3]=d[4]=d[5] = parrot;
        d[6] = skull;
        d[7] = coin;

        Assertions.assertEquals(1100,g.regularPts(d,c));
        System.out.println("        Scored 1100 pts");
    }

    @Test
    public void Row83()
    {
        d = g.getGameDices();
        System.out.println("ROW 83: 2 (monkeys/parrots/swords/coins) + Monkey Business FC  ");
        Dice sword = new Dice("Sword");
        Dice monkey = new Dice("Monkey");
        Dice parrot  = new Dice("Parrot");
        Dice coin  = new Dice("Coin");

        c = new Card("Monkey Business",1,0);

        d[0]=d[1] =monkey;
        d[2]=d[3] = parrot;
        d[4]=d[5] = sword;
        d[6]=d[7] = coin;

        int[] pos = {5,6};
        System.out.println("        Re-rolling 2 swords");
        d = g.shuffleDice(d,pos);
        d[4] = monkey;
        d[5] = parrot;

        Assertions.assertEquals(1700,g.regularPts(d,c));
        System.out.println("        Scored 1700 pts");
    }

    @Test
    public void Row84()
    {
        d = g.getGameDices();
        System.out.println("ROW 84: 3 skulls + 3 monkeys + 2 parrots + Monkey Business FC  ");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        Dice parrot  = new Dice("Parrot");
        c = new Card("Monkey Business",1,0);

        d[0]=d[1] =monkey;
        d[2]=d[3] = parrot;
        d[4]=d[5]=d[6]=skull;
        d[7] =monkey;

        Assertions.assertTrue(g.endTurn(d,c));
        System.out.println("        Died");

    }

    @Test
    public void Row97()
    {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Coin");
        d[7] = new Dice("Parrot");
        System.out.println("ROW 97: 3 monkeys + 3 swords + 1 coin + 1 parrots + Coin FC  ");

        Assertions.assertEquals(400,g.regularPts(d,c));
        System.out.println("        Scored 400 pts");



    }

    @Test
    public void Row98()
    {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Coin");
        d[7] = new Dice("Coin");
        System.out.println("ROW 98: 3 monkeys + 3 swords + 2 Coins + Captain FC  ");
        c = new Card("Captain",1,0);
        Assertions.assertEquals(1800,g.regularPts(d,c));
        System.out.println("        Scored 1800 pts");
    }

    @Test
    public void Row99()
    {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Diamond");
        System.out.println("ROW 99: 3 monkeys + 4 swords + 1 Diamond + Coin FC  ");

        Assertions.assertEquals(1000,g.regularPts(d,c));
        System.out.println("        Scored 1000 pts");
    }


    @Test
    public void Row100()
    {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Monkey");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Parrot");
        d[6] = new Dice("Parrot");
        d[7] = new Dice("Coin");
        System.out.println("ROW 100: 4 monkeys + 1 swords + 1 Coin + 2 Parrots (with 2 Sword Sea Battle)  ");
        c = new Card("Sea Battle",2,300);

        System.out.println("        Re-roll 2 parrots");
        int[] pos = {6,7};
        d = g.shuffleDice(d,pos);
        d[5] = new Dice("Coin");
        d[6] = new Dice("Sword");
        Assertions.assertEquals(1200,g.SeaBattlePts(d,c));
        System.out.println("        Scored 1200 pts");
    }


    @Test
    public void Row103()
    {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Parrot");
        d[3] = new Dice("Coin");
        d[4] = new Dice("Coin");
        d[5] = new Dice("Diamond");
        d[6] = new Dice("Diamond");
        d[7] = new Dice("Diamond");
        System.out.println("ROW 103: 2 monkeys, 1 parrot , 2 coins , 3 diamonds ");
        c = new Card("Monkey Business",1,0);

        Assertions.assertEquals(1200,g.regularPts(d,c));
        System.out.println("        Scored 1200 pts");

    }



    @Test
    public void Row106()
    {
        d = g.getGameDices();
        d[0] = new Dice("Skull");
        d[1] = new Dice("Sword");
        d[2] = new Dice("Sword");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Sword");
        System.out.println("ROW 106: 1 Skull , 7 Swords (2 Skull FC) ");
        c = new Card("Skulls",2,0);

        Assertions.assertTrue(g.endTurn(d,c));
        System.out.println("        Dead");

    }

}
