package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Boar extends Herbivore {
    public Boar() {
        super(400, 50.0, 2, 50, Map.of(
                Plant.class, 1.0,
                Mouse.class, 0.5,
                Caterpillar.class, 0.9
        ));
    }

    @Override
    public void reproduce() {
        List<Boar> boars = location.getAnimals().stream()
                .filter(a -> a instanceof Boar)
                .map(a -> (Boar) a)
                .collect(Collectors.toList());

        if (boars.size() >= 2 && location.canAddAnimal(Boar.class)) {
            Boar baby = new Boar();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {

    }
}