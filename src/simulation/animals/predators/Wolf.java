package simulation.animals.predators;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Wolf extends Predator {
    public static int maxInCell = 10; // Уменьшили с 30 до 10
    
    public Wolf() {
        super(50, 8.0, 3, maxInCell);
    }
    
    public Wolf(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Wolf> wolves = location.getAnimals().stream()
                    .filter(a -> a instanceof Wolf)
                    .map(a -> (Wolf) a)
                    .collect(Collectors.toList());

            if (wolves.size() >= 2 && location.canAddAnimal(Wolf.class)) {
                Wolf pup = new Wolf(50, 8.0, 3, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(pup, location.getX(), location.getY());
            }
        }
    }
}