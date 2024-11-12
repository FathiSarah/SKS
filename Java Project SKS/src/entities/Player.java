package entities;

import inputs.KeyboardInputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class Player extends Entity {
    
    GamePanel gamePanel;
    KeyboardInputs keyboardInputs;

    private boolean weaponInInventory = false;
    private boolean keyInInventory = false;

    public Player(GamePanel gamePanel, KeyboardInputs keyboardInputs) {
        super(100, 100, 50, 100);
        this.gamePanel = gamePanel;
        this.keyboardInputs = keyboardInputs;
    }

    public void getWeapon(KeyEvent e) {
        e.getKeyCode();
        if (keyboardInputs.isEPressed()) {
            weaponInInventory = true;
        }
    }

    public void getKey(KeyEvent e) {
        e.getKeyCode();
        if (keyboardInputs.isEPressed()) {
            keyInInventory = true;
        }
    }

    public boolean killNPC() {
        if (weaponInInventory) {
            System.out.println("Player killed NPC");
            // removeWeapon();
            return true;
        }
        return false;
    }
}
