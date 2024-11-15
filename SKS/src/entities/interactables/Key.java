package entities.interactables;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static utils.LoadSave.loadImage;

public class Key extends Items {

    public Key(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, 50 * Game.SCALE, 15* Game.SCALE);
    }

    public void render(Graphics g) {
        if (!isPickedUp) {
            BufferedImage keyImage = loadImage("key.png");
            g.drawImage(keyImage, (int) x, (int) y, (int) width, (int) height, null);
            drawHitBox(g);
        }
    }
}