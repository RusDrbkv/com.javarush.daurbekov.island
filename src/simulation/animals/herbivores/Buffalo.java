package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Buffalo extends Herbivore {
    public static int maxInCell = 3;
    
    public Buffalo() {
        super(700, 100.0, 3, maxInCell);
    }

    public Buffalo(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Buffalo> buffalos = location.getAnimals().stream()
                    .filter(a -> a instanceof Buffalo)
                    .map(a -> (Buffalo) a)
                    .collect(Collectors.toList());

            if (buffalos.size() >= 2 && location.canAddAnimal(Buffalo.class)) {
                Buffalo baby = new Buffalo(700, 100.0, 3, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}