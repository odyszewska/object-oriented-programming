package agh.ics.oop.model;

import java.util.Random;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;



public class GrassField extends AbstractWorldMap {
    public GrassField(int n) {
        int maxCoordinate = (int) Math.sqrt(n * 10);

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            Vector2d position;
            do {
                int x = random.nextInt(maxCoordinate + 1);
                int y = random.nextInt(maxCoordinate + 1);
                position = new Vector2d(x, y);
            } while (grasses.containsKey(position));

            Grass grass = new Grass(position);
            grasses.put(position, grass);
        }
    }


    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);

        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Map.Entry<Vector2d, Animal> i : super.animals.entrySet()) {
            Vector2d position = i.getKey();
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }
        for (Map.Entry<Vector2d, Grass> i : this.grasses.entrySet()) {
            Vector2d position = i.getKey();
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }

        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (!super.isOccupied(position)) {
            return this.grasses.get(position);
        } else {
            return super.objectAt(position);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || this.grasses.containsKey(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = new ArrayList<>(super.getElements());
        elements.addAll(grasses.values());
        return elements;
    }


}
