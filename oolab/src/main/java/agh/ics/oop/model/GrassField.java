package agh.ics.oop.model;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;



public class GrassField extends AbstractWorldMap {
    private int width;
    private int height;

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
    public List <WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(super.getElements());
        elements.addAll(grasses.values());
        return elements;
    }
    @Override
    public Boundary getCurrentBounds(){
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Map.Entry<Vector2d, Animal> i:super.animals.entrySet()){
            lowerLeft = lowerLeft.lowerLeft(i.getKey());
            upperRight = upperRight.upperRight(i.getKey());
        }
        for (Map.Entry<Vector2d, Grass> i:this.grasses.entrySet()){
            lowerLeft = lowerLeft.lowerLeft(i.getKey());
            upperRight = upperRight.upperRight(i.getKey());
        }

        return new Boundary(lowerLeft,upperRight);
    }
}
