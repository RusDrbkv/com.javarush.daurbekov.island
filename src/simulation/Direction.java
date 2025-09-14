package simulation;

/**
 * Направления движения животных
 */
public enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTHEAST(1, -1),
    NORTHWEST(-1, -1),
    SOUTHEAST(1, 1),
    SOUTHWEST(-1, 1);
    
    private final int deltaX;
    private final int deltaY;
    
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    
    public int getDeltaX() {
        return deltaX;
    }
    
    public int getDeltaY() {
        return deltaY;
    }
    
    public static Direction getRandomDirection() {
        Direction[] directions = values();
        return directions[(int) (Math.random() * directions.length)];
    }
}
