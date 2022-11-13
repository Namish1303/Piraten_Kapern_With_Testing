
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
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

            c = g.getGameCard();
    }

    @When("Card is {string} with value {int} and bonus {int}")
    public void cardIsCardWithValueValueAndBonusBonus(String arg0, int arg1, int arg2) {
        c = new Card(arg0,arg1,arg2);
    }

    @Then("Player scores {int}")
    public void playerScoresScore(int arg0) {
        Assertions.assertEquals(arg0,g.regularPts(d,c));

    }

    @Then("Player dies and scores {int}")
    public void playerDiesAndScoresScore(int arg0) {
        Assertions.assertTrue(g.endTurn(d,c));
        Assertions.assertEquals(arg0,0);
    }

    @Then("player re-rolls dices {string} and gets {string}")
    public void playerReRollsDicesNumber(String arg0, String arg1) {
        List<String> pos = new ArrayList<>(Arrays.asList(arg0.split(",")));
        int[] pos2 = new int[pos.size()];
        for(int i=0;i<pos.size();i++)
        {
            pos2[i] = Integer.parseInt(pos.get(i));
        }
        Assertions.assertTrue(g.isMoveValid(d,pos2,c) ==1 || g.isMoveValid(d,pos2,c) == 2);
        g.shuffleDice(d,pos2);

        List<String> rolls = new ArrayList<>(Arrays.asList(arg1.split(",")));
        for(int i=0; i<8;i++)
        {
            //System.out.println(d[i].reveal());
            d[i] = new Dice(rolls.get(i));
        }

    }

    @And("dies and scores {int}")
    public void diesAndScoresScore(int arg0) {
            Assertions.assertTrue(g.endTurn(d,c));
            Assertions.assertEquals(arg0,0);
    }

    @And("scores {int}")
    public void scoresScore(int arg0) {
        Assertions.assertEquals(arg0,g.regularPts(d,c));
    }

    @And("scores {int} with SeaBattle Points")
    public void scoresWithSeaBattlePoints(int arg0) {
        Assertions.assertEquals(arg0,g.SeaBattlePts(d,c));
    }

    @Then("Player dies and gets a deduction of {int}")
    public void playerDiesAndGetsADeductionOfScore(int argo0) {
        Assertions.assertEquals(argo0,g.SeaBattlePts(d,c));
    }
}
