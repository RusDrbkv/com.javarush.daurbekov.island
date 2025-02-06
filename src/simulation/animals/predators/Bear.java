package simulation.animals.predators;

import simulation.animals.herbivores.Mouse;
import simulation.animals.herbivores.Rabbit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bear extends Predator {
    public Bear() {
        super(500, 80.0, 2, 5, Map.of(
                Wolf.class, 0.8,
                Rabbit.class, 0.8,
                Mouse.class, 0.9
        ));
    }

    @Override
    public void reproduce() {
        List<Bear> bears = location.getAnimals().stream()
                .filter(a -> a instanceof Bear)
                .map(a -> (Bear) a)
                .collect(Collectors.toList());

        if (bears.size() >= 2 && location.canAddAnimal(Bear.class)) {
            Bear baby = new Bear();
            location.addAnimal(baby);
        }
    }
}