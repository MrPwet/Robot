package robot;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: clap
 * Date: 27/11/13
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class BatteryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testTimeToSufficientCharge() throws Exception {
        Battery batTest = new Battery();

        Assert.assertEquals(batTest.timeToSufficientCharge(0), 0);
        Assert.assertEquals(batTest.timeToSufficientCharge(100), 0);
        Assert.assertEquals(batTest.timeToSufficientCharge(101), 1000);

    }

    @Test
    public void testCanDeliver() {
        Battery batTest = new Battery();

        Assert.assertEquals(batTest.canDeliver(100), true);
        Assert.assertEquals(batTest.canDeliver(101), false);
    }

    @Test
    public void testSetUp() throws Exception {
        Battery batTest = new Battery();

        batTest.setUp();
        Thread.sleep(500);
        for(int i=1; i <= 3; i++){
            Assert.assertEquals( 100 + 10*i, batTest.getChargeLevel(), 0);
            Thread.sleep(1000);
        }
    }

    @Test
    public void testCharge() {
        Battery batTest = new Battery();

        batTest.charge();
        Assert.assertEquals(110, batTest.getChargeLevel() , 0);
    }

    @Test
    public void testUse() throws InsufficientChargeException {
        Battery batTest = new Battery();

        batTest.use(20);
        Assert.assertEquals(80, batTest.getChargeLevel(), 0);

        exception.expect(InsufficientChargeException.class);
        batTest.use(9000);

    }
}
