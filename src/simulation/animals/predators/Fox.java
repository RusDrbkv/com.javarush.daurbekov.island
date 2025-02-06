package simulation.animals.predators;

import simulation.animals.herbivores.Caterpillar;
import simulation.animals.herbivores.Duck;
import simulation.animals.herbivores.Mouse;
import simulation.animals.herbivores.Rabbit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fox extends Predator {
    public Fox() {
        super(8, 2.0, 2, 30, Map.of(
                Rabbit.class, 0.7,
                Mouse.class, 0.9,
                Duck.class, 0.6,
                Caterpillar.class, 0.4
        ));
    }

    @Override
    public void reproduce() {
        List<Fox> foxes = location.getAnimals().stream()
                .filter(a -> a instanceof Fox)
                .map(a -> (Fox) a)
                .collect(Collectors.toList());

        if (foxes.size() >= 2 && location.canAddAnimal(Fox.class)) {
            Fox baby = new Fox();
            location.addAnimal(baby);
        }
    }
}