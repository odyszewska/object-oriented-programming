package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AnimalTest {
    private Animal animal;

    @Test
    void testInitialPositionAndOrientation() {
        animal = new Animal(new Vector2d(2, 2));
        assertEquals("Position: (2,2), Orientation: Północ", animal.toString());
    }

    @Test
    void testMoveForward() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.FORWARD);
        assertEquals("Position: (2,3), Orientation: Północ", animal.toString());
    }

    @Test
    void testMoveBackward() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.BACKWARD);
        assertEquals("Position: (2,1), Orientation: Północ", animal.toString());
    }

    @Test
    void testTurnRight() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.RIGHT);
        assertEquals("Position: (2,2), Orientation: Wschód", animal.toString());
    }

    @Test
    void testTurnLeft() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.LEFT);
        assertEquals("Position: (2,2), Orientation: Zachód", animal.toString());
    }

    @Test
    void testMoveOutOfBounds() {
        animal = new Animal(new Vector2d(4, 4));
        animal.move(MoveDirection.FORWARD);
        assertEquals("Position: (4,4), Orientation: Północ", animal.toString());
        animal.move(MoveDirection.RIGHT);
        assertEquals("Position: (4,4), Orientation: Wschód", animal.toString());
        animal.move(MoveDirection.FORWARD);
        assertEquals("Position: (4,4), Orientation: Wschód", animal.toString());
    }
    @Test
    void testMoveOutOfBounds2() {
        animal = new Animal(new Vector2d(0, 0));
        animal.move(MoveDirection.BACKWARD);
        assertEquals("Position: (0,0), Orientation: Północ", animal.toString());
        animal.move(MoveDirection.LEFT);
        assertEquals("Position: (0,0), Orientation: Zachód", animal.toString());
        animal.move(MoveDirection.FORWARD);
        assertEquals("Position: (0,0), Orientation: Zachód", animal.toString());
    }

    @Test
    void testIsAt() {
        animal = new Animal(new Vector2d(2, 2));
        assertTrue(animal.isAt(new Vector2d(2, 2)));
        assertFalse(animal.isAt(new Vector2d(3, 3)));
        assertFalse(animal.isAt(new Vector2d(2, 3)));
        assertFalse(animal.isAt(new Vector2d(3, 2)));
    }
}