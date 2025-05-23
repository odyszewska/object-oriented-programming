package agh.ics.oop.model;

import java.util.UUID;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;

    public RectangularMap(int width, int height) {
        super(UUID.randomUUID());
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
                && position.precedes(new Vector2d(width +1, height +1))
                && super.canMoveTo(position);
    }




    @Override
    public Boundary getCurrentBounds(){
        return new Boundary(new Vector2d(0,0),new Vector2d(width,height));
    }

}
