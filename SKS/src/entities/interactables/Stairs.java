package entities.interactables;

import java.awt.*;

public class Stairs {

    private float minX, maxX, minY, maxY;
    private float targetX, targetY;

    public Stairs(float minX, float maxX, float minY, float maxY, float targetX, float targetY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public boolean isWithinBounds(float x, float y, float width, float height) {
        return x < maxX && x + width > minX && y < maxY && y + height > minY;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public float getMinX() {
        return minX;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxY() {
        return maxY;
    }


}
