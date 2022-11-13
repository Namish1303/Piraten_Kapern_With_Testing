import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// FIX NETWORKING

// SEA BATTLES

//
public class Game implements Serializable {
    Dice[] dice;
    CardDeck deck;
    Card current;

    public Game() {
        dice = new Dice[8];
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Dice();
        }
        deck = new CardDeck();

    }

    // function to calculate the points of collections
    public int collectionPts(int num)
    {
        switch (num){
            case 3:
                return 100;
            case 4:
                return 200;
            case 5:
                return 500;
            case 6:
                return 1000;
            case 7:
                return 2000;
            case 8:
                return 4000;
            case 9:
                return 4000;
        }
        return 0;
    }

    // function to send back 8 game dices
    public Dice[] getGameDices(){
        for(int i=0;i<8;i++)
        {
            dice[i].randomize();
        }

        return dice;
    }

    // function to send the top card from the deck
    public Card getGameCard()
    {
       return deck.reveal();
    }

    // function to convert a set of dices into a map for easy calculation
    public HashMap<String, Integer> DiceToCollection(Dice[] d)
    {
        HashMap<String,Integer> map = new HashMap<>();

        for(int i=0; i<d.length;i++)
        {
            if(map.containsKey(d[i].reveal()))
            {
                map.put(d[i].reveal(),map.get(d[i].reveal()) + 1);
            }
            else
            {
                map.put(d[i].reveal(),1);
            }
        }
        return map;
    }


    // function to calculate points in a regular scenario
    public int regularPts(Dice[] D, Card C)
    {
        Map<String,Integer> occurence = new HashMap<>();
        int pts = 0;
        for(int i = 0; i<D.length; i++)
        {
            //System.out.println(D[i].reveal());
            if(D[i].reveal().equals("Coin") || D[i].reveal().equals("Diamond"))
            {
                pts += 100;
            }

            if(occurence.containsKey(D[i].reveal()))
            {
                occurence.put(D[i].reveal(),occurence.get(D[i].reveal()) + 1);
            }
            else
            {
                occurence.put(D[i].reveal(),1);
            }
        }

        String cardName = C.reveal();
        //System.out.println("Card: " +cardName);
        if(cardName.equals("Coin"))
        {
            pts +=100;
            if(occurence.containsKey("Coin"))
            {
                occurence.put("Coin",occurence.get("Coin") + 1);
            }
            else
            {
                occurence.put("Coin",1);
            }
        }
        else if(cardName.equals("Diamond"))
        {
            pts +=100;
            if(occurence.containsKey("Diamond"))
            {
                occurence.put("Diamond",occurence.get("Diamond") + 1);
            }
            else
            {
                occurence.put("Diamond",1);
            }
        }

        //Monkey Business card conditions
        else if(cardName.equals("Monkey Business"))
        {
            if(occurence.containsKey("Monkey") && occurence.containsKey("Parrot"))
            {
                occurence.put("Monkey",occurence.get("Monkey") + occurence.get("Parrot"));
                occurence.remove("Parrot");
            }
        }
        //System.out.println(occurence);
        boolean fullChest = true;

        //Calculation points for Sets
        for(Map.Entry<String,Integer> entry: occurence.entrySet())
        {
            pts += collectionPts(entry.getValue());
            if(cardName.equals("Sea Battle"))
            {
                if(collectionPts(entry.getValue())==0 && (!entry.getKey().equals("Coin")) && (!entry.getKey().equals("Diamond")) && (!entry.getKey().equals("Sword")))
                {
                    fullChest=false;

                }
            }
            else {
                if (collectionPts(entry.getValue()) == 0 && (!entry.getKey().equals("Coin")) && (!entry.getKey().equals("Diamond"))) {
                    fullChest = false;

                }
            }
        }
        //full chest condition

        if(fullChest && (D.length ==8))
        {

            pts += 500;
        }

        //doubling the score if the card is "Captain"
        if(cardName.equals("Captain"))
        {
            pts *=2;
        }
        return pts;
    }

    // function to check whether player goes to Isle of Dead
    public boolean isIsleOfDead(Dice[] d, int n, Card c)
    {

        int count=0;
        if(c.reveal().equals("Skulls"))
        {
            count = c.getNumber();
        }

        if(n!=1)
        {
            return false;
        }


        else
        {
            HashMap<String,Integer> temp = new HashMap<>();
            temp = DiceToCollection(d);
            if(!temp.containsKey("Skull"))
            {
                return false;
            }
            if(temp.get("Skull") >= (4-count)) {
                return true;
            }

            return false;
        }
    }

    public int IslePts(Dice[] d, Card c)
    {
        int tempPts;
        HashMap<String,Integer> temp = new HashMap<>();
        temp = DiceToCollection(d);
        tempPts = temp.get("Skull") * 100;

        if(c.reveal().equals("Skulls"))
        {
            tempPts += c.getNumber() * 100;
        }
        else if(c.reveal().equals("Captain"))
        {
            tempPts *= 2;
        }
        else
        {
            return tempPts;
        }

        return tempPts;
    }

    public int isMoveValid(Dice[] d, int[] pos, Card c)
    {
        int count = 0;
        for(int i=0;i<pos.length;i++)
        {
            if(d[pos[i]-1].reveal() == "Skull")
            {
                count+=1;
            }
        }

        if(count ==1 && c.reveal()=="Sorceress")
        {
            return 2;
        }
        else if(count == 0)
        {
            return 1;
        }
        else if (count >=1 && (c == null || c.reveal() != "Sorceress"))
        {
            return 0;
        }
        System.out.println(count + c.reveal());
        return  0;
    }

    public boolean isGameOver(int[] scores, int[] turns)
    {
        if(scores[0] >3000 && turns[1] >= turns[0] && turns[2] >= turns[0])
        {
            return true;
        }
        else if(scores[1] >3000 && turns[0] > turns[1] && turns[2] >= turns[1])
        {
            return true;
        }
        else if(scores[2] >3000 && turns[1] > turns[2] && turns[0] >turns[2])
        {
            return true;
        }
        return false;
    }

    public boolean endTurn(Dice[] D, Card c)
    {
        int count =0;
        if(c.reveal().equals("Skulls"))
        {
            count = c.number;
        }
        for(int i =0; i< 8;i++)
        {
            if(D[i].reveal().equals("Skull"))
            {
                count +=1;
            }
        }

        if(count == 3){
            if(c.reveal().equals("Sorceress"))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if(count>3)
        {
            return true;
        }
        else{
            return false;
        }

    }

    public Dice[] shuffleDice(Dice[] D, int[] pos)
    {
        //System.out.println(pos);

        for(int j=0; j<pos.length;j++)
        {
            int i = pos[j]-1;
            //System.out.println(D[i].reveal());
            D[i].randomize();

        }
        return D;
    }

    public String gameScores(int[] scores)
    {
        String temp;
        temp ="";
        temp += "----------------------------------------\n";
        temp += String.format("%10s","Player 1");
        temp += "|";
        temp += String.format("%10d",scores[0]);
        temp += "\n";
        temp += String.format("%10s","Player 2");
        temp += "|";
        temp += String.format("%10d",scores[1]);
        temp += "\n";
        temp += String.format("%10s","Player 3");
        temp += "|";
        temp += String.format("%10d",scores[2]);
        temp += "\n----------------------------------------";



        return temp;

    }

    public int SeaBattlePts(Dice[] d, Card c)
    {
        if(endTurn(d,c))
        {
            return 0-c.bonus;
        }
        else
        {
            HashMap<String,Integer> sb = new HashMap<>();
            sb = DiceToCollection(d);

            if(sb.containsKey("Sword"))
            {
                if(sb.get("Sword") >= c.number)
                {
                    return (regularPts(d,c)+c.bonus);
                }
                else
                {
                    return 0-c.bonus;
                }
            }
            else
            {
                return 0-c.bonus;
            }
        }

    }

    public int ChestPts(Dice[] d, Card c)
    {
        int count =0;
        if(endTurn(d,c))
        {
            for(int i=0; i<8;i++)
            {
                if(d[i].inChest)
                {
                    count+=1;
                }
            }

            Dice[] temp = new Dice[count];
            int j=0;
            for(int i=0;i<8;i++)
            {
                if(d[i].inChest)
                {
                    temp[j] = d[i];
                    j++;
                }
            }
            return regularPts(temp,c);

        }
        else
        {
            return regularPts(d,c);
        }
    }

    public String printDandC(Dice[] d, Card c)
    {
        String toSend = "";
        for(int i=0; i<d.length;i++)
        {

            toSend += String.format("%-15d|",i+1);
        }
        toSend += "\n";
        for(int i=0; i<d.length;i++)
        {
            if(d[i].inChest)
            {
                toSend += String.format("%-15s|",d[i].reveal()+"(C)");
            }
            else {
                toSend += String.format("%-15s|", d[i].reveal());
            }
        }
        toSend += "\n\n";
        if(c.reveal() == "Skulls")
        {
            toSend += "Card: "+c.number +" "+ c.reveal();
        }
        else if(c.reveal() == "Sea Battle")
        {
            toSend+= "Card: "+c.number+" "+c.reveal();
            toSend += "\nBonus: "+c.bonus;
        }
        else {
            toSend += "Card: " + c.reveal();
        }

        return toSend;




    }


    public void DiceInChest(Dice[] d, int[] p)
    {
        for(int i=0;i<p.length;i++)
        {
            int j = p[i];
            d[j-1].InTheChest();
        }

    }

    public void DiceOutChest(Dice[] d, int[] p)
    {
        for(int i=0;i<p.length;i++)
        {
            int j = p[i];
            d[j-1].OutOfChest();
        }
    }





}