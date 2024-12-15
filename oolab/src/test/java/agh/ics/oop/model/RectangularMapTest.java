package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

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
        try{
            map.place(new Animal(initialPosition));
        } catch (IncorrectPositionException e){
            System.out.println("error");
        }
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
        try{
            map.place(new Animal(initialPosition));
        }catch (IncorrectPositionException e){
            System.out.println("error");
        }
    }

    @Test
    public void placeInvalidOccupied() {
        Vector2d initialPosition = new Vector2d(2,2);
        RectangularMap map = new RectangularMap(5,5);
        try{
            map.place(new Animal(initialPosition));
        } catch (IncorrectPositionException e){
            System.out.println("error");
        }
        Exception exception = assertThrows(IncorrectPositionException.class, () -> map.place(new Animal(initialPosition)));
        assertEquals("Position (2,2) is not correct.", exception.getMessage());
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
        try{
            map.place(new Animal(initialPosition));
        } catch (IncorrectPositionException e){
            System.out.println("error");
        }
        assertTrue(map.isOccupied(initialPosition));
    }

    @Test
    public void objectAt() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal(new Vector2d(2,2));
        try{
            map.place(animal1);
        }catch (IncorrectPositionException e){
            System.out.println("error");
        }

        assertEquals(animal1, map.objectAt(new Vector2d(2,2)));
    }

    @Test
    public void testGetElementsRectangularMap() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        try {
            map.place(animal1);
            map.place(animal2);
        }catch (IncorrectPositionException e){
            System.out.println("error");
        }
        List<WorldElement> elements = map.getElements();
        assertEquals(2, elements.size());

        assertTrue(elements.contains(animal1));
        assertTrue(elements.contains(animal2));
    }

}