package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Horse extends Herbivore {
    public static int maxInCell = 20;
    
    public Horse() {
        super(400, 60.0, 4, maxInCell);
    }

    public Horse(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Horse> horses = location.getAnimals().stream()
                    .filter(a -> a instanceof Horse)
                    .map(a -> (Horse) a)
                    .collect(Collectors.toList());

            if (horses.size() >= 2 && location.canAddAnimal(Horse.class)) {
                Horse baby = new Horse(400, 60.0, 4, maxInCell, true); // true = детеныш
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}