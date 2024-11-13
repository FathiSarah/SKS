package items;

import entities.Player;

public class Items{

    private float x, y;
    private String name;
    protected boolean isPickedUp = false;

    public Items(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

//    public boolean equipWeapon() {
//        if ()
//    }

    public boolean removeWeapon(Player player) {
        if (player.killNPC()) {
            isPickedUp = false;
            return true;
        }
        return false;
    }
}
