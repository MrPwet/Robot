package robot;
import org.junit.*;
import static robot.MapTools.*;
import static robot.Direction.*;

/**
 * Created by clap on 25/12/13.
 */
public class MapToolsTest {

    private Coordinates coo11 = new Coordinates(1, 1);

    @Test
    public void testNextForwardPosition() throws Exception {
        Assert.assertEquals(0, nextForwardPosition(coo11, NORTH).getY());
        Assert.assertEquals(2, nextForwardPosition(coo11, SOUTH).getY());
        Assert.assertEquals(2, nextForwardPosition(coo11, EAST).getX());
        Assert.assertEquals(0, nextForwardPosition(coo11, WEST).getX());

    }

    @Test
    public void testNextBackwardPosition() throws Exception {
        Assert.assertEquals(2, nextBackwardPosition(coo11, NORTH).getY());
        Assert.assertEquals(0, nextBackwardPosition(coo11, SOUTH).getY());
        Assert.assertEquals(0, nextBackwardPosition(coo11, EAST).getX());
        Assert.assertEquals(2, nextBackwardPosition(coo11, WEST).getX());
    }

    @Test
    public void testCounterclockwise() throws Exception {
        Assert.assertEquals(WEST, counterclockwise(NORTH));
        Assert.assertEquals(SOUTH, counterclockwise(WEST));
        Assert.assertEquals(EAST, counterclockwise(SOUTH));
        Assert.assertEquals(NORTH, counterclockwise(EAST));
    }

    @Test
    public void testClockwise() throws Exception {
        Assert.assertEquals(WEST, clockwise(SOUTH));
        Assert.assertEquals(SOUTH, clockwise(EAST));
        Assert.assertEquals(EAST, clockwise(NORTH));
        Assert.assertEquals(NORTH, clockwise(WEST));
    }
}
