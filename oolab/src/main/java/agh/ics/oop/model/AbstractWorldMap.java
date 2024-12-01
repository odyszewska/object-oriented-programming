package agh.ics.oop.model;

import java.util.*;

import agh.ics.oop.model.util.MapVisualizer;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();
    private final UUID id;

    @Override
    public void place(Animal animal) throws IncorrectPositionException{
        if(!canMoveTo(animal.getPosition())) {
            throw new IncorrectPositionException(animal.getPosition());
        }
        animals.put(animal.getPosition(), animal);
        mapChanged("New animal placed on position: " + animal.getPosition());
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();
        if (!oldPosition.equals(newPosition)){
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
            mapChanged("Animal moved from: "+oldPosition+" to: "+newPosition);
        }

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
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public abstract Boundary getCurrentBounds();


    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Boundary bounds = this.getCurrentBounds();
        return mapVisualizer.draw(getCurrentBounds().lowerLeft(), getCurrentBounds().upperRight());
    }

    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }
    public void removeObserver(MapChangeListener observer){
        observers.remove(observers);
    }
    protected void mapChanged(String message) {
        for (MapChangeListener observer: observers){
            observer.mapChanged(this,message);
        }
    }

    protected AbstractWorldMap(UUID id){
        this.id = id;
    }
    public UUID getId() {
        return this.id;
    }
}


