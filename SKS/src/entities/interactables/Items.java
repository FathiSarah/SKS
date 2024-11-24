package entities.interactables;

import entities.Entity;
import entities.Player;
import inputs.KeyboardInputs;

/**
 * Items class is a subclass of Entity class. It is used to create items in the game.
 * Items are used by the player to pick up and use in the game.
 */
public class Items extends Entity{

    protected float x, y;
    protected String name;
    public boolean isPickedUp = false;

    /**
     * Constructor for Items class
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public Items(float x, float y, float width, float height, String name) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public boolean checkPickUpItem() {
        if (!isPickedUp && getHitBox().intersects(player.getHitBox())) {
            System.out.println("Key picked up");
            isPickedUp = true;
            return true;
        }
        return false;
    }

    public boolean removeWeapon(Player player) {
        if (player.killNPC()) {
            isPickedUp = false;
            return true;
        }
        return false;
    }


    public boolean isPickedUp() {
        return isPickedUp;
    }
    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
}
