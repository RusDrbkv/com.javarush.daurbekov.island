package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Boar extends Herbivore {
    public static int maxInCell = 50;
    
    public Boar() {
        super(400, 50.0, 2, maxInCell);
    }

    public Boar(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Boar> boars = location.getAnimals().stream()
                    .filter(a -> a instanceof Boar)
                    .map(a -> (Boar) a)
                    .collect(Collectors.toList());

            if (boars.size() >= 2 && location.canAddAnimal(Boar.class)) {
                Boar baby = new Boar(400, 50.0, 2, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}