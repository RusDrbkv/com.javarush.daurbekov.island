package simulation.animals.predators;

import simulation.animals.Animal;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Predator extends Animal {
    protected Map<Class<?>, Double> preyChances;

    public Predator(double weight, double maxSatiety, int speed,
                    int maxInCell, Map<Class<?>, Double> preyChances) {
        super(weight, maxSatiety, speed, maxInCell);
        this.preyChances = preyChances;
    }

    @Override
    public void eat() {
        List<Animal> preyList = location.getAnimals().stream()
                .filter(a -> preyChances.containsKey(a.getClass()))
                .collect(Collectors.toList());

        if (!preyList.isEmpty()) {
            Animal prey = preyList.get(random.nextInt(preyList.size()));
            if (random.nextDouble() < preyChances.get(prey.getClass())) {
                satiety = Math.min(satiety + prey.getWeight(), maxSatiety);
                prey.die();
            }
        }
    }

    @Override
    public void move() {
        int dx = random.nextInt(speed * 2 + 1) - speed;
        int dy = random.nextInt(speed * 2 + 1) - speed;
        location.moveAnimal(this, dx, dy);
    }
}