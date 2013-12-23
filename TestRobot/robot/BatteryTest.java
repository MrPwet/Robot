package robot;
import org.junit.*;

/**
 * Created with IntelliJ IDEA.
 * User: clap
 * Date: 27/11/13
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class BatteryTest {

    @Test
    public void testTimeToSufficientCharge() throws Exception {
        Battery batTest = new Battery();

        Assert.assertEquals(batTest.timeToSufficientCharge(0), 0);
        Assert.assertEquals(batTest.timeToSufficientCharge(100), 0);
        Assert.assertEquals(batTest.timeToSufficientCharge(101), 1000);

    }

    @Test
    public void canDeliverTest() {
        Battery batTest = new Battery();

        Assert.assertEquals(batTest.canDeliver(100), true);
        Assert.assertEquals(batTest.canDeliver(101), false);
    }

    @Test
    public void setUpTest() throws Exception {
        Battery batTest = new Battery();

        batTest.setUp();
        for(int i=0; i <= 2; i++){
            Assert.assertEquals( 100 + 10*i, batTest.getChargeLevel(), 0);
            Thread.sleep(1000);
        }
    }

    @Test
    public void chargeTest() {
        Battery batTest = new Battery();

        batTest.charge();
        Assert.assertEquals(110, batTest.getChargeLevel() , 0);
    }

    @Test(expected = InsufficientChargeException.class)
    public void useTest() throws InsufficientChargeException {
        Battery batTest = new Battery();

        batTest.use(20);
        Assert.assertEquals(80, batTest.getChargeLevel(), 0);
        batTest.use(9000);
        /* throws InsufficientChargeException */
    }
}
