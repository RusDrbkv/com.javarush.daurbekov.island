package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(0.01, 0.0, 0, 1000, Map.of(
                Plant.class, 1.0
        ));
    }

    @Override
    public void move() {
        // Гусеницы не перемещаются
    }

    @Override
    public void reproduce() {
        List<Caterpillar> caterpillars = location.getAnimals().stream()
                .filter(a -> a instanceof Caterpillar)
                .map(a -> (Caterpillar) a)
                .collect(Collectors.toList());

        if (caterpillars.size() >= 2 && location.canAddAnimal(Caterpillar.class)) {
            Caterpillar baby = new Caterpillar();
            location.addAnimal(baby);
        }
    }
}