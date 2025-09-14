package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Goat extends Herbivore {
    public static int maxInCell = 20; // Уменьшили с 140 до 20
    
    public Goat() {
        super(60, 10.0, 3, maxInCell);
    }

    public Goat(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Goat> goats = location.getAnimals().stream()
                    .filter(a -> a instanceof Goat)
                    .map(a -> (Goat) a)
                    .collect(Collectors.toList());

            if (goats.size() >= 2 && location.canAddAnimal(Goat.class)) {
                Goat baby = new Goat(60, 10.0, 3, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}