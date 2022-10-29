
import java.util.Random;

public class Dice {

    String[] values;
    String face;
    boolean inChest;
    public Dice()
    {
        values = new String[6];
        values[0] ="Monkey";
        values[1] = "Parrot";
        values[2] = "Skull";
        values[3] = "Coin";
        values[4] = "Diamond";
        values[5] = "Sword";
        //values = ["Monkey","Parrot","Skull","Coin","Diamond","Sword"];
        face = "Sword";
        inChest = false;
    }

    public Dice(String n)
    {
        face = n;
    }

    public void randomize()
    {
        Random rand = new Random();
        int side = rand.nextInt(6);
        //System.out.println(side);
        face = values[side];
    }

    public String reveal()
    {
        return face;
    }

    public void InTheChest()
    {
        inChest = true;
    }

    public void OutOfChest()
    {
        inChest = false;
    }


}
