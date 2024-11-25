package agh.ics.oop;

import java.util.List;

import agh.ics.oop.model.*;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        GrassField map = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }
    public static void run(MoveDirection[] directions){
        for (MoveDirection direction : directions) {
            switch(direction){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }

}
