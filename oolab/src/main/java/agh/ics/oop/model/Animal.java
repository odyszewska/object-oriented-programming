package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        return "Position: " + position + ", Orientation: " + orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }


    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (isWithinBounds(newPosition)) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (isWithinBounds(newPosition)) {
                    position = newPosition;
                }
            }
        }
    }

    private boolean isWithinBounds(Vector2d position) {
        return position.follows(new Vector2d(-1, -1)) && position.precedes(new Vector2d(5, 5));
    }

}
