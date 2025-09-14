package simulation;

import simulation.animals.herbivores.*;
import simulation.animals.predators.*;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Island island = new Island(Config.WIDTH, Config.HEIGHT);
        
        initializeAnimals(island);

        island.startSimulation();
        TimeUnit.SECONDS.sleep(30); 
        island.shutdown();
    }

    private static void initializeAnimals(Island island) {
        for (int i = 0; i < 50; i++) { // Увеличили до 50
            island.addAnimal(new Rabbit(), randX(), randY());
            island.addAnimal(new Sheep(), randX(),randY());
            island.addAnimal(new Mouse(), randX(),randY());
            island.addAnimal(new Horse(), randX(),randY());
            island.addAnimal(new Goat(), randX(),randY());
            island.addAnimal(new Duck(), randX(),randY());
            island.addAnimal(new Deer(), randX(),randY());
            island.addAnimal(new Caterpillar(), randX(),randY());
            island.addAnimal(new Buffalo(), randX(),randY());
            island.addAnimal(new Boar(), randX(),randY());
            island.addAnimal(new Wolf(), randX(), randY());
            island.addAnimal(new Fox(), randX(),randY());
            island.addAnimal(new Eagle(), randX(),randY());
            island.addAnimal(new Boa(), randX(),randY());
            island.addAnimal(new Bear(), randX(),randY());
        }
    }

    private static int randX() {
        return (int) (Math.random() * Config.WIDTH);
    }

    private static int randY() {
        return (int) (Math.random() * Config.HEIGHT);
    }
}