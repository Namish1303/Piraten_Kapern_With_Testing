
public class Card {

    String value;
    int number;
    int bonus;
    public Card (String value, int num, int bonus)
    {
        this.value = value;
        number = num;
        this.bonus = bonus;
    }

    public int getNumber() {
        return number;
    }

    public String reveal()
    {
        return value;
    }

    public int getBonus()
    {
        return bonus;
    }


}
