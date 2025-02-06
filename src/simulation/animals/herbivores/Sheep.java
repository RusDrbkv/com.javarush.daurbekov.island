package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Sheep extends Herbivore {
    public Sheep() {
        super(70, 15.0, 3, 140, Map.of(
                Plant.class, 1.0
        ));
    }

    @Override
    public void reproduce() {
        List<Sheep> sheeps = location.getAnimals().stream()
                .filter(a -> a instanceof Sheep)
                .map(a -> (Sheep) a)
                .collect(Collectors.toList());

        if (sheeps.size() >= 2 && location.canAddAnimal(Sheep.class)) {
            Sheep baby = new Sheep();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}