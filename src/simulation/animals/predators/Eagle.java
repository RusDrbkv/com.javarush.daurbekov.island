package simulation.animals.predators;

import simulation.animals.herbivores.Duck;
import simulation.animals.herbivores.Mouse;
import simulation.animals.herbivores.Rabbit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Eagle extends Predator {
    public Eagle() {
        super(6, 1.0, 3, 20, Map.of(
                Rabbit.class, 0.9,
                Mouse.class, 0.9,
                Duck.class, 0.8
        ));
    }

    @Override
    public void reproduce() {
        List<Eagle> eagles = location.getAnimals().stream()
                .filter(a -> a instanceof Eagle)
                .map(a -> (Eagle) a)
                .collect(Collectors.toList());

        if (eagles.size() >= 2 && location.canAddAnimal(Eagle.class)) {
            Eagle baby = new Eagle();
            location.addAnimal(baby);
        }
    }
}