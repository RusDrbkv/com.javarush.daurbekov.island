package simulation;

import simulation.animals.Animal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Location {
    private final int x, y;
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final Plant plant;
    private final Island island;

    public Location(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
        this.plant = new Plant();
    }

    public boolean canAddAnimal(Class<? extends Animal> animalClass) {
        long count = animals.stream()
                .filter(a -> a.getClass() == animalClass)
                .count();
        return count < getMaxForClass(animalClass);
    }

    private int getMaxForClass(Class<? extends Animal> animalClass) {
        try {
            return animalClass.getDeclaredField("maxInCell").getInt(null);
        } catch (Exception e) {
            return Config.DEFAULT_MAX_ANIMALS;
        }
    }

    public void addAnimal(Animal animal) {
        if (canAddAnimal(animal.getClass())) {
            animals.add(animal);
            animal.setLocation(this);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
    
    public void removeUnit(Unit unit) {
        if (unit instanceof Animal) {
            removeAnimal((Animal) unit);
        }
    }

    public void moveAnimal(Animal animal, int dx, int dy) {
        int newX = Math.max(0, Math.min(x + dx, Config.WIDTH - 1));
        int newY = Math.max(0, Math.min(y + dy, Config.HEIGHT - 1));

        Location newLocation = island.getLocation(newX, newY);
        if (newLocation != null && newLocation.canAddAnimal(animal.getClass())) {
            removeAnimal(animal);
            newLocation.addAnimal(animal);
        }
    }

    public List<Animal> getAnimals() {
        return new CopyOnWriteArrayList<>(animals);
    }

    public Plant getPlant() {
        return plant;
    }
    
    public Island getIsland() {
        return island;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}