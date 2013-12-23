package robot;
import org.junit.*;

/**
 * Created by clap on 23/12/13.
 */
public class CoordinatesTest {

    @Test
    public void CoordinatesContructTest() {
        Coordinates coo = new Coordinates(10, 10);
        Assert.assertEquals(10, coo.getX());
        Assert.assertEquals(10, coo.getY());
    }
}
