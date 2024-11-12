package entities;

public abstract class Entity {
    protected float x, y;
    protected int width;
	protected int height;
    protected int speed;

    protected boolean isAlive = true;
    protected boolean isMoving = false;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean Alive() {
        return isAlive;
    }

    public boolean Moving() {
        return isMoving;
    }
}