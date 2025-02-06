package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mouse extends Herbivore {
    public Mouse() {
        super(0.05, 0.01, 1, 500, Map.of(
                Plant.class, 1.0,
                Caterpillar.class, 0.9
        ));
    }

    @Override
    public void reproduce() {
        List<Mouse> mice = location.getAnimals().stream()
                .filter(a -> a instanceof Mouse)
                .map(a -> (Mouse) a)
                .collect(Collectors.toList());

        if (mice.size() >= 2 && location.canAddAnimal(Mouse.class)) {
            Mouse baby = new Mouse();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}