package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void testOptionParserWithValidCommands() {
        String[] commands = {"f", "b", "r", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
        assertEquals(expected, OptionsParser.parse(commands));
    }
    @Test
    void testOptionParserWithEmptyCommands() {
        String[] emptyCommands = {};
        List<MoveDirection> expected = List.of();
        assertEquals(expected, OptionsParser.parse(emptyCommands));
    }

    @Test
    void testOptionParserWithInvalidCommands() {
        String[] commandsWithInvalid = {"f", "b", "invalid", "r", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(commandsWithInvalid));
        assertEquals("invalid is not legal move specification", exception.getMessage());
    }
}