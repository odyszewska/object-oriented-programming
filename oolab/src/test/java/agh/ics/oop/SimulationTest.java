package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import agh.ics.oop.model.*;
import java.util.List;

class SimulationTest {

    @Test
    void testAnimalOrientationAndPositionAfterMovements() {
        String[] commands = {"f", "f", "r", "f"};
        Vector2d initialPosition = new Vector2d(2, 2);
        WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands), map);

        simulation.run();

        String expectedPositionAndOrientation = ">";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }

    @Test
    void testAnimalDoesNotMoveOutOfBounds() {
        String[] commands = {"f", "f", "f", "f", "f", "f"};
        Vector2d initialPosition = new Vector2d(4, 4);
        WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands), map);

        simulation.run();

        String expectedPositionAndOrientation = "^";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }

    @Test
    void testInputCommandsAreParsedCorrectly() {
        String[] commands = {"f", "r", "f", "l", "b"};
        var expectedDirections = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD
        );
        var actualDirections = OptionsParser.parse(commands);
        assertEquals(expectedDirections, actualDirections);
    }


}
