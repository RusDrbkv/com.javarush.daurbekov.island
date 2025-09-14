package simulation.animals.predators;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Fox extends Predator {
    public static int maxInCell = 5; // Уменьшили до 5
    
    public Fox() {
        super(8, 2.0, 2, maxInCell);
    }

    public Fox(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Fox> foxes = location.getAnimals().stream()
                    .filter(a -> a instanceof Fox)
                    .map(a -> (Fox) a)
                    .collect(Collectors.toList());

            if (foxes.size() >= 2 && location.canAddAnimal(Fox.class)) {
                Fox baby = new Fox(8, 2.0, 2, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}