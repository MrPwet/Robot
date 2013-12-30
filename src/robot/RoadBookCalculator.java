package robot;

import java.util.ArrayList;
import java.util.List;

import static robot.Direction.*;
import static robot.Instruction.*;

public class RoadBookCalculator {

    static RoadBook calculateRoadBook(Direction direction, Coordinates position, Coordinates destination, ArrayList<Instruction> instructions) {
        List<Direction> directionList = new ArrayList<Direction>();
        if (destination.getX() < position.getX()) directionList.add(WEST);
        if (destination.getX() > position.getX()) directionList.add(Direction.EAST);
        if (destination.getY() < position.getY()) directionList.add(Direction.NORTH);
        if (destination.getY() > position.getY()) directionList.add(Direction.SOUTH);
        if (directionList.isEmpty()) return new RoadBook(instructions);
        if (directionList.contains(direction)) {
            instructions.add(FORWARD);
            return calculateRoadBook(direction, MapTools.nextForwardPosition(position, direction), destination, instructions);
        }
        /*
            La condition permet de savoir si le virage à droite permet de se
            placer directement dans la bonne direction. Sinon, le virage a
            gauche est utilisé.
        */
        else if(directionList.contains(MapTools.clockwise(direction))) {
            instructions.add(TURNRIGHT);
            return calculateRoadBook(MapTools.clockwise(direction), position, destination, instructions);
        }
        else {
            instructions.add(TURNLEFT);
            return calculateRoadBook(MapTools.counterclockwise(direction), position, destination, instructions);
        }
    }
}