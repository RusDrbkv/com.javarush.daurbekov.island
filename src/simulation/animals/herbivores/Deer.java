package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Deer extends Herbivore {
    public Deer() {
        super(300, 50.0, 4, 20, Map.of(
                Plant.class, 1.0
        ));
    }

    @Override
    public void reproduce() {
        List<Deer> deers = location.getAnimals().stream()
                .filter(a -> a instanceof Deer)
                .map(a -> (Deer) a)
                .collect(Collectors.toList());

        if (deers.size() >= 2 && location.canAddAnimal(Deer.class)) {
            Deer baby = new Deer();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}