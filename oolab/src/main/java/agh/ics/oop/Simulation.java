package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.animals = positions.stream().map(Animal::new).toList();
        this.moves = moves;
    }

    public void run() {
        int numberOfAnimals = animals.size();
        for (int i = 0; i < moves.size(); i++) {
            Animal currentAnimal = animals.get(i % numberOfAnimals);

            MoveDirection move = moves.get(i);
            currentAnimal.move(move);

            System.out.println("Zwierzę " + (i % numberOfAnimals) + ": " + currentAnimal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
