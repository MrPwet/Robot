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
    public void testGetPointToPointEnergyCoefficient() throws Exception {
        Random random = mock(Random.class);
        LandSensor ls = new LandSensor(random);

        //Test pour 2 terrain de type terre
        when(random.nextInt(anyInt())).thenReturn(0);
        Assert.assertEquals(1, ls.getPointToPointEnergyCoefficient(new Coordinates(0, 0), new Coordinates(0, 1)), 0);

        //Test pour 2 terrain de type roche
        when(random.nextInt(anyInt())).thenReturn(1);
        Assert.assertEquals(2,ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test pour 2 terrain de type sable
        when(random.nextInt(anyInt())).thenReturn(2);
        Assert.assertEquals(4,ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test pour 2 terrain de type boue
        when(random.nextInt(anyInt())).thenReturn(3);
        Assert.assertEquals(3,ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test terrain terre + terrain roche
        when(random.nextInt(anyInt())).thenReturn(0,1);
        Assert.assertEquals(1.5, ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test terrain terre + terrain sable
        when(random.nextInt(anyInt())).thenReturn(0,2);
        Assert.assertEquals(2.5, ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test terrain terre + terrain boue
        when(random.nextInt(anyInt())).thenReturn(0,3);
        Assert.assertEquals(2, ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test terrain roche + sable
        when(random.nextInt(anyInt())).thenReturn(1,2);
        Assert.assertEquals(3, ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test terrain roche + boue
        when(random.nextInt(anyInt())).thenReturn(1,3);
        Assert.assertEquals(2.5, ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test terrain sable + terrain boue
        when(random.nextInt(anyInt())).thenReturn(2,3);
        Assert.assertEquals(3.5, ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(0,1)),0);

        //Test si le random chie dans la colle
        exception.expect(LandSensorDefaillance.class);
        when(random.nextInt(anyInt())).thenReturn(6);
        ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(1,1));

        //Test si
        exception.expect(InaccessibleCoordinate.class);
        when(random.nextInt(anyInt())).thenReturn(4);
        ls.getPointToPointEnergyCoefficient(new Coordinates(0,0), new Coordinates(1,1));






    }
}
