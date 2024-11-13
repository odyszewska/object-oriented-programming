package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final int width;
    private final int height;
    private Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0,0), new Vector2d(width,height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (animals.containsKey(position)) {
            return false;
        }
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height));
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition(), animal);
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
    }
}
