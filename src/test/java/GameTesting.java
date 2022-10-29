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
        System.out.println("RUNNING TESTS FOR COLLECTIONS POINTS");
        System.out.println("Collection of 3 is 100 pts");
        Assertions.assertEquals(100,g.collectionPts(3));

        System.out.println("Collection of 6 is 1000 pts");
        Assertions.assertEquals(1000,g.collectionPts(6));

        System.out.println("Collection of 8 is 4000 pts");
        Assertions.assertEquals(4000,g.collectionPts(8));

    }





}
