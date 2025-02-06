package simulation.animals;

import simulation.Location;
import java.util.Random;

public abstract class Animal implements Runnable {
    protected double weight;
    protected double satiety;
    protected double maxSatiety;
    protected int speed;
    protected int maxInCell;
    protected Location location;
    protected boolean isAlive = true;
    protected static final Random random = new Random();

    public Animal(double weight, double maxSatiety, int speed, int maxInCell) {
        this.weight = weight;
        this.maxSatiety = maxSatiety;
        this.satiety = maxSatiety * 0.5;
        this.speed = speed;
        this.maxInCell = maxInCell;
    }

    protected void checkHunger() {
        satiety -= weight * 0.02;
        if (satiety <= 0) die();
    }

    public abstract void eat();
    public abstract void reproduce();
    public abstract void move();

    public void die() {
        isAlive = false;
        if (location != null) {
            location.removeAnimal(this);
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void run() {
        if (isAlive) {
            move();
            eat();
            reproduce();
            checkHunger();
        }
    }

    public double getWeight() {
        return weight;
    }
}