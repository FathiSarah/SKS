package entities.interactables;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static utils.LoadSave.loadImage;

public class Knife extends Items {
    
    public Knife(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, 75 * Game.SCALE, 40* Game.SCALE);
    }

    public void render(Graphics g) {
        if (!isPickedUp) {
            BufferedImage knifeImage = loadImage("png-transparent-drawing-black-pixel-art-sprite-online-and-offline-throwing-knife-angle-diagram.png");
            g.drawImage(knifeImage, (int) x, (int) y, (int) width, (int) height, null);
            drawHitBox(g);
        }
    }
}