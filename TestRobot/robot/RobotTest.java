package robot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.mockito.Mockito.*;

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

    //Test moveForward
    @Test
    public void TestMoveForward() throws UnlandedRobotException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
        Robot r2d2 = new Robot();
        Robot c3po = new Robot();
        Coordinates start = new Coordinates(5,5);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0);
        r2d2.land(start,ls);
        r2d2.moveForward();
        Assert.assertEquals("On devrait être en 4", 4, r2d2.getYposition(), 0);
        Assert.assertEquals("Pas bougé", 5, r2d2.getXposition(), 0);

        exception.expect(UnlandedRobotException.class);
        c3po.moveForward();
    }

    //Test moveBackward
    @Test
    public void TestMoveBackward() throws UnlandedRobotException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
        Robot r2d2 = new Robot();
        Robot c3po = new Robot();
        Coordinates start = new Coordinates(5,5);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0);
        r2d2.land(start,ls);
        r2d2.moveBackward();
        Assert.assertEquals("On devrait être en 4", 6, r2d2.getYposition(), 0);
        Assert.assertEquals("Pas bougé", 5, r2d2.getXposition(), 0);

        exception.expect(UnlandedRobotException.class);
        c3po.moveBackward();
    }

    //Test LetsGo
    @Test
    public void TestLetsGo1() throws UnlandedRobotException, UndefinedRoadbookException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
        Robot r2d2 = new Robot();

        exception.expect(UndefinedRoadbookException.class);
        r2d2.letsGo();
    }

    @Test
    public void TestLetsGo2() throws UnlandedRobotException, UndefinedRoadbookException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
        Robot r2d2 = new Robot();
        List<Instruction> lst = new ArrayList<Instruction>();
        lst.add(Instruction.FORWARD);
        lst.add(Instruction.TURNRIGHT);
        lst.add(Instruction.FORWARD);
        lst.add(Instruction.TURNLEFT);
        lst.add(Instruction.FORWARD);
        lst.add(Instruction.TURNLEFT);
        lst.add(Instruction.BACKWARD);
        RoadBook rb = new RoadBook(lst);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0);
        r2d2.setRoadBook(rb);
        r2d2.land(new Coordinates(5,5), ls);
        r2d2.letsGo();
        Assert.assertEquals(7, r2d2.getXposition(),0);
        Assert.assertEquals(3, r2d2.getYposition(),0);
        Assert.assertEquals(Direction.WEST, r2d2.getDirection());

    }


}
