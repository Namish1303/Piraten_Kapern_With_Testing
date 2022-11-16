
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1_2StepDefs {
    Game g =  new Game();
    Dice[] d = new Dice[8];
    Card c;
    List<Integer> chest = new ArrayList<>();
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

    @Then("player goes to Isle of Dead")
    public void playerGoesToIsleOfDead() {
        Assertions.assertTrue(g.isIsleOfDead(d,1,c));
    }


    @And("incurs a deduction of {int} for other players")
    public void incursADeductionOfScoreForOtherPlayers(int arg0) {
        Assertions.assertEquals(arg0,g.IslePts(d,c));
    }

    @Then("player puts {string} in Chest")
    public void playerPutsInChest(String arg0) {
        List<String> pos = new ArrayList<>(Arrays.asList(arg0.split(",")));
        int[] pos2 = new int[pos.size()];
        for(int i=0;i<pos.size();i++)
        {
            pos2[i] = Integer.parseInt(pos.get(i));
            chest.add(pos2[i]);
        }

        g.DiceInChest(d,pos2);

    }

    @And("takes out {string} from Chest")
    public void takesOutFromChest(String arg0) {
        List<String> pos = new ArrayList<>(Arrays.asList(arg0.split(",")));
        int[] pos2 = new int[pos.size()];
        for(int i=0;i<pos.size();i++)
        {
            pos2[i] = Integer.parseInt(pos.get(i));
            chest.remove(Integer.valueOf(pos2[i]));
        }

        g.DiceOutChest(d,pos2);
    }

    @And("Player scores {int} including Chest")
    public void playerScoresIncludingChest(int arg0) {

        for(int i=0;i<chest.size();i++)
        {
            d[chest.get(i)-1].InTheChest();
        }
        Assertions.assertEquals(arg0,g.ChestPts(d,c));
    }

    @And("Player die and scores {int} including Chest")
    public void playerDieAndScoresIncludingChest(int arg0) {
        Assertions.assertTrue(g.endTurn(d,c));
        for(int i=0;i<chest.size();i++)
        {
            d[chest.get(i)-1].InTheChest();
        }
        Assertions.assertEquals(arg0,g.ChestPts(d,c));

    }
}
