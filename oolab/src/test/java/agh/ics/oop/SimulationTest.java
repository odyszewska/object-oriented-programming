package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.List;

class SimulationTest {

    @Test
    void testAnimalOrientationAndPositionAfterMovements() {
        String[] commands = {"f", "f", "r", "f"};
        Vector2d initialPosition = new Vector2d(2, 2);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (3,4), Orientation: Wschód";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }
    @Test
    void testAnimalOrientationAndPositionAfterDifferentMovements1() {
        String[] commands = {"f", "l", "f", "r", "f"};
        Vector2d initialPosition = new Vector2d(2, 2);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (1,4), Orientation: Północ";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }
    @Test
    void testAnimalOrientationAndPositionAfterDifferentMovements2() {
        String[] commands = {"f", "f", "r", "f", "l", "b"};
        Vector2d initialPosition = new Vector2d(2, 2);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (3,3), Orientation: Północ";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }
    @Test
    void testAnimalOrientationAndPositionAfterDifferentMovements4() {
        String[] commands = {"r", "f", "f", "b", "l", "f"};
        Vector2d initialPosition = new Vector2d(4, 4);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (3,4), Orientation: Północ";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }
    @Test
    void testAnimalOrientationAndPositionAfterDifferentMovements5() {
        String[] commands = {"b", "b", "r", "f", "f", "f", "l","l", "l"};
        Vector2d initialPosition = new Vector2d(2, 2);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (4,0), Orientation: Południe";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }
    @Test
    void testAnimalOrientationAndPositionAfterMovements6() {
        String[] commands = {"f", "b", "l", "f"};
        Vector2d initialPosition = new Vector2d(2, 2);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (1,2), Orientation: Zachód";
        assertEquals(expectedPositionAndOrientation, simulation.getAnimals().get(0).toString());
    }

    @Test
    void testAnimalDoesNotMoveOutOfBounds() {
        String[] commands = {"f", "f", "f", "f", "f", "f"};
        Vector2d initialPosition = new Vector2d(4, 4);
        Simulation simulation = new Simulation(List.of(initialPosition), OptionsParser.parse(commands));

        simulation.run();

        String expectedPositionAndOrientation = "Position: (4,4), Orientation: Północ";
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