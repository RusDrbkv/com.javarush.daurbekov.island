package simulation.animals.predators;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Bear extends Predator {
    public static int maxInCell = 5;
    
    public Bear() {
        super(500, 80.0, 2, maxInCell);
    }

    public Bear(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    // Убираем переопределение eat() - используем базовую логику из Predator

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Bear> bears = location.getAnimals().stream()
                    .filter(a -> a instanceof Bear)
                    .map(a -> (Bear) a)
                    .collect(Collectors.toList());

            if (bears.size() >= 2 && location.canAddAnimal(Bear.class)) {
                Bear baby = new Bear(500, 80.0, 2, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}