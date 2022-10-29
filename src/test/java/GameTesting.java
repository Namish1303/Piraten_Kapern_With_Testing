import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTesting {
    Game g;
    public GameTesting()
    {
        g = new Game();
    }

    @Test
    public void collPts()
    {
        Assertions.assertEquals(100,g.collectionPts(3));
    }





}
