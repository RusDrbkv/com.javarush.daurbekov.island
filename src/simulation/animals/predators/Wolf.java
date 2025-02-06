package simulation.animals.predators;

import simulation.animals.herbivores.Duck;
import simulation.animals.herbivores.Mouse;
import simulation.animals.herbivores.Rabbit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Wolf extends Predator {
    public Wolf() {
        super(50, 8.0, 3, 30, Map.of(
                Rabbit.class, 0.6,
                Mouse.class, 0.8,
                Duck.class, 0.4
        ));
    }

    @Override
    public void reproduce() {
        List<Wolf> wolves = location.getAnimals().stream()
                .filter(a -> a instanceof Wolf)
                .map(a -> (Wolf) a)
                .collect(Collectors.toList());

        if (wolves.size() >= 2 && location.canAddAnimal(Wolf.class)) {
            Wolf pup = new Wolf();
            location.addAnimal(pup);
        }
    }
}