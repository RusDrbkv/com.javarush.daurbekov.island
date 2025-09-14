package simulation.animals.predators;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Eagle extends Predator {
    public static int maxInCell = 5;
    
    public Eagle() {
        super(6, 1.0, 3, maxInCell);
    }

    public Eagle(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Eagle> eagles = location.getAnimals().stream()
                    .filter(a -> a instanceof Eagle)
                    .map(a -> (Eagle) a)
                    .collect(Collectors.toList());

            if (eagles.size() >= 2 && location.canAddAnimal(Eagle.class)) {
                Eagle baby = new Eagle(6, 1.0, 3, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}