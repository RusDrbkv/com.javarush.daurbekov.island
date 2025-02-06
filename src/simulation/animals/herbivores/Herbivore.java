package simulation.animals.herbivores;

import simulation.Plant;
import simulation.animals.Animal;

import java.util.Map;

public abstract class Herbivore extends Animal {
    protected Map<Class<?>, Double> foodChances;

    public Herbivore(double weight, double maxSatiety, int speed,
                     int maxInCell, Map<Class<?>, Double> foodChances) {
        super(weight, maxSatiety, speed, maxInCell);
        this.foodChances = foodChances;
    }

    @Override
    public void eat() {
        if (foodChances.containsKey(Plant.class)) {
            Plant plant = location.getPlant();
            if (plant.getQuantity() > 0 && random.nextDouble() < foodChances.get(Plant.class)) {
                satiety = Math.min(satiety + plant.getNutritionValue(), maxSatiety);
                plant.consume();
            }
        }
    }
}