package simulation.animals.predators;

import simulation.animals.herbivores.Duck;
import simulation.animals.herbivores.Mouse;
import simulation.animals.herbivores.Rabbit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Boa extends Predator {
    public Boa() {
        super(15, 3.0, 1, 30, Map.of(
                Rabbit.class, 0.2,
                Mouse.class, 0.4,
                Duck.class, 0.1
        ));
    }

    @Override
    public void reproduce() {
        List<Boa> boas = location.getAnimals().stream()
                .filter(a -> a instanceof Boa)
                .map(a -> (Boa) a)
                .collect(Collectors.toList());

        if (boas.size() >= 2 && location.canAddAnimal(Boa.class)) {
            Boa baby = new Boa();
            location.addAnimal(baby);
        }
    }
}