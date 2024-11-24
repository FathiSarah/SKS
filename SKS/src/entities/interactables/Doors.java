package entities.interactables;

import main.Game;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import static utils.LoadSave.loadImage;

/**
 * Doors class is a subclass of Items class. It is used
 * to create doors in the game. Doors need to be opened by a key in order to pass through them.
 */
public class Doors extends Items {

    /**
     * Constructor for Doors class
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public Doors(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, 50 * Game.SCALE, 15* Game.SCALE);
    }

    /**
     * render method is used to render the door on the screen
     * @param g
     */
    public void render(Graphics g) {
            BufferedImage doorImage = loadImage(name);
            g.drawImage(doorImage, (int) x, (int) y, (int) width, (int) height, null);
            drawHitBox(g);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
}
