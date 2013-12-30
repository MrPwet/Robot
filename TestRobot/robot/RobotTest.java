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
    public void TestGetXYDPosition2() throws CannotLandHereException, UnlandedRobotException, LandSensorDefaillance {
        Robot c3po = new Robot();
        Coordinates start = new Coordinates(2,3);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);
        int xPos;
        int yPos;
        Direction dir;

        //il est nécessaire de mocker random pour éviter l'aterrissage sur un terrain infranchissable
        when(random.nextInt(anyInt())).thenReturn(0);

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
    public void TestMoveForward() throws CannotLandHereException, UnlandedRobotException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
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
    public void TestMoveBackward() throws CannotLandHereException, UnlandedRobotException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
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
    public void TestLetsGo2() throws CannotLandHereException, UnlandedRobotException, UndefinedRoadbookException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate {
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

    //Test turnLeft and turnRight

    @Test
    public void TestTurnLeft1() throws UnlandedRobotException {
        Robot r2d2 = new Robot();

        exception.expect(UnlandedRobotException.class);
        r2d2.turnLeft();
    }

    @Test
    public void TestTurnRight1() throws UnlandedRobotException {
        Robot r2d2 = new Robot();

        exception.expect(UnlandedRobotException.class);
        r2d2.turnRight();
    }

    @Test
    public void TestTurnLeftRight() throws CannotLandHereException, UnlandedRobotException, LandSensorDefaillance {
        Robot r2d2 = new Robot();
        Robot c3po = new Robot();
        Coordinates start = new Coordinates(5,5);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0);
        c3po.land(start, ls);
        c3po.turnRight();
        Assert.assertEquals(Direction.EAST, c3po.getDirection());

        r2d2.land(start, ls);
        r2d2.turnLeft();
        Assert.assertEquals(Direction.WEST, r2d2.getDirection());

    }

    //Test moveTo
    @Test
    public void TestMoveTo() throws CannotLandHereException, InsufficientChargeException, LandSensorDefaillance, InaccessibleCoordinate, UnlandedRobotException {
        Battery duracell = mock(Battery.class);
        Robot r2d2 = new Robot(1.0, duracell);
        Coordinates start = new Coordinates(5,5);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0);
        r2d2.land(start, ls);

        when(duracell.canDeliver(anyDouble())).thenReturn(true);
        r2d2.moveForward();
        Assert.assertEquals(4, r2d2.getYposition(), 0);

        when(duracell.canDeliver(anyDouble())).thenReturn(false);
        exception.expect(InsufficientChargeException.class);
        r2d2.moveForward();

    }

    //test computeRoadTo
    @Test
    public void TestComputeRoadTo1() throws UnlandedRobotException {
        Robot r2d2 = new Robot();

        exception.expect(UnlandedRobotException.class);
        r2d2.computeRoadTo(new Coordinates(2,3));
    }

    @Test
    public void TestComputeRoadTo2() throws CannotLandHereException, UnlandedRobotException, InsufficientChargeException, LandSensorDefaillance, UndefinedRoadbookException, InaccessibleCoordinate {
        Robot c3po = new Robot();
        Coordinates start = new Coordinates(5,5);
        Coordinates dest = new Coordinates(6,6);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0);
        c3po.land(start, ls);
        c3po.computeRoadTo(dest);
        c3po.letsGo();

        Assert.assertEquals(6,c3po.getXposition(),0);
        Assert.assertEquals(6,c3po.getYposition(),0);
        Assert.assertEquals(Direction.SOUTH,c3po.getDirection());

    }

    @Test
    public void testMoveToInaccessibleCoordinate() throws Exception {

        Battery duracell = mock(Battery.class);
        Robot r2d2 = new Robot(1.0, duracell);
        Coordinates start = new Coordinates(5,5);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        //when(random.nextInt(anyInt())).thenReturn(4);
        when(random.nextInt(anyInt())).thenReturn(0, 4);
        r2d2.land(start, ls);

        when(duracell.canDeliver(anyDouble())).thenReturn(true);

        exception.expect(InaccessibleCoordinate.class);
        r2d2.moveForward();
        
        //le robot ne doit pas avoir bougé
        Assert.assertEquals(5, r2d2.getXposition(), 0);
    }

    /*
        Le test précédent met en évidence le fait que le robot puisse atterrir
        sur un terrain Infranchissable sans problème. Nous avons donc créé
        une méthode permettant de vérifier la nature du terrain avant
        l'atterrissage
    */

    @Test
    public void testAterissageImpossible() throws Exception {

        Robot r2d2 = new Robot();
        Coordinates start = new Coordinates(5,5);
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        //le point d'atterrissage est infranchissable
        when(random.nextInt(anyInt())).thenReturn(4);

        exception.expect(CannotLandHereException.class);
        r2d2.land(start, ls);

    }

}
