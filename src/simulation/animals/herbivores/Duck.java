package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Duck extends Herbivore {
    public static int maxInCell = 30; // Уменьшили с 200 до 30
    
    public Duck() {
        super(1, 0.15, 4, maxInCell);
    }

    public Duck(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Duck> ducks = location.getAnimals().stream()
                    .filter(a -> a instanceof Duck)
                    .map(a -> (Duck) a)
                    .collect(Collectors.toList());

            if (ducks.size() >= 2 && location.canAddAnimal(Duck.class)) {
                Duck baby = new Duck(1, 0.15, 4, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}