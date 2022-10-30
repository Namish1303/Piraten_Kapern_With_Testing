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


}
