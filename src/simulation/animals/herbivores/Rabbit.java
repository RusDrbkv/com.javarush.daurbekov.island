package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Rabbit extends Herbivore {
    public static int maxInCell = 30; // Уменьшили с 150 до 30
    
    public Rabbit() {
        super(2, 0.45, 2, maxInCell);
    }
    
    public Rabbit(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Rabbit> rabbits = location.getAnimals().stream()
                    .filter(a -> a instanceof Rabbit)
                    .map(a -> (Rabbit) a)
                    .collect(Collectors.toList());

            if (rabbits.size() >= 2 && location.canAddAnimal(Rabbit.class)) {
                Rabbit baby = new Rabbit(2, 0.45, 2, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}