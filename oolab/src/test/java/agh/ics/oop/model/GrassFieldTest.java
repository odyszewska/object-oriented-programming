package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;


class GrassFieldTest {
    @Test
    public void testPlaceAnimal() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position);

        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            System.out.println("error");
        }
    }

    @Test
    public void testPlaceOnOccupiedPosition() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal1 = new Animal(position);


        Animal animal2 = new Animal(position);
        try{
            map.place(animal1);
            map.place(animal2);
        }catch (IncorrectPositionException e){
            assertEquals("Position (2,2) is not correct.", e.getMessage());
        }
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
        try {
            map.place(animal);
        } catch (IncorrectPositionException e) {
            System.out.println("error");
        }
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
        try {
            map.place(animal);
        } catch (IncorrectPositionException e){
            System.out.println("error");
        }

        assertTrue(map.isOccupied(position));
    }

    @Test
    public void testObjectAt() {
        GrassField map = new GrassField(10);
        Vector2d position = new Vector2d(2, 2);
        Animal animal = new Animal(position);
        try{
            map.place(animal);
        } catch (IncorrectPositionException e){
            System.out.println("error");
        }

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
        try {
            map.place(animal);
        }catch (IncorrectPositionException e){
            System.out.println("error");
        }

        List<WorldElement> elements = map.getElements();
        assertEquals(5, elements.size());
    }


}