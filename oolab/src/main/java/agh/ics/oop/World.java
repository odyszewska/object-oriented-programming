package agh.ics.oop;

import java.util.List;
import java.util.ArrayList;

import agh.ics.oop.model.*;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        try{
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
            RectangularMap rectangularMap = new RectangularMap(10, 10);
            GrassField grassField = new GrassField(10);

            ConsoleMapDisplay consoleDisplay = new ConsoleMapDisplay();

            List<Simulation> simulations = new ArrayList<>();
            Simulation simulation1 = new Simulation(positions, directions, rectangularMap);
            Simulation simulation2 = new Simulation(positions, directions, grassField);
            simulations.add(simulation1);
            simulations.add(simulation2);

            rectangularMap.addObserver(consoleDisplay);
            grassField.addObserver(consoleDisplay);

            SimulationEngine engine = new SimulationEngine(simulations);
            //engine.runSync();
            //engine.runAsync();
            for(int i=0; i<1000; i++){
                grassField = new GrassField(10);
                simulations.add(new Simulation(positions, directions, grassField));
                grassField.addObserver(consoleDisplay);
            }
            engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool();
            engine.awaitSimulationsEnd();
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }

        System.out.println("System zakończył działanie");
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
