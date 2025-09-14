package simulation.animals.predators;

import simulation.*;
import simulation.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Predator extends Animal {

    public Predator(double weight, double maxSatiety, int speed, int maxInCell) {
        super(weight, maxSatiety, speed, maxInCell);
    }
    
    public Predator(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight, maxSatiety, speed, maxInCell, isBaby);
    }

    @Override
    public void eat() {
        if (location != null) {
            // Сначала попробуем съесть животных (ограничиваем поиск)
            List<Animal> preyList = location.getAnimals().stream()
                    .filter(a -> a != this && a.isAlive())
                    .limit(10) // Ограничиваем поиск до 10 животных для производительности
                    .collect(Collectors.toList());

            if (!preyList.isEmpty()) {
                Animal prey = preyList.get(random.nextInt(preyList.size()));
                eat(prey);
            }
            
            // Если не удалось поесть животных, попробуем растения (для всеядных)
            if (satiety < maxSatiety * 0.3) { // Если сытость меньше 30%
                Plant plant = location.getPlant();
                if (plant.getQuantity() > 0) {
                    eat(plant);
                }
            }
        }
    }

    @Override
    public void eat(Creature food) {
        if (food instanceof Animal) {
            Animal prey = (Animal) food;
            String predatorName = this.getClass().getSimpleName();
            String preyName = prey.getClass().getSimpleName();
            
            // Проверяем, может ли хищник съесть добычу по размеру
            if (canEatPrey(prey)) {
                double probability = Config.getEatingProbability(predatorName, preyName);
                if (random.nextDouble() < probability) {
                    satiety = Math.min(satiety + prey.getWeight(), maxSatiety);
                    prey.die();
                }
            }
        }
    }
    
    // Проверка, может ли хищник съесть добычу по размеру
    private boolean canEatPrey(Animal prey) {
        // Хищник может съесть добычу, если она не больше его в 2 раза
        return prey.getWeight() <= this.weight * 2.0;
    }

    @Override
    public void move() {
        // Хищники двигаются более целенаправленно
        Direction direction = Direction.getRandomDirection();
        move(direction);
    }
}