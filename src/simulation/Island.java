package simulation;

import simulation.animals.Animal;
import simulation.animals.herbivores.*;
import simulation.animals.predators.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Island {
    private final Location[][] grid;
    private final ScheduledExecutorService executor;
    private final int width;
    private final int height;
    private final List<Class<? extends Animal>> allAnimalClasses = Arrays.asList(
            Wolf.class, Boa.class, Fox.class, Bear.class, Eagle.class,
            Horse.class, Deer.class, Rabbit.class, Mouse.class, Goat.class,
            Sheep.class, Boar.class, Buffalo.class, Duck.class, Caterpillar.class
    );

    private Map<Class<? extends Animal>, Long> previousAnimals = new HashMap<>();
    private long previousPlants = 0;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Location[width][height];
        this.executor = Executors.newScheduledThreadPool(3);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Location(x, y, this);
            }
        }
    }

    public void startSimulation() {
        executor.scheduleAtFixedRate(this::growPlants, 0,
                Config.PLANT_GROW_INTERVAL, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(this::processAnimals, 0,
                Config.SIMULATION_TICK, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(this::printStats, 0,
                Config.STATS_UPDATE, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void growPlants() {
        Arrays.stream(grid).flatMap(Arrays::stream).forEach(loc ->
                loc.getPlant().grow()
        );
    }

    private void processAnimals() {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(processors * 2);

        Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .forEach(loc -> loc.getAnimals().forEach(animal ->
                        pool.submit(() -> {
                            if (animal != null && animal.isAlive()) {
                                animal.run();
                            }
                        })
                ));

        pool.shutdown();
    }

    private void printStats() {
        Map<Class<? extends Animal>, Long> currentAnimals = Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .flatMap(loc -> loc.getAnimals().stream())
                .filter(Animal::isAlive)
                .collect(Collectors.groupingBy(
                        Animal::getClass,
                        Collectors.counting()
                ));

        long currentPlants = Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .mapToInt(loc -> loc.getPlant().getQuantity())
                .sum();

        System.out.println("\n=== Статистика острова ===");
        System.out.println("Животные:");

        // Формируем полный список животных
        Map<Class<? extends Animal>, Long> fullStats = new LinkedHashMap<>();
        for (Class<? extends Animal> clazz : allAnimalClasses) {
            fullStats.put(clazz, currentAnimals.getOrDefault(clazz, 0L));
        }

        // Выводим статистику
        fullStats.forEach((clazz, count) -> {
            String className = clazz.getSimpleName();
            String diff = "";

            if (previousAnimals.containsKey(clazz)) {
                long prevCount = previousAnimals.get(clazz);
                long difference = count - prevCount;
                if (difference != 0) {
                    diff = String.format(" (%+d)", difference);
                }
            }

            System.out.printf("  %-12s: %-4d%s%n", className, count, diff);
        });

        // Статистика растений
        String plantDiff = previousPlants != 0 ?
                String.format(" (%+d)", currentPlants - previousPlants) : "";
        System.out.printf("Растения: %d%s%n", currentPlants, plantDiff);
        System.out.println("========================");

        // Сохраняем текущее состояние
        previousAnimals = new HashMap<>(fullStats);
        previousPlants = currentPlants;
    }

    public void addAnimal(Animal animal, int x, int y) {
        if (animal == null) return;
        if (x >= 0 && x < width && y >= 0 && y < height) {
            Location location = grid[x][y];
            if (location.canAddAnimal(animal.getClass())) {
                location.addAnimal(animal);
                animal.setLocation(location);
            }
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }
}