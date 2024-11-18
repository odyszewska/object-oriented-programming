package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

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
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0))
                && position.precedes(new Vector2d(width - 1, height - 1))
                && super.canMoveTo(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0,0), new Vector2d(width-1,height-1));
    }




}
