
import java.util.ArrayList;
import java.util.Collections;

public class CardDeck
{
    ArrayList<Card> cards;

    //initialising deck
    //assigning the 35 cards in the deck
    public CardDeck()
    {
        cards = new ArrayList<Card>();
        assignCards();
        Collections.shuffle(cards);
        //System.out.println(cards.size());
    }


    private void assignCards()
    {
        Card chest = new Card("Chest",1,0);
        Card sorceress = new Card("Sorceress",1,0);
        Card captain= new Card("Captain",1,0);
        Card monkey = new Card("Monkey Business",1,0);
        Card diamond = new Card("Diamond",1,0);
        Card coin = new Card("Coin",1,0);

        for(int i=0; i<4 ; i++)
        {
            addToDeck(chest);
            addToDeck(sorceress);
            addToDeck(captain);
            addToDeck(monkey);
            addToDeck(diamond);
            addToDeck(coin);
        }

        Card sword2 = new Card("Sea Battle",2,300);
        Card sword3 = new Card("Sea Battle",3,500);
        Card sword4 = new Card("Sea Battle",4,1000);
        Card skull2 = new Card("Skulls",2,0);
        Card skull1 = new Card("Skulls",1,0);

        for(int i =0; i<2 ;i++)
        {
            addToDeck(skull2);
            addToDeck(sword2);
            addToDeck(sword3);
            addToDeck(sword4);
            addToDeck(skull1);
        }
        addToDeck(skull1);


    }

    private void addToDeck(Card toAdd)
    {
        cards.add(toAdd);
    }

    //function to get the top cards and pass it to the server, then add the card back at the bottom of the deck
    public Card reveal()
    {
        cards.add(cards.remove(0));
        //System.out.println(cards.size());
        return cards.get(34);
    }


}
