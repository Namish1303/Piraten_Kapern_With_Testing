
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1StepDefs {
    Game g =  new Game();
    Dice[] d = new Dice[8];
    Card c;
    @Given("player rolls {string}")
    public void playerRollsRolls(String arg0) {
            d = g.getGameDices();
            List<String> rolls = new ArrayList<>(Arrays.asList(arg0.split(",")));

            for(int i=0; i<8;i++)
            {
                d[i] = new Dice(rolls.get(i));
            }
    }

    @When("Card is {string} with value {int} and bonus {int}")
    public void cardIsCardWithValueValueAndBonusBonus(String arg0, int arg1, int arg2) {
        c = new Card(arg0,arg1,arg2);
    }

    @Then("Player scores {int}")
    public void playerScoresScore(int arg0) {
        Assertions.assertEquals(arg0,g.regularPts(d,c));

    }
}
