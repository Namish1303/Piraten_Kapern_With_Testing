import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Part3StepDefs {
    @Given("Game has started")
    public void gameHasStarted() {
    }

    @When("player {int} has Card {string} with value {int} and bonus {int}")
    public void playerHasCardWithValueAndBonus(int arg0, String arg1, int arg2, int arg3) {
    }

    @And("player {int} rolls {string} and stops")
    public void playerRollsAndStops(int arg0, String arg1) {
    }

    @Then("game is over and player {int} wins")
    public void gameIsOverAndPlayerWins(int arg0) {
    }
}
