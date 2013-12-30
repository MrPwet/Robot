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
            Assert.assertTrue("RoadBook has more Instructions than expected", iterInst.hasNext());
            Assert.assertEquals(iterInst.next(), testRb.next());
        }
        Assert.assertFalse("RoadBook has less instructions than expected", iterInst.hasNext());
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

    @Test
    public void testLongTrajet() throws Exception {

        ArrayList<Instruction> expectedInst;
        expectedInst = new ArrayList<Instruction>();
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);
        expectedInst.add(TURNRIGHT);
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);

        RoadBook resultRoadBook;

        resultRoadBook = calculateRoadBook(EAST, new Coordinates(1,1), new Coordinates(4, 4), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);
        
        expectedInst = new ArrayList<Instruction>();
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);
        expectedInst.add(TURNLEFT);
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);
        expectedInst.add(FORWARD);

        resultRoadBook = calculateRoadBook(SOUTH, new Coordinates(1,1), new Coordinates(4, 4), new ArrayList<Instruction>() );
        assertSameRoadBook(resultRoadBook, expectedInst);
        /*
            Ce test exploite la nouvelle possibilité de tourner à gauche
            La nouvelle fonctionnalité a été ajoutée pour éviter que le robot
            fasse 3 quarts de tour vers la droite  
        */
    }
}
