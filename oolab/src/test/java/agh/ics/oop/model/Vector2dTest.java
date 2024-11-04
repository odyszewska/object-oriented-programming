package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void testEquals() {
        assertEquals(new Vector2d(1, 2), new Vector2d(1, 2));
        assertNotEquals(new Vector2d(1, 2), new Vector2d(2, 1));
        assertNotEquals(new Vector2d(1, 2), "some string");
    }

    @Test
    void testToString() {
        assertEquals("(1,2)", new Vector2d(1, 2).toString());
    }

    @Test
    void testPrecedes() {
        assertTrue(new Vector2d(1, 2).precedes(new Vector2d(2, 3)));
        assertFalse(new Vector2d(3, 4).precedes(new Vector2d(2, 3)));
    }

    @Test
    void testFollows(){
        assertTrue(new Vector2d(3, 4).follows(new Vector2d(2, 3)));
        assertFalse(new Vector2d(1, 2).follows(new Vector2d(2, 3)));
    }

    @Test
    void testUpperRight() {
        assertEquals(new Vector2d(3, 4), new Vector2d(1, 4).upperRight(new Vector2d(3, 2)));
    }

    @Test
    void testLowerLeft() {
        assertEquals(new Vector2d(1, 2), new Vector2d(1, 4).lowerLeft(new Vector2d(3, 2)));
    }

    @Test
    void testAdd() {
        assertEquals(new Vector2d(3, 5), new Vector2d(1, 2).add(new Vector2d(2, 3)));
    }

    @Test
    void testSubtract() {
        assertEquals(new Vector2d(-1, -1), new Vector2d(1, 2).subtract(new Vector2d(2, 3)));
    }

    @Test
    void testOpposite() {
        assertEquals(new Vector2d(-1, -2), new Vector2d(1, 2).opposite());
    }
}