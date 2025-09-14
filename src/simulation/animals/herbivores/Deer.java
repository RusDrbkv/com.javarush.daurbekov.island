package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Deer extends Herbivore {
    public static int maxInCell = 20;
    
    public Deer() {
        super(300, 50.0, 4, maxInCell);
    }

    public Deer(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Deer> deers = location.getAnimals().stream()
                    .filter(a -> a instanceof Deer)
                    .map(a -> (Deer) a)
                    .collect(Collectors.toList());

            if (deers.size() >= 2 && location.canAddAnimal(Deer.class)) {
                Deer baby = new Deer(300, 50.0, 4, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}