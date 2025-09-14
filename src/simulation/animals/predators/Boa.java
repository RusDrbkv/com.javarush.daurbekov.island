package simulation.animals.predators;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Boa extends Predator {
    public static int maxInCell = 10; // Уменьшили с 30 до 10
    
    public Boa() {
        super(15, 3.0, 1, maxInCell);
    }

    public Boa(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Boa> boas = location.getAnimals().stream()
                    .filter(a -> a instanceof Boa)
                    .map(a -> (Boa) a)
                    .collect(Collectors.toList());

            if (boas.size() >= 2 && location.canAddAnimal(Boa.class)) {
                Boa baby = new Boa(15, 3.0, 1, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}