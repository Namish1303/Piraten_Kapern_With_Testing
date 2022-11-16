import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part3StepDefs {
    TestGameServer tgs = new TestGameServer();
    Game g = new Game();
    Dice[] d = new Dice[8];
    Card c;
    @Given("Game has started")
    public void gameHasStarted() {
        try {
            tgs.acceptConnections();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error");
        }
    }

    @When("player {int} has Card {string} with value {int} and bonus {int}")
    public void playerHasCardWithValueAndBonus(int arg0, String arg1, int arg2, int arg3) {

        c = g.getGameCard();
        c = new Card(arg1,arg2,arg3);

    }

    @And("player {int} rolls {string} and scores {int}")
    public void playerRolls(int arg0, String arg1, int arg2) {
        d = g.getGameDices();
        List<String> rolls = new ArrayList<>(Arrays.asList(arg1.split(",")));

        for(int i=0; i<8;i++)
        {
            d[i] = new Dice(rolls.get(i));
        }

        tgs.playTurn(arg0-1,d,c);
        //System.out.println(tgs.scores[arg0-1]);
        Assertions.assertEquals(arg2,tgs.scores[arg0-1]);



    }

    @Then("game is over and player {int} wins")
    public void gameIsOverAndPlayerWins(int arg0) {
        Assertions.assertTrue(g.isGameOver(tgs.scores,tgs.turns));
        Assertions.assertEquals(arg0,tgs.returnWinner());

    }

    @Then("player {int} rerolls {string} and gets {string}")
    public void playerRerollsAndGets(int arg0, String arg1, String arg2) {
        List<String> pos = new ArrayList<>(Arrays.asList(arg1.split(",")));
        int[] pos2 = new int[pos.size()];
        for(int i=0;i<pos.size();i++)
        {
            pos2[i] = Integer.parseInt(pos.get(i));
        }

        d = g.shuffleDice(d,pos2);

        List<String> rolls = new ArrayList<>(Arrays.asList(arg2.split(",")));
        for(int i=0; i<8;i++)
        {
            //System.out.println(d[i].reveal());
            d[i] = new Dice(rolls.get(i));
        }


    }

    @And("player {int} rerolls {string} and gets {string} and scores {int}")
    public void playerRerollAndGets(int arg0, String arg1, String arg2, int arg3) {

        List<String> pos = new ArrayList<>(Arrays.asList(arg1.split(",")));
        int[] pos2 = new int[pos.size()];
        for(int i=0;i<pos.size();i++)
        {
            pos2[i] = Integer.parseInt(pos.get(i));
        }

        d = g.shuffleDice(d,pos2);

        List<String> rolls = new ArrayList<>(Arrays.asList(arg2.split(",")));
        for(int i=0; i<8;i++)
        {
            //System.out.println(d[i].reveal());
            d[i] = new Dice(rolls.get(i));
        }

        tgs.playTurn(arg0-1,d,c);
        //System.out.println(tgs.scores[arg0-1]);
        Assertions.assertEquals(arg3,tgs.scores[arg0-1]);
    }

    @Then("game is over and player {int} scores {int} , player {int} scores {int} and player {int} scores {int}")
    public void gameIsOverAndPlayerScoresPlayerScoresAndPlayerScores(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {

        Assertions.assertEquals(arg1,tgs.scores[arg0-1]);
        Assertions.assertEquals(arg3,tgs.scores[arg2-1]);
        Assertions.assertEquals(arg5,tgs.scores[arg4-1]);
    }

    @And("player {int} rolls {string}")
    public void playerRolls2(int arg0, String arg1) {
        d = g.getGameDices();
        List<String> rolls = new ArrayList<>(Arrays.asList(arg1.split(",")));

        for(int i=0; i<8;i++)
        {
            d[i] = new Dice(rolls.get(i));
        }
    }
}
