package entities.interactables;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Weapons extends Items {
    
    protected Rectangle attackHitbox;
    private boolean isAttacking = false;

    public Weapons(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, width, height);
    }

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