package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Caterpillar extends Herbivore {
    public static int maxInCell = 100; // Уменьшили с 1000 до 100
    
    public Caterpillar() {
        super(0.01, 0.0, 0, maxInCell);
    }

    public Caterpillar(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Caterpillar> caterpillars = location.getAnimals().stream()
                    .filter(a -> a instanceof Caterpillar)
                    .map(a -> (Caterpillar) a)
                    .collect(Collectors.toList());

            if (caterpillars.size() >= 2 && location.canAddAnimal(Caterpillar.class)) {
                Caterpillar baby = new Caterpillar(0.01, 0.0, 0, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}