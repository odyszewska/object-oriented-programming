package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;
    private WorldMap map;


    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap map) {
        this.animals = new ArrayList<>();
        this.moves = moves;
        this.map = map;
        for (Vector2d position: positions){
            this.animals.add(new Animal(position));
        }
        for (Animal animal: animals){
            this.map.place(animal);
        }
    }

    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            Animal currentAnimal = animals.get(i % animals.size());

            MoveDirection direction = moves.get(i);
            map.move(currentAnimal, direction);

            System.out.println("Zwierzę " + (i % animals.size()) + ": " + currentAnimal);
            System.out.println(map.toString());
        }
    }

    public List<MoveDirection> getMoves(){
        return moves;
    }
    public List<Animal> getAnimals(){
        return animals;
    }
}
