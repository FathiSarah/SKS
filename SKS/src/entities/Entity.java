package entities;


import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y, width, height;
    protected Rectangle2D.Float hitBox;
    protected int speed;

    protected boolean isAlive = true;
    protected boolean isMoving = false;

    protected Player player;


    public Entity(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitBox(Graphics g) {
        // for debugging the hitBox
        g.setColor(Color.RED);
        g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
    }

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

    public boolean Alive() {
        return isAlive;
    }

    public boolean Moving() {
        return isMoving;
    }
}
