package simulation.animals.herbivores;

import simulation.*;
import java.util.List;
import java.util.stream.Collectors;

public class Mouse extends Herbivore {
    public static int maxInCell = 50; // Уменьшили с 500 до 50
    
    public Mouse() {
        super(0.05, 0.01, 1, maxInCell);
    }
    
    public Mouse(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void eat() {
        if (location != null) {
            Plant plant = location.getPlant();
            if (plant.getQuantity() > 0) {
                eat(plant);
            }
            
            // Мыши также могут есть гусениц
            List<simulation.animals.Animal> caterpillars = location.getAnimals().stream()
                    .filter(a -> a instanceof Caterpillar)
                    .collect(Collectors.toList());
            
            if (!caterpillars.isEmpty()) {
                simulation.animals.Animal caterpillar = caterpillars.get(random.nextInt(caterpillars.size()));
                eat(caterpillar);
            }
        }
    }

    @Override
    public void reproduce() {
        if (location != null && canReproduce()) {
            List<Mouse> mice = location.getAnimals().stream()
                    .filter(a -> a instanceof Mouse)
                    .map(a -> (Mouse) a)
                    .collect(Collectors.toList());

            if (mice.size() >= 2 && location.canAddAnimal(Mouse.class)) {
                Mouse baby = new Mouse(0.05, 0.01, 1, maxInCell, true); // true = детеныш
                // Используем Island.addAnimal() для проверки общего лимита
                location.getIsland().addAnimal(baby, location.getX(), location.getY());
            }
        }
    }
}