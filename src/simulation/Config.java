package simulation;

import java.util.HashMap;
import java.util.Map;

public class Config {
    // Настройки острова
    public static int WIDTH = 100;
    public static int HEIGHT = 20;
    
    // Настройки времени
    public static int PLANT_GROW_INTERVAL = 10000;  // Увеличили с 5 до 10 секунд
    public static int SIMULATION_TICK = 2000;       // Увеличили с 1 до 2 секунд
    public static int STATS_UPDATE = 5000;          // Увеличили с 3 до 5 секунд
    
    // Настройки растений
    public static int MAX_PLANTS_IN_CELL = 50;
    public static double PLANT_NUTRITION = 0.8;
    
    // Настройки животных
    public static double HUNGER_DECREASE_RATE = 0.01; // Скорость голодания
    public static double ADULT_START_SATIETY = 0.8; // Сытость взрослых при старте (80%)
    public static double BABY_START_SATIETY = 0.5; // Сытость детенышей при рождении (50%)
    public static double REPRODUCTION_CHANCE = 0.1; // Вероятность размножения (10%)
    public static double MIN_SATIETY_FOR_REPRODUCTION = 0.5; // Минимальная сытость для размножения (50%)
    
    // Вероятности поедания (хищник -> жертва)
    public static final Map<String, Map<String, Double>> EATING_PROBABILITIES = new HashMap<>();
    
    static {
        // Инициализация вероятностей поедания
        initEatingProbabilities();
    }
    
    private static void initEatingProbabilities() {
        // Волк (согласно таблице)
        Map<String, Double> wolfPrey = new HashMap<>();
        wolfPrey.put("Horse", 0.10);
        wolfPrey.put("Deer", 0.15);
        wolfPrey.put("Rabbit", 0.60);
        wolfPrey.put("Mouse", 0.80);
        wolfPrey.put("Goat", 0.60);
        wolfPrey.put("Sheep", 0.70);
        wolfPrey.put("Boar", 0.15);
        wolfPrey.put("Buffalo", 0.10);
        wolfPrey.put("Duck", 0.40);
        EATING_PROBABILITIES.put("Wolf", wolfPrey);
        
        // Медведь (согласно таблице)
        Map<String, Double> bearPrey = new HashMap<>();
        bearPrey.put("Boa", 0.80);
        bearPrey.put("Horse", 0.40);
        bearPrey.put("Deer", 0.80);
        bearPrey.put("Rabbit", 0.80);
        bearPrey.put("Mouse", 0.90);
        bearPrey.put("Goat", 0.70);
        bearPrey.put("Sheep", 0.70);
        bearPrey.put("Boar", 0.50);
        bearPrey.put("Buffalo", 0.20);
        bearPrey.put("Duck", 0.10);
        EATING_PROBABILITIES.put("Bear", bearPrey);
        
        // Лиса (согласно таблице)
        Map<String, Double> foxPrey = new HashMap<>();
        foxPrey.put("Rabbit", 0.70);
        foxPrey.put("Mouse", 0.90);
        foxPrey.put("Duck", 0.60);
        foxPrey.put("Caterpillar", 0.40);
        EATING_PROBABILITIES.put("Fox", foxPrey);
        
        // Орел (согласно таблице)
        Map<String, Double> eaglePrey = new HashMap<>();
        eaglePrey.put("Fox", 0.10);
        eaglePrey.put("Rabbit", 0.90);
        eaglePrey.put("Mouse", 0.90);
        eaglePrey.put("Duck", 0.80);
        EATING_PROBABILITIES.put("Eagle", eaglePrey);
        
        // Удав (согласно таблице)
        Map<String, Double> boaPrey = new HashMap<>();
        boaPrey.put("Fox", 0.15);
        boaPrey.put("Rabbit", 0.20);
        boaPrey.put("Mouse", 0.40);
        boaPrey.put("Duck", 0.10);
        EATING_PROBABILITIES.put("Boa", boaPrey);
        
        // Травоядные едят растения
        Map<String, Double> herbivoreFood = new HashMap<>();
        herbivoreFood.put("Plant", 1.0);
        
        String[] herbivores = {"Rabbit", "Mouse", "Goat", "Sheep", "Boar", "Buffalo", "Duck", "Caterpillar", "Deer", "Horse"};
        for (String herbivore : herbivores) {
            EATING_PROBABILITIES.put(herbivore, new HashMap<>(herbivoreFood));
        }
    }
    
    // Методы для изменения настроек
    public static void setIslandSize(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }
    
    public static void setPlantSettings(int maxPlants, double nutrition) {
        MAX_PLANTS_IN_CELL = maxPlants;
        PLANT_NUTRITION = nutrition;
    }
    
    public static void setTimeSettings(int plantGrow, int simulationTick, int statsUpdate) {
        PLANT_GROW_INTERVAL = plantGrow;
        SIMULATION_TICK = simulationTick;
        STATS_UPDATE = statsUpdate;
    }
    
    public static void setHungerRate(double rate) {
        HUNGER_DECREASE_RATE = rate;
    }
    
    public static double getEatingProbability(String predator, String prey) {
        Map<String, Double> preyMap = EATING_PROBABILITIES.get(predator);
        if (preyMap != null) {
            return preyMap.getOrDefault(prey, 0.0);
        }
        return 0.0;
    }
    
    // Методы для настройки сытости
    public static void setAdultStartSatiety(double satiety) {
        ADULT_START_SATIETY = Math.max(0.1, Math.min(1.0, satiety)); // Ограничиваем от 10% до 100%
    }
    
    public static void setBabyStartSatiety(double satiety) {
        BABY_START_SATIETY = Math.max(0.1, Math.min(1.0, satiety)); // Ограничиваем от 10% до 100%
    }
}