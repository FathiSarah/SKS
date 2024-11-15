package entities.interactables;

import entities.Entity;
import entities.Player;
import inputs.KeyboardInputs;

public class Items extends Entity{

    protected float x, y;
    protected String name;
    public boolean isPickedUp = false;
    private KeyboardInputs keyboardInputs;
    private boolean equip;

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
}
