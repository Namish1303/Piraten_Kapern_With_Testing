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


    @Test
    public void CalculatePoints()
    {
        System.out.println("\n\nRUNNING TESTS FOR CALCULATING POINTS");
        Dice d1 = new Dice("Coin");
        Dice d2 = new Dice("Sword");
        Dice d3 = new Dice("Diamond");

        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d1;
        dice[4] = d1;
        dice[5] = d3;
        dice[6] = d2;
        dice[7] = d3;
        // 2 swords, 3 coins, 3 diamonds

        c = new Card("Coin",1,0);

        System.out.println("2 swords, 3 coins, 3 diamonds with a Coin card should result in (300+300+100+200+100 = 1000)");
        Assertions.assertEquals(1000,g.regularPts(dice,c));

        c = new Card("Captain",1,0);
        System.out.println("2 swords, 3 coins, 3 diamonds with a Captain card should result in (300+300+100+100 *2 = 1600)");
        Assertions.assertEquals(1600,g.regularPts(dice,c));


        dice[1] = d3;
        dice[6] = d1;
        c = new Card("Monkey Bussiness",1,0);
        System.out.println("4 Coins and 4 diamonds with Monkey Bussiness Card result in (200+200+400+400+500 = 1700" );
        Assertions.assertEquals(1700,g.regularPts(dice,c));


        for(int i=0; i<8;i++)
        {
            dice[i] = d2;
        }
        c = new Card("Coin",1,0);
        System.out.println("8 swords with a coin face card should get (4000+100+500)");
        Assertions.assertEquals(4600,g.regularPts(dice,c));

     }


     @Test
    public void IsIOD()
     {
         System.out.println("\n\nCHECK WHETHER SHOULD GO TO ISLE OF DEAD OR NOT");
         Dice d1 = new Dice("Skull");
         Dice d2 = new Dice("Sword");
         for(int i=0; i<8;i++)
         {
             dice[i] = d2;
         }

         dice[1]=d1;
         dice[2]=d1;
         c = new Card("Chest",1,0);
         System.out.println("Having 2 skulls in your set should not send you to Isle of Dead");
         Assertions.assertFalse(g.isIsleOfDead(dice,1,c));

         dice[3]=d1;
         dice[4]=d1;
         System.out.println("Having 4 skulls in your set should not send you to Isle of Dead after your 1st re rell");
         Assertions.assertFalse(g.isIsleOfDead(dice,2,c));

         System.out.println("Having 4 skulls in your set should not send you to Isle of Dead before your 1st re rell");
         Assertions.assertTrue(g.isIsleOfDead(dice,1,c));

         c = new Card("Skulls",2,0);
         System.out.println("Having 2 skulls in set + 2 skulls card sen you to Isle of Dead before 1st re-roll");
         Assertions.assertTrue(g.isIsleOfDead(dice,1,c));

     }

    @Test
    public void IODpts()
    {
        System.out.println("\n\nTESTS TO SEE THE DEDUCTIONS FROM ISLE OF DEAD");

        Dice d1 = new Dice("Skull");
        Dice d2 = new Dice("Sword");
        for(int i=0; i<8;i++)
        {
            dice[i] = d1;
        }
        dice[1] =d2;
        dice[2] =d2;
        c = new Card("Coin",1,0);
        System.out.println("With 6 skulls in your set with a coin card you should be deduction 600 from everyone else");
        Assertions.assertEquals(600,g.IslePts(dice,c));

        c = new Card("Captain",1,0);
        System.out.println("With 6 skulls in your set with a Captain card you should be deductiong 1200 from everyone else");
        Assertions.assertEquals(1200,g.IslePts(dice,c));
        
        dice[3] = d2;
        System.out.println("With 5 skulls in your set with a 2 Skull card,you should be dedcution 700 from everyone else ");
        Assertions.assertEquals(700,g.IslePts(dice,c));

    }











}
