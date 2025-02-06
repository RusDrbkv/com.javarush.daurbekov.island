package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Goat extends Herbivore {
    public Goat() {
        super(60, 10.0, 3, 140, Map.of(
                Plant.class, 1.0
        ));
    }

    @Override
    public void reproduce() {
        List<Goat> goats = location.getAnimals().stream()
                .filter(a -> a instanceof Goat)
                .map(a -> (Goat) a)
                .collect(Collectors.toList());

        if (goats.size() >= 2 && location.canAddAnimal(Goat.class)) {
            Goat baby = new Goat();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}