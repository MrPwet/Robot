package robot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: MrPwet
 * Date: 26/12/13
 * Time: 12:55
 */
public class LandTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetLandByOrdinal() throws Exception {
        Land land;

        land = Land.getLandByOrdinal(0);
        Assert.assertEquals("Terre", land.Terre, land);

        land = Land.getLandByOrdinal(1);
        Assert.assertEquals("Roche", land.Roche, land);

        land = Land.getLandByOrdinal(2);
        Assert.assertEquals("Sable", land.Sable, land);

        land = Land.getLandByOrdinal(3);
        Assert.assertEquals("Boue", land.Boue, land);

        land = Land.getLandByOrdinal(4);
        Assert.assertEquals("Infranchissable", land.Infranchissable, land);

        exception.expect(TerrainNonRepertorieException.class);
        land = Land.getLandByOrdinal(5);
        land = Land.getLandByOrdinal(-5);
    }

    public void testCountLand() {
        Assert.assertEquals("Retourne la taille de l'enum land (5)", 5, Land.CountLand());
    }
}
