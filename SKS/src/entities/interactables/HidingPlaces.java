package entities.interactables;

import main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static utils.LoadSave.*;

public class HidingPlaces extends Items {

    public HidingPlaces (float x, float y, float width, float height, String name){
        super(x, y, width, height, name);
        initHitBox(x, y, width * Game.SCALE, height * Game.SCALE);
    }

    public void render(Graphics g, String name) {
        BufferedImage hidingPlaceImage = loadImage(name);
        g.drawImage(hidingPlaceImage, (int) x, (int) y, (int) width, (int) height, null);
        drawHitBox(g);

    }

    public String getName() {
        return name;
    }


}
