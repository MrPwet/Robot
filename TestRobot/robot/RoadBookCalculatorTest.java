package robot;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import static robot.Direction.*;
import static robot.Instruction.*;

import static robot.RoadBookCalculator.*;

/**
 * Created by clap on 28/12/13.
 */
public class RoadBookCalculatorTest {

    public static void assertSameRoadBook(RoadBook testRb, ArrayList<Instruction> expectedInst) {
        Iterator<Instruction> iterInst = expectedInst.iterator();
        while(testRb.hasInstruction()){
            Assert.assertTrue("RoadBook has less Instructions than expected", iterInst.hasNext());
            Assert.assertEquals(iterInst.next(), testRb.next());
        }
        Assert.assertFalse("RoadBook has more instructions than expected", iterInst.hasNext());
    }

    @Test
    public void testCalculateRoadBook() throws Exception {

        ArrayList<Instruction> expectedInst;

        RoadBook resultRoadBook;

        expectedInst = new ArrayList<Instruction>();

        resultRoadBook = calculateRoadBook(WEST , new Coordinates(1,1), new Coordinates(1, 1), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);

        expectedInst = new ArrayList<Instruction>();
        expectedInst.add(FORWARD);

        resultRoadBook = calculateRoadBook(WEST , new Coordinates(2,1), new Coordinates(1, 1), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);

        resultRoadBook = calculateRoadBook(EAST , new Coordinates(1,1), new Coordinates(2, 1), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);

        resultRoadBook = calculateRoadBook(SOUTH , new Coordinates(1,1), new Coordinates(1, 2), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);

        resultRoadBook = calculateRoadBook(NORTH , new Coordinates(1,2), new Coordinates(1, 1), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);

        expectedInst = new ArrayList<Instruction>();
        expectedInst.add(TURNRIGHT);
        expectedInst.add(FORWARD);

        resultRoadBook = calculateRoadBook(WEST , new Coordinates(1,2), new Coordinates(1, 1), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);
    }
}
