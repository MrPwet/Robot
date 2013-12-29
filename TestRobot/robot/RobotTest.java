package robot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: MrPwet
 * Date: 28/12/13
 * Time: 17:52
 */
public class RobotTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    //Test getXposition & getYposition & getDirection
    @Test
    public void TestGetXposition1() throws UnlandedRobotException {
        Robot r2d2 = new Robot();

        exception.expect(UnlandedRobotException.class);
        r2d2.getXposition();
    }

    @Test
    public void TestGetYposition1() throws UnlandedRobotException {
        Robot r2d2 = new Robot();

        exception.expect(UnlandedRobotException.class);
        r2d2.getYposition();
    }

    @Test
    public void TestGetDirection() throws UnlandedRobotException {
        Robot r2d2 = new Robot();

        exception.expect(UnlandedRobotException.class);
        r2d2.getDirection();
    }

    @Test
    public void TestGetXYDPosition2() throws UnlandedRobotException {
        Robot c3po = new Robot();
        Coordinates start = new Coordinates(2,3);
        Random random = new Random();
        LandSensor ls = new LandSensor(random);
        int xPos;
        int yPos;
        Direction dir;

        c3po.land(start,ls);

        xPos = c3po.getXposition();
        Assert.assertEquals(2, xPos, 0);

        yPos = c3po.getYposition();
        Assert.assertEquals(3, yPos, 0);

        dir = c3po.getDirection();
        Assert.assertEquals(Direction.NORTH, dir);
    }

}
