package simulation.animals;

import simulation.*;
import java.util.Random;

public abstract class Animal extends Creature implements Runnable {
    protected double satiety;
    protected double maxSatiety;
    protected int speed;
    protected int maxInCell;
    protected static final Random random = new Random();

    public Animal(double weight, double maxSatiety, int speed, int maxInCell) {
        super(weight);
        this.maxSatiety = maxSatiety;
        this.satiety = maxSatiety * Config.ADULT_START_SATIETY; // Используем настраиваемую сытость
        this.speed = speed;
        this.maxInCell = maxInCell;
    }
    
    // Конструктор для детенышей с меньшей сытостью
    public Animal(double weight, double maxSatiety, int speed, int maxInCell, boolean isBaby) {
        super(weight);
        this.maxSatiety = maxSatiety;
        this.satiety = isBaby ? maxSatiety * Config.BABY_START_SATIETY : maxSatiety * Config.ADULT_START_SATIETY;
        this.speed = speed;
        this.maxInCell = maxInCell;
    }

    protected void decreaseSatiety() {
        satiety -= weight * Config.HUNGER_DECREASE_RATE;
        if (satiety <= 0) die();
    }

    public abstract void eat();
    public abstract void reproduce();
    public abstract void move();
    
    // Базовая логика размножения с проверками
    protected boolean canReproduce() {
        if (location == null) return false;
        
        return isAlive &&
               satiety >= maxSatiety * Config.MIN_SATIETY_FOR_REPRODUCTION &&
               random.nextDouble() < Config.REPRODUCTION_CHANCE;
    }

    @Override
    public void die() {
        super.die();
    }

    @Override
    public void eat(Creature food) {
        // Базовая реализация - переопределяется в подклассах
        if (food instanceof Plant) {
            Plant plant = (Plant) food;
            if (plant.getQuantity() > 0) {
                double probability = Config.getEatingProbability(this.getClass().getSimpleName(), "Plant");
                if (random.nextDouble() < probability) {
                    satiety = Math.min(satiety + plant.getNutritionValue(), maxSatiety);
                    plant.consume();
                }
            }
        }
    }

    public void move(Direction direction) {
        if (location != null) {
            location.moveAnimal(this, direction.getDeltaX(), direction.getDeltaY());
        }
    }

    @Override
    public void run() {
        if (isAlive) {
            move();
            eat();
            reproduce();
            decreaseSatiety();
        }
    }

    public double getSatiety() {
        return satiety;
    }

    public double getMaxSatiety() {
        return maxSatiety;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxInCell() {
        return maxInCell;
    }
}