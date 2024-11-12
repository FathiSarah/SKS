package weapons;

import entities.Player;

public class Weapon{

    private int x, y;
    private String name;
    private boolean isEquiped = false;

    public Weapon(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public boolean equipWeapon() {
        if ()
    }

    public boolean removeWeapon(Player player) {
        if (player.killNPC()) {
            isEquiped = false;
            return true;
        }
        return false;
    }
}
