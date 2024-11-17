package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AnimalTest {
    private Animal animal;

    @Test
    void testInitialPositionAndOrientation() {
        animal = new Animal(new Vector2d(2, 2));
        assertEquals("^", animal.toString());
    }

    @Test
    void testMoveForward() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.FORWARD,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("^", animal.toString());
    }

    @Test
    void testMoveBackward() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.BACKWARD,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("^", animal.toString());
    }

    @Test
    void testTurnRight() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.RIGHT,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals(">", animal.toString());
    }

    @Test
    void testTurnLeft() {
        animal = new Animal(new Vector2d(2, 2));
        animal.move(MoveDirection.LEFT,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("<", animal.toString());
    }

    @Test
    void testMoveOutOfBounds() {
        animal = new Animal(new Vector2d(4, 4));
        animal.move(MoveDirection.FORWARD,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("^", animal.toString());
        animal.move(MoveDirection.RIGHT,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals(">", animal.toString());
        animal.move(MoveDirection.FORWARD,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals(">", animal.toString());
    }
    @Test
    void testMoveOutOfBounds2() {
        animal = new Animal(new Vector2d(0, 0));
        animal.move(MoveDirection.BACKWARD,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("^", animal.toString());
        animal.move(MoveDirection.LEFT,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("<", animal.toString());
        animal.move(MoveDirection.FORWARD,new MoveValidator(){
            public boolean canMoveTo(Vector2d position){
                return true;
            }
        });
        assertEquals("<", animal.toString());
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