import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AcceptancePart2 {

    Dice[] d;
    Card c;
    Game g;

    public AcceptancePart2() {
        d = new Dice[8];
        c = new Card("Coin", 1, 0);
        g = new Game();
        System.out.println("\n\n ACCEPTANCE TESTING PART 2");
    }

    @Test
    public void Row77() {
        d = g.getGameDices();
        System.out.println("ROW 77: 2 diamonds, 1 (sword/monkey/coin), 3 parrots");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot = new Dice("Parrot");
        Dice monkey = new Dice("Monkey");
        Dice diamond = new Dice("Diamond");
        Dice coin = new Dice("Coin");
        c = new Card("Sorceress", 1, 0);

        d[0] = d[1] = diamond;
        d[2] = coin;
        d[3] = sword;
        d[4] = monkey;
        d[5] = d[6] = d[7] = parrot;

        int[] pos = {6, 7, 8};
        System.out.println("        Re-rolling parrots");
        d = g.shuffleDice(d, pos);
        d[5] = skull;
        d[6] = d[7] = monkey;

        System.out.println("        To check if skull can be rolled");
        int[] pos2 = {6};
        Assertions.assertEquals(2, g.isMoveValid(d, pos2, c));

        if (g.isMoveValid(d, pos2, c) == 2) {
            c = new Card("null", 1, 0);
            d = g.shuffleDice(d, pos2);
            d[5] = monkey;
        }


        Assertions.assertEquals(500, g.regularPts(d, c));
        System.out.println("        Scored 500 pts;");


    }

    @Test
    public void Row78() {
        d = g.getGameDices();
        System.out.println("ROW 78: 3 skulls, 3 parrots, 2 swords ");
        Dice skull = new Dice("Skull");
        Dice sword = new Dice("Sword");
        Dice parrot = new Dice("Parrot");

        c = new Card("Sorceress", 1, 0);

        d[0] = new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Skull");
        d[3] = d[4] = d[5] = parrot;
        d[6] = d[7] = sword;


        System.out.println("        To check if skull can be rolled then reroll");
        int[] pos2 = {3};
        Assertions.assertEquals(2, g.isMoveValid(d, pos2, c));

        if (g.isMoveValid(d, pos2, c) == 2) {
            c = new Card("null", 1, 0);
            d = g.shuffleDice(d, pos2);
            d[2] = parrot;
        }
        int[] pos = {7, 8};
        System.out.println("        Re-rolling 2 swords");
        d = g.shuffleDice(d, pos);
        d[6] = d[7] = parrot;


        Assertions.assertEquals(1000, g.regularPts(d, c));
    }


    @Test
    public void Row79() {
        d = g.getGameDices();
        System.out.println("ROW 79: 1 skull, 4 parrots, 3 monkeys  ");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        Dice parrot = new Dice("Parrot");

        c = new Card("Sorceress", 1, 0);
        d[0] = new Dice("Skull");
        d[1] = d[2] = d[3] = d[4] = parrot;
        d[5] = d[6] = d[7] = monkey;

        int[] pos = {6, 7, 8};
        System.out.println("        Re-rolling monkeys");
        d = g.shuffleDice(d, pos);
        d[5] = skull;
        d[6] = d[7] = parrot;

        System.out.println("        Check if the skull can be re-rolled then re-roll it");
        int[] pos2 = {6};
        Assertions.assertEquals(2, g.isMoveValid(d, pos2, c));

        if (g.isMoveValid(d, pos2, c) == 2) {
            c = new Card("null", 1, 0);
            d = g.shuffleDice(d, pos2);
            d[5] = parrot;
        }
        Assertions.assertEquals(2000, g.regularPts(d, c));


    }


    @Test
    public void Row82() {
        d = g.getGameDices();
        System.out.println("ROW 82: 3 parrots, 3 monkeys , 1 coin, 1 skull + Monkey Business FC  ");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        Dice parrot = new Dice("Parrot");
        Dice coin = new Dice("Coin");

        c = new Card("Monkey Business", 1, 0);

        d[0] = d[1] = d[2] = monkey;
        d[3] = d[4] = d[5] = parrot;
        d[6] = skull;
        d[7] = coin;

        Assertions.assertEquals(1100, g.regularPts(d, c));
        System.out.println("        Scored 1100 pts");
    }

    @Test
    public void Row83() {
        d = g.getGameDices();
        System.out.println("ROW 83: 2 (monkeys/parrots/swords/coins) + Monkey Business FC  ");
        Dice sword = new Dice("Sword");
        Dice monkey = new Dice("Monkey");
        Dice parrot = new Dice("Parrot");
        Dice coin = new Dice("Coin");

        c = new Card("Monkey Business", 1, 0);

        d[0] = d[1] = monkey;
        d[2] = d[3] = parrot;
        d[4] = d[5] = sword;
        d[6] = d[7] = coin;

        int[] pos = {5, 6};
        System.out.println("        Re-rolling 2 swords");
        d = g.shuffleDice(d, pos);
        d[4] = monkey;
        d[5] = parrot;

        Assertions.assertEquals(1700, g.regularPts(d, c));
        System.out.println("        Scored 1700 pts");
    }

    @Test
    public void Row84() {
        d = g.getGameDices();
        System.out.println("ROW 84: 3 skulls + 3 monkeys + 2 parrots + Monkey Business FC  ");
        Dice skull = new Dice("Skull");
        Dice monkey = new Dice("Monkey");
        Dice parrot = new Dice("Parrot");
        c = new Card("Monkey Business", 1, 0);

        d[0] = d[1] = monkey;
        d[2] = d[3] = parrot;
        d[4] = d[5] = d[6] = skull;
        d[7] = monkey;

        Assertions.assertTrue(g.endTurn(d, c));
        System.out.println("        Died");

    }

    @Test
    public void Row97() {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Coin");
        d[7] = new Dice("Parrot");
        System.out.println("ROW 97: 3 monkeys + 3 swords + 1 coin + 1 parrots + Coin FC  ");

        Assertions.assertEquals(400, g.regularPts(d, c));
        System.out.println("        Scored 400 pts");


    }

    @Test
    public void Row98() {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Coin");
        d[7] = new Dice("Coin");
        System.out.println("ROW 98: 3 monkeys + 3 swords + 2 Coins + Captain FC  ");
        c = new Card("Captain", 1, 0);
        Assertions.assertEquals(1800, g.regularPts(d, c));
        System.out.println("        Scored 1800 pts");
    }

    @Test
    public void Row99() {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Diamond");
        System.out.println("ROW 99: 3 monkeys + 4 swords + 1 Diamond + Coin FC  ");

        Assertions.assertEquals(1000, g.regularPts(d, c));
        System.out.println("        Scored 1000 pts");
    }


    @Test
    public void Row100() {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Monkey");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Parrot");
        d[6] = new Dice("Parrot");
        d[7] = new Dice("Coin");
        System.out.println("ROW 100: 4 monkeys + 1 swords + 1 Coin + 2 Parrots (with 2 Sword Sea Battle)  ");
        c = new Card("Sea Battle", 2, 300);

        System.out.println("        Re-roll 2 parrots");
        int[] pos = {6, 7};
        d = g.shuffleDice(d, pos);
        d[5] = new Dice("Coin");
        d[6] = new Dice("Sword");
        Assertions.assertEquals(1200, g.SeaBattlePts(d, c));
        System.out.println("        Scored 1200 pts");
    }


    @Test
    public void Row103() {
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Parrot");
        d[3] = new Dice("Coin");
        d[4] = new Dice("Coin");
        d[5] = new Dice("Diamond");
        d[6] = new Dice("Diamond");
        d[7] = new Dice("Diamond");
        System.out.println("ROW 103: 2 monkeys, 1 parrot , 2 coins , 3 diamonds ");
        c = new Card("Monkey Business", 1, 0);

        Assertions.assertEquals(1200, g.regularPts(d, c));
        System.out.println("        Scored 1200 pts");

    }


    @Test
    public void Row106() {
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
        c = new Card("Skulls", 2, 0);

        Assertions.assertTrue(g.endTurn(d, c));
        System.out.println("        Dead");

    }

    @Test
    public void Row107() {
        d = g.getGameDices();
        d[0] = new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Sword");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Sword");
        System.out.println("ROW 107: 2 Skull , 6 Swords (1 Skull FC) ");
        c = new Card("Skulls", 1, 0);

        Assertions.assertTrue(g.endTurn(d, c));
        System.out.println("        Dead");
    }


    @Test
    public void Row108() {
        d = g.getGameDices();
        d[0] = new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Monkey");
        d[4] = new Dice("Monkey");
        d[5] = new Dice("Parrot");
        d[6] = new Dice("Parrot");
        d[7] = new Dice("Parrot");
        System.out.println("ROW 108: 2 Skull , 3(monkeys/parrots) (2 Skull FC) ");
        c = new Card("Skulls", 2, 0);

        System.out.println("        4 skulls , means players goes to isle of dead");
        Assertions.assertTrue(g.isIsleOfDead(d, 1, c));

        System.out.println("        Re-roll 3 parrots");
        int[] pos = {6, 7, 8};
        d = g.shuffleDice(d, pos);
        d[5] = new Dice("Skull");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Sword");

        System.out.println("        Re-rolling sword and 3 monkeys");
        int[] pos2 = {3, 4, 5, 8};
        d = g.shuffleDice(d, pos2);
        d[2] = new Dice("Skull");
        d[3] = new Dice("Skull");
        d[4] = new Dice("Skull");
        d[7] = new Dice("Sword");

        Assertions.assertEquals(900, g.IslePts(d, c));
        System.out.println("        Deduction of 900 for everyone else");


    }

    @Test
    public void Row110() {
        d = g.getGameDices();
        d[0] = new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Skull");
        d[3] = new Dice("Skull");
        d[4] = new Dice("Skull");
        d[5] = new Dice("Monkey");
        d[6] = new Dice("Monkey");
        d[7] = new Dice("Monkey");
        System.out.println("ROW 110: 5 skulls ,3 monkeys (Captain FC) ");
        c = new Card("Captain", 1, 0);

        System.out.println("        5 skulls , means players goes to isle of dead");
        Assertions.assertTrue(g.isIsleOfDead(d, 1, c));

        System.out.println("        Re-roll 3 monkeys");
        int[] pos = {6, 7, 8};
        d = g.shuffleDice(d, pos);
        d[5] = new Dice("Skull");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Coin");

        Assertions.assertEquals(1400, g.IslePts(d, c));
        System.out.println("        Deduction of 1400 for everyone else");

    }


    @Test
    public void Row111() {
        d = g.getGameDices();
        d[0] = new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Skull");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Sword");
        System.out.println("ROW 111: 3 skulls ,5 swords (2 Skull FC) ");
        c = new Card("Skulls", 2, 0);

        System.out.println("        Player should go to isle of dead");
        Assertions.assertTrue(g.isIsleOfDead(d, 1, c));

        System.out.println("        Re-roll 5 swords");
        int[] pos = {4, 5, 6, 7, 8};
        d = g.shuffleDice(d, pos);
        d[3] = new Dice("Coin");
        d[4] = new Dice("Coin");
        d[5] = new Dice("Coin");
        d[6] = new Dice("Coin");
        d[7] = new Dice("Coin");

        Assertions.assertEquals(500, g.IslePts(d, c));
        System.out.println("        Deduction of 500 for everyone else");

    }


    @Test
    public void Row114() {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Monkey");
        d[4] = new Dice("Skull");
        d[5] = new Dice("Skull");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Sword");
        System.out.println("ROW 114: 4 monkeys ,3 skulls, 1 sword (2 Sword FC) ");
        c = new Card("Sea Battle", 2, 300);

        Assertions.assertTrue(g.endTurn(d, c));
        Assertions.assertEquals(-300, g.SeaBattlePts(d, c));
        System.out.println("        Deduction of 300 points");
    }

    @Test
    public void Row115() {
        d = g.getGameDices();
        d[0] = new Dice("Parrot");
        d[1] = new Dice("Parrot");
        d[2] = new Dice("Parrot");
        d[3] = new Dice("Parrot");
        d[4] = new Dice("Skull");
        d[5] = new Dice("Skull");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Sword");
        System.out.println("ROW 115: 2 Swords ,2 skulls, 4 parrots (3 Sword FC) ");
        c = new Card("Sea Battle", 3, 500);

        System.out.println("        Re-roll 4 parrots");
        int[] pos = {1, 2, 3, 4};
        d = g.shuffleDice(d, pos);
        d[0] = new Dice("Skull");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Skull");
        d[3] = new Dice("Skull");

        Assertions.assertTrue(g.endTurn(d, c));
        Assertions.assertEquals(-500, g.SeaBattlePts(d, c));
        System.out.println("        Deduction of 500 points");


    }


    @Test
    public void Row116() {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Sword");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Skull");
        d[5] = new Dice("Skull");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Sword");
        System.out.println("ROW 116: 2 monkeys, 3 skulls ,3 swords (4sword FC) ");
        c = new Card("Sea Battle", 4, 1000);

        Assertions.assertTrue(g.endTurn(d, c));
        Assertions.assertEquals(-1000, g.SeaBattlePts(d, c));
        System.out.println("        Deduction of 1000 points");
    }

    @Test
    public void Row117(){
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Parrot");
        d[5] = new Dice("Parrot");
        d[6] = new Dice("Coin");
        d[7] = new Dice("Sword");
        System.out.println("ROW 117: 3 monkeys, 1 Coin, 2 Parrot ,2 swords (2sword FC) ");
        c = new Card("Sea Battle", 2, 300);

        Assertions.assertEquals(500,g.SeaBattlePts(d,c));
        System.out.println("        Scored 500 points");

    }

    @Test
    public void Row118()
    {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Parrot");
        d[5] = new Dice("Parrot");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Sword");
        System.out.println("ROW 118: 4 monkeys, 1 Skull, 2 Parrot ,1 swords (2sword FC) ");
        c = new Card("Sea Battle", 2, 300);

        System.out.println("        Re-roll 2 parrots");
        int[] pos = {5,6};
        d = g.shuffleDice(d,pos);
        d[5] = new Dice("Sword");
        d[4] = new Dice("Skull");
        Assertions.assertEquals(500,g.SeaBattlePts(d,c));
        System.out.println("        Scored 500 points");
    }


    @Test
    public void Row120()
    {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Sword");
        System.out.println("ROW 120: 3 monkeys, 1 Skull, 4 swords (3sword FC) ");
        c = new Card("Sea Battle", 3, 500);

        Assertions.assertEquals(800,g.SeaBattlePts(d,c));
        System.out.println("        Scored 800 points");
    }

    @Test
    public void Row121()
    {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Monkey");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Skull");
        d[7] = new Dice("Skull");
        System.out.println("ROW 121: 4 monkeys, 2 Skull, 2 swords (3sword FC) ");
        c = new Card("Sea Battle", 3, 500);

        System.out.println("        Re-roll 4 monkeys");
        int[] pos = {1,2,3,4};
        d = g.shuffleDice(d,pos);
        d[0] = new Dice("Sword");
        d[1] = new Dice("Skull");
        d[2] = new Dice("Sword");
        d[3] = new Dice("Skull");
        Assertions.assertEquals(-500,g.SeaBattlePts(d,c));
        System.out.println("        Deducted 500 points");
    }


    @Test
    public void Row123()
    {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Sword");
        d[5] = new Dice("Sword");
        d[6] = new Dice("Sword");
        d[7] = new Dice("Skull");
        System.out.println("ROW 123: 3 monkeys, 1 Skull, 4 swords (4sword FC) ");
        c = new Card("Sea Battle", 4, 1000);

        Assertions.assertEquals(1300,g.SeaBattlePts(d,c));
        System.out.println("        Scored 1300 pts");
    }

    @Test
    public void Row124()
    {
        d = g.getGameDices();
        d[0] = new Dice("Monkey");
        d[1] = new Dice("Monkey");
        d[2] = new Dice("Monkey");
        d[3] = new Dice("Sword");
        d[4] = new Dice("Skull");
        d[5] = new Dice("Diamond");
        d[6] = new Dice("Parrot");
        d[7] = new Dice("Parrot");
        System.out.println("ROW 124: 3 monkeys, 1 sword, 1 skull, 1 diamond, 2 parrots  (4sword FC) ");
        c = new Card("Sea Battle", 4, 1000);


        System.out.println("        Re-roll 2 parrots");
        int[] pos = {7,8};
        d = g.shuffleDice(d,pos);
        d[6] = new Dice("Sword");
        d[7] = new Dice("Sword");


        System.out.println("        Re-roll 3 monkeys");
        int[] pos2 = {1,2,3};
        d = g.shuffleDice(d,pos2);
        d[1] = new Dice("Sword");
        d[0] = new Dice("Parrot");
        d[2] = new Dice("Parrot");


        Assertions.assertEquals(1300,g.SeaBattlePts(d,c));
        System.out.println("        Scored 1300 points");
    }


    @Test
    public void Row87()
    {
        d = g.getGameDices();
        d[0] = new Dice("Parrot");
        d[1] = new Dice("Sword");
        d[2] = new Dice("Sword");
        d[3] = new Dice("Coin");
        d[4] = new Dice("Diamond");
        d[5] = new Dice("Diamond");
        d[6] = new Dice("Parrot");
        d[7] = new Dice("Parrot");
        System.out.println("ROW 87: 3 parrots, 2 swords, 2 diamonds, 1 coin");
        c = new Card("Chest",1,0);

        System.out.println("        Put coin and diamond in chest");
        d[4].InTheChest();
        d[5].InTheChest();
        d[3].InTheChest();

        System.out.println("        Re-roll 2 swords");
        int[] pos = {2,3};
        d = g.shuffleDice(d,pos);
        d[1] = new Dice("Parrot");
        d[2] = new Dice("Parrot");

        d[0].InTheChest();
        d[1].InTheChest();
        d[2].InTheChest();
        d[6].InTheChest();
        d[7].InTheChest();
        System.out.println("        Put parrots in chest and take out diamonds and coins");
        d[4].OutOfChest();
        d[5].OutOfChest();
        d[3].OutOfChest();

        System.out.println("        Re-roll 3 dices");
        int[] pos2 = {4,5,6};
        d = g.shuffleDice(d,pos2);
        d[4] = new Dice("Skull");
        d[5] = new Dice("Coin");
        d[3] = new Dice("Parrot");

        Assertions.assertEquals(1100,g.ChestPts(d,c));
        System.out.println("        Scored 1100 pts");


    }

    @Test
    public void Row92()
    {
        d = g.getGameDices();
        d[0] = new Dice("Parrot");
        d[1] = new Dice("Coin");
        d[2] = new Dice("Coin");
        d[3] = new Dice("Coin");
        d[4] = new Dice("Skull");
        d[5] = new Dice("Skull");
        d[6] = new Dice("Parrot");
        d[7] = new Dice("Parrot");
        System.out.println("ROW 92: 3 parrots, 2 Skulls, 3 coin");
        c = new Card("Chest",1,0);
        System.out.println("        put 3 coins in chest");

        d[1].InTheChest();
        d[2].InTheChest();
        d[3].InTheChest();

        System.out.println("        Re-roll 3 parrots");
        int[] pos = {1,7,8};
        d = g.shuffleDice(d,pos);
        d[0] = new Dice("Diamond");
        d[6] = new Dice("Diamond");
        d[7] =new Dice("Coin");

        System.out.println("        Put coin in chest");
        d[7].InTheChest();

        System.out.println("        Re-roll 2 diamonds");
        int[] pos2 = {1,7};
        d = g.shuffleDice(d,pos2);
        d[0] = new Dice("Skull");
        d[6] = new Dice("Coin");

        Assertions.assertEquals(600,g.ChestPts(d,c));
        System.out.println("    Dead with a score of 600");


    }


}
