package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Sheep extends Herbivore {
    public static int maxInCell = 5; // Уменьшили до 5
    
    public Sheep() {
        super(70, 15.0, 3, maxInCell);
    }

    public Sheep(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Sheep> sheep = location.getAnimals().stream()
                    .filter(a -> a instanceof Sheep)
                    .map(a -> (Sheep) a)
                    .collect(Collectors.toList());

            if (sheep.size() >= 2 && location.canAddAnimal(Sheep.class)) {
                Sheep baby = new Sheep(70, 15.0, 3, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}