import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.Socket;

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
        c= new Card("Skulls",2,0);
        System.out.println("With 5 skulls in your set with a 2 Skull card,you should be dedcution 700 from everyone else ");
        Assertions.assertEquals(700,g.IslePts(dice,c));

    }

    @Test
    public void ValidMoves()
    {
        System.out.println("\n\nRUNNNING TESTS TO SEE IF MOVES ARE VALID");
        Dice d1 = new Dice("Skull");
        Dice d2 = new Dice("Sword");
        for(int i=0; i<8;i++)
        {
            dice[i] = d2;
        }
        dice[1] =d1;
        dice[2] =d1;

        int[] pos = new int[2];
        pos[0] = 2;
        pos[1] = 3;
        c = new Card("Coin",1,0);
        System.out.println("If my dice 2 and 3 are skulls i cannot re-reoll them, if i have a coin card");
        Assertions.assertEquals(0,g.isMoveValid(dice,pos,c));

        pos[1]=4;
        c = new Card("Sorceress",1,0);
        System.out.println("I can re-reroll 1 of them if i have a sorceress card");
        Assertions.assertEquals(2,g.isMoveValid(dice,pos,c));

        pos[1] =3;
        System.out.println("I cannot re-roll 2 skulls even with a sorceress card");
        Assertions.assertEquals(0,g.isMoveValid(dice,pos,c));

        c = new Card("Coin",1,0);
        pos[0] = 7;
        pos[1] = 8;
        System.out.println("I can re-roll any non-skulls with any card");
        Assertions.assertEquals(1,g.isMoveValid(dice,pos,c));


    }


    @Test
    public void gameOver()
    {
        System.out.println("\n\nCHECKING GAME OVER SCENARIOS");
        int[] scores = new int[3];
        int[] turns = new int[3];

        scores[0] = 3100;
        scores[1] = 2500;
        scores[2] = 2700;

        turns[0] = 5;
        turns[1] = 5;
        turns[2] = 5;

        System.out.println("1st player has pts > 3000 after his turn, after 2nd and 3rd players turn, the game ends");
        Assertions.assertTrue(g.isGameOver(scores,turns));

        turns[2] = 4;
        System.out.println("1st Player has pts > 3000 after his turn, 2nd player has played but 3rd players has not");
        Assertions.assertFalse(g.isGameOver(scores,turns));

        System.out.println("Player 3 has pts > 3000 after his turn, and player 1 and 2 player another turn");
        scores[2] = 3100;
        Assertions.assertTrue(g.isGameOver(scores,turns));

    }


    @Test
    public void isPlayerDead()
    {
        System.out.println("\n\nTESTING PLAYER DIEING CONDITIONS");
        Dice d1 = new Dice("Skull");
        Dice d2 = new Dice("Sword");
        for(int i=0; i<8;i++)
        {
            dice[i] = d2;
        }
        dice[1] =d1;
        dice[2] =d1;
        c = new Card("Coin",1,0);
        System.out.println("6 swords and 2 skulls means you are not dead, with a coin FC");
        Assertions.assertFalse(g.endTurn(dice,c));

        dice[3] = d1;
        System.out.println("5 swords and 3 skulls means you are dead, with a coin FC");
        Assertions.assertTrue(g.endTurn(dice,c));

        c = new Card("Sorceress",1,0);
        System.out.println("5 swords and 3 skulls with a Sorceress means not dead yet");
        Assertions.assertFalse(g.endTurn(dice,c));

        dice[4] = d1;
        System.out.println("4 Swords and 4 skulls even with a Sorceress means dead");
        Assertions.assertTrue(g.endTurn(dice,c));


    }

    @Test
    public void shuffle()
    {
        System.out.println("\n\nTESTING SHUFFLING OF DICES\nBefore shuffle should be different than after shuffle");
        Dice[] d2;
        String one,two,three;
        dice = g.getGameDices();


        int[] pos = new int[3];
        pos[0] = 1;
        pos[1] = 3;
        pos[2] = 7;

        Boolean temp;
        one = dice[0].reveal();
        two = dice[2].reveal();
        three = dice[6].reveal();
        d2 = g.shuffleDice(dice,pos);


        temp = d2[0].reveal() == one;
        temp = temp & d2[2].reveal() == two;
        temp = temp & d2[6].reveal() == three;

        Assertions.assertFalse(temp);


    }










}
