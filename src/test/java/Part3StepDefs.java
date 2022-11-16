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

    @And("player {int} rolls {string} and stops")
    public void playerRolls(int arg0, String arg1) {
        d = g.getGameDices();
        List<String> rolls = new ArrayList<>(Arrays.asList(arg1.split(",")));

        for(int i=0; i<8;i++)
        {
            d[i] = new Dice(rolls.get(i));
        }

        tgs.playTurn(arg0-1,d,c);
        //System.out.println(tgs.scores[arg0-1]);



    }

    @Then("game is over and player {int} wins")
    public void gameIsOverAndPlayerWins(int arg0) {
        Assertions.assertTrue(g.isGameOver(tgs.scores,tgs.turns));
        Assertions.assertEquals(arg0,tgs.returnWinner());

    }
}
