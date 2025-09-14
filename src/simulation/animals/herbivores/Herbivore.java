package simulation.animals.herbivores;

import simulation.*;
import simulation.animals.Animal;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, double maxSatiety, int speed, int maxInCell) {
        super(weight, maxSatiety, speed, maxInCell);
    }
    
    public Herbivore(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void eat() {
        if (location != null) {
            Plant plant = location.getPlant();
            if (plant.getQuantity() > 0) {
                eat(plant);
            }
        }
    }

    @Override
    public void move() {
        // Травоядные двигаются случайно
        Direction direction = Direction.getRandomDirection();
        move(direction);
    }
}