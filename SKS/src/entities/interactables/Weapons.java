package entities.interactables;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Weapons class is a subclass of Items class. It is used to create weapons in the game.
 * Weapons are used by the player to attack the enemies in the game.
 */
public class Weapons extends Items {
    
    protected Rectangle attackHitbox;
    private boolean isAttacking = false;

    /**
     * Constructor for Weapons class
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public Weapons(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, width, height);
    }

    /**
     * render method is used to render the weapon on the screen
     * @param g
     */
    public void render(Graphics g) {
    }


    public void setResetAttack() {
        isAttacking = false;
        attackHitbox = null;
    }

    public boolean getAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

}