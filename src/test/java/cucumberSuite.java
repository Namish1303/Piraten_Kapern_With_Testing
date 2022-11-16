import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/Part2.feature","src/test/java/Part1.feature"},
        plugin = {"pretty"}
)

public class cucumberSuite {
}
