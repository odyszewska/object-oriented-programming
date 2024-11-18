package agh.ics.oop.model;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Grass> grasses = new HashMap<>();
    protected final Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition(), animal);
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
    }

    @Override
    public Vector2d getPosition() {
        return new Vector2d(0, 0);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return this.animals.get(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return animals.values().stream()
                .map(animal -> (WorldElement) animal)
                .collect(Collectors.toList());
    }


}


