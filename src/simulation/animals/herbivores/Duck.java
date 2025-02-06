package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Duck extends Herbivore {
    public Duck() {
        super(1, 0.15, 4, 200, Map.of(
                Plant.class, 1.0,
                Caterpillar.class, 0.9
        ));
    }

    @Override
    public void reproduce() {
        List<Duck> ducks = location.getAnimals().stream()
                .filter(a -> a instanceof Duck)
                .map(a -> (Duck) a)
                .collect(Collectors.toList());

        if (ducks.size() >= 2 && location.canAddAnimal(Duck.class)) {
            Duck baby = new Duck();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}