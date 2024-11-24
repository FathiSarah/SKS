package entities.interactables;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static utils.LoadSave.loadImage;

/**
 * Key class is a subclass of Items class. It is used to create keys in the game.
 * Keys are used by the player to unlock doors in the game.
 */
public class Key extends Items {

    /**
     * Constructor for Key class
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public Key(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, 50 * Game.SCALE, 15* Game.SCALE);
    }

    /**
     * render method is used to render the key on the screen
     * @param g
     */
    public void render(Graphics g) {
        if (!isPickedUp) {
            BufferedImage keyImage = loadImage("key.png");
            g.drawImage(keyImage, (int) x, (int) y, (int) width, (int) height, null);
            drawHitBox(g);
        }
    }
}