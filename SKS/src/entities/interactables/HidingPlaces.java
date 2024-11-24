package entities.interactables;

import main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static utils.LoadSave.*;

/**
 * HidingPlaces class is a subclass of Items class. It is used to create hiding places in the game.
 * Hiding places are used by the player to hide from the enemies.
 */
public class HidingPlaces extends Items {

    /**
     * Constructor for HidingPlaces class
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public HidingPlaces (float x, float y, float width, float height, String name){
        super(x, y, width, height, name);
        initHitBox(x, y, width * Game.SCALE, height * Game.SCALE);
    }

    /**
     * render method is used to render the hiding place on the screen
     * @param g
     * @param name
     */
    public void render(Graphics g, String name) {
        BufferedImage hidingPlaceImage = loadImage(name);
        g.drawImage(hidingPlaceImage, (int) x, (int) y, (int) width, (int) height, null);
        drawHitBox(g);

    }

    public String getName() {
        return name;
    }


}
