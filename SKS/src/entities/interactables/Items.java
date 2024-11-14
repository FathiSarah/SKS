package entities.interactables;

import entities.Entity;
import entities.Player;

public class Items extends Entity{

    protected  float x, y;
    protected String name;
    protected boolean isPickedUp = false;

    public Items(float x, float y, float width, float height, String name) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.name = name;
    }

   public boolean equipWeapon() {
    return false;
    }

    public boolean removeWeapon(Player player) {
        if (player.killNPC()) {
            isPickedUp = false;
            return true;
        }
        return false;
    }


}
