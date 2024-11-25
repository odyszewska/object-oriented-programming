package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Collection;

class GrassFieldTest {
    @Test
    public void testPlaceAnimal() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position);

        assertTrue(map.place(animal));
    }

    @Test
    public void testPlaceOnOccupiedPosition() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal1 = new Animal(position);
        map.place(animal1);

        Animal animal2 = new Animal(position);
        assertFalse(map.place(animal2));
    }

    @Test
    public void testCanMoveToValidPosition() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(3, 3);

        assertTrue(map.canMoveTo(position));
    }

    @Test
    public void testCanMoveToOccupiedPosition() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(3, 3);
        Animal animal = new Animal(position);
        map.place(animal);

        assertFalse(map.canMoveTo(position));
    }

    @Test
    public void testCanMoveToGrassPosition() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(4, 4);

        assertTrue(map.canMoveTo(position));
    }

    @Test
    public void testOccupied() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position);
        map.place(animal);

        assertTrue(map.isOccupied(position));
    }

    @Test
    public void testObjectAt() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position);
        map.place(animal);

        assertEquals(animal, map.objectAt(position));
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
        RectangularMap map = new RectangularMap(5, 5);
        assertFalse(map.canMoveTo(new Vector2d(-1, 8)));
    }

    @Test
    public void testGetElementsGrassField() {
        GrassField map = new GrassField(4);
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);

        Collection<WorldElement> elements = map.getElements();
        assertEquals(5, elements.size());
    }

}