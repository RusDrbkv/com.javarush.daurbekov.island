package simulation;

/**
 * Базовый класс для всех сущностей на острове
 */
public abstract class Unit {
    protected Location location;
    protected boolean isAlive = true;
    
    public Unit() {
        this.isAlive = true;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public void die() {
        isAlive = false;
        if (location != null) {
            location.removeUnit(this);
        }
    }
}
