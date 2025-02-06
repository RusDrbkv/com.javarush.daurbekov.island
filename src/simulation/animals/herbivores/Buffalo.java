package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(700, 100.0, 3, 10, Map.of(
                Plant.class, 1.0
        ));
    }

    @Override
    public void reproduce() {
        List<Buffalo> buffalos = location.getAnimals().stream()
                .filter(a -> a instanceof Buffalo)
                .map(a -> (Buffalo) a)
                .collect(Collectors.toList());

        if (buffalos.size() >= 2 && location.canAddAnimal(Buffalo.class)) {
            Buffalo baby = new Buffalo();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}