package agh.ics.oop.model;

public class Animal implements WorldElement{
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public MapDirection getOrientation() { return this.orientation;}

    @Override
    public String toString() {
        return this.orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }


    public void move(MoveDirection direction, MoveValidator moveValidator) {
        Vector2d newPosition = this.position;

        switch (direction) {
            case FORWARD -> newPosition = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(this.orientation.toUnitVector());
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
        }

        if (direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD) {
            if (moveValidator.canMoveTo(newPosition)) {
                this.position = newPosition;
            }
        }
    }

}
