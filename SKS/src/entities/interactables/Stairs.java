package entities.interactables;

/**
 * Stairs class is a subclass of Entity class. It is used to create stairs in the game.
 * Stairs are used by the player to move between floors in the game.
 */
public class Stairs {

    private float minX, maxX, minY, maxY;
    private float targetX, targetY;

    /**
     * Constructor for Stairs class
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @param targetX
     * @param targetY
     */
    public Stairs(float minX, float maxX, float minY, float maxY, float targetX, float targetY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    /**
     * isWithinBounds method is used to check if the player is within the bounds of the stairs
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public boolean isWithinBounds(float x, float y, float width, float height) {
        return x < maxX && x + width > minX && y < maxY && y + height > minY;
    }

    public float getTargetX() {
        return targetX;
    }
    public float getTargetY() {
        return targetY;
    }
}
