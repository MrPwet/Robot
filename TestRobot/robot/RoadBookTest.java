package robot;

import org.junit.Assert;
import org.junit.Test;
import static robot.Instruction.*;

import java.util.*;

/**
 * Created by clap on 25/12/13.
 */
public class RoadBookTest {

    private static ArrayList<Instruction> instList = new ArrayList<Instruction>();

    public RoadBookTest() {
        instList.add(TURNLEFT);
        instList.add(TURNRIGHT);
    }

    @Test
    public void testNext() throws Exception {
        RoadBook rbTest = new RoadBook(instList);
        Assert.assertEquals(TURNLEFT, rbTest.next());
    }

    @Test
    public void testHasInstruction() throws Exception {
        RoadBook rbTest = new RoadBook(instList);
        Assert.assertTrue(rbTest.hasInstruction());
        rbTest.next();
        Assert.assertTrue(rbTest.hasInstruction());
    }
}
