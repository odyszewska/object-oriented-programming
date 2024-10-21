package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        MoveDirection[] directions = OptionsParser.parse(args);
        run(directions);
        System.out.println("system zakończył działanie");
    }
    public static void run(MoveDirection[] directions){
        for (MoveDirection direction : directions) {
            switch(direction){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
                default -> System.out.println("Ignorowany argument");
            }
        }
    }

}
