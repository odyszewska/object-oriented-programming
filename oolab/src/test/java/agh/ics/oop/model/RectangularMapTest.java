package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    public void testWidthAndHeight() {
        RectangularMap map = new RectangularMap(3,7);
        assertEquals(3, map.getWidth());
        assertEquals(7, map.getHeight());
    }

    @Test
    public void canMakeValidMove() {
        RectangularMap map = new RectangularMap(5,5);
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
    }

    @Test
    public void cantMoveToExistingAnimal() {
        Vector2d initialPosition = new Vector2d(2,2);
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(initialPosition));
        assertFalse(map.canMoveTo(initialPosition));
    }

    @Test
    public void cantMoveBeyondTopEdge () {
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.canMoveTo(new Vector2d(4,6)));
    }

    @Test
    public void cantMoveBeyondRightEdge () {
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.canMoveTo(new Vector2d(6,3)));
    }

    @Test
    public void cantMoveBeyondBottomEdge () {
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.canMoveTo(new Vector2d(5,-1)));
    }

    @Test
    public void cantMoveBeyondLeftEdge () {
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.canMoveTo(new Vector2d(-1,8)));
    }


    @Test
    public void placeValid() {
        Vector2d initialPosition = new Vector2d(2,2);
        RectangularMap map = new RectangularMap(5,5);
        assertTrue(map.place(new Animal(initialPosition)));
    }

    @Test
    public void placeInvalidOccupied() {
        Vector2d initialPosition = new Vector2d(2,2);
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(initialPosition));
        assertFalse(map.place(new Animal(initialPosition)));
    }


    @Test
    public void isNotOccupied() {
        Vector2d initialPosition = new Vector2d(2,2);
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.isOccupied(initialPosition));
    }

    @Test
    public void isOccupied() {
        Vector2d initialPosition = new Vector2d(2,2);
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(initialPosition));
        assertTrue(map.isOccupied(initialPosition));
    }

    @Test
    public void objectAt() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal(new Vector2d(2,2));
        map.place(animal1);

        assertEquals(animal1, map.objectAt(new Vector2d(2,2)));
    }


}