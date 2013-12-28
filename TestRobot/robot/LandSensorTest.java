package robot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mrpwet
 * Date: 26/12/13
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */


public class LandSensorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void chemin1GPTPEC() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);
        double result;

        when(random.nextInt(anyInt())).thenReturn(0);
        result = ls.getPointToPointEnergyCoefficient(new Coordinates(0,0),new Coordinates(0,1));
        Assert.assertEquals(1,result,0);
    }

    @Test
    public void chemin2GPTPEC() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(6);
        exception.expect(LandSensorDefaillance.class);
        ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
    }

    @Test
    public void chemin3GPTPEC() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0,6);
        exception.expect(LandSensorDefaillance.class);
        ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
    }

    @Test
    public void chemin4GPTPEC() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        when(random.nextInt(anyInt())).thenReturn(0,4);
        exception.expect(InaccessibleCoordinate.class);
        ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1));
    }

    @Test
    public void chemin5GPTPEC() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);
        double result;
        Coordinates start = new Coordinates(2,2);
        Coordinates dest = new Coordinates(2,3);

        when(random.nextInt(anyInt())).thenReturn(0,1);
        ls.getPointToPointEnergyCoefficient(start,dest);
        result = ls.getPointToPointEnergyCoefficient(start, dest);
        Assert.assertEquals(1.5, result, 0);
    }

    @Test
    public void chemin6GPTPEC() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);
        double result;
        Coordinates start = new Coordinates(2,2);
        Coordinates dest = new Coordinates(2,3);

        when(random.nextInt(anyInt())).thenReturn(1,2);
        ls.getPointToPointEnergyCoefficient(start,start);
        result = ls.getPointToPointEnergyCoefficient(start, dest);
        Assert.assertEquals(3, result, 0);
    }

}
