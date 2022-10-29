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
        for(int i = 0; i<8; i++)
        {
            //System.out.println(D[i].reveal());
            if(D[i].reveal() == "Coin" || D[i].reveal() == "Diamond")
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
        if(cardName == "Coin")
        {
            if(occurence.containsKey("Coin"))
            {
                occurence.put("Coin",occurence.get("Coin") + 1);
            }
            else
            {
                occurence.put("Coin",1);
            }
        }
        else if(cardName == "Diamond")
        {
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
        else if(cardName == "Monkey Business")
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
            if(collectionPts(entry.getValue())==0 && (entry.getKey()!="Coin") && (entry.getKey()!="Diamond"))
            {
                fullChest=false;

            }
        }
        //full chest condition
        if(fullChest)
        {
            pts += 500;
        }

        //doubling the score if the card is "Captain"
        if(cardName == "Captain")
        {
            pts *=2;
        }
        return pts;
    }
}