package simulation;

/**
 * Базовый класс для всех живых существ (животные и растения)
 */
public abstract class Creature extends Unit {
    protected double weight;
    
    public Creature(double weight) {
        super();
        this.weight = weight;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public abstract void eat(Creature food);
}
