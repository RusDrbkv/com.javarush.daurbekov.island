package simulation.animals.herbivores;

import simulation.Plant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(2, 0.45, 2, 150, Map.of(Plant.class, 1.0));
    }

    @Override
    public void reproduce() {
        List<Rabbit> rabbits = location.getAnimals().stream()
                .filter(a -> a instanceof Rabbit)
                .map(a -> (Rabbit) a)
                .collect(Collectors.toList());

        if (rabbits.size() >= 2 && location.canAddAnimal(Rabbit.class)) {
            Rabbit baby = new Rabbit();
            location.addAnimal(baby);
        }
    }

    @Override
    public void move() {
        int dx = random.nextInt(speed * 2 + 1) - speed;
        int dy = random.nextInt(speed * 2 + 1) - speed;
        location.moveAnimal(this, dx, dy);
    }
}