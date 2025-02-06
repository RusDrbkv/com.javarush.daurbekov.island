package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Horse extends Herbivore {
    public Horse() {
        super(400, 60.0, 4, 20, Map.of(
                Plant.class, 1.0
        ));
    }

    @Override
    public void reproduce() {
        List<Horse> horses = location.getAnimals().stream()
                .filter(a -> a instanceof Horse)
                .map(a -> (Horse) a)
                .collect(Collectors.toList());

        if (horses.size() >= 2 && location.canAddAnimal(Horse.class)) {
            Horse baby = new Horse();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}