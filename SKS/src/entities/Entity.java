package entities;


import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Entity class that will be the base class for all entities in the game.
 */
public abstract class Entity {

    protected float x, y, width, height;
    protected Rectangle2D.Float hitBox;
    protected float speed;

    protected boolean isAlive = true;

    protected Player player;

    public static boolean debug = false;

    /**
     * Constructor for the Entity class.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //Used to temporarily draw the hitbox of the entity, won't keep it in the final version
    protected void drawHitBox(Graphics g) {
        // for debugging the hitBox
        if(debug){
            g.setColor(Color.RED);
            g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
        }

    }

    /**
     * Method that initializes the hitbox of the entity.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }


    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public boolean Alive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        alive = isAlive;
        }
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
