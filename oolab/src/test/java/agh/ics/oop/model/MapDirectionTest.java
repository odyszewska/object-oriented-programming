package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void testNext(){
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
    }

    @Test
    void testPrevious(){
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
    }

    @Test
    void testToString(){
        assertEquals("^",MapDirection.NORTH.toString());
        assertEquals(">",MapDirection.EAST.toString());
        assertEquals("v",MapDirection.SOUTH.toString());
        assertEquals("<",MapDirection.WEST.toString());
    }
}