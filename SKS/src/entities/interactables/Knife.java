package entities.interactables;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import main.Game;
import static utils.LoadSave.loadImage;

/**
 * Knife class is a subclass of Weapons class. It is used to create knives in the game.
 * Knives are used by the player to kill the enemies in the game.
 */
public class Knife extends Weapons {
    private Clip knifeEquipSound;

    /**
     * Constructor for Knife class
     * @param x
     * @param y
     * @param width
     * @param height
     * @param name
     */
    public Knife(float x, float y, int width, int height, String name) {
        super(x, y, width, height, name);
        initHitBox(x, y, 75 * Game.SCALE, 40* Game.SCALE);
    }

    /**
     * render method is used to render the knife on the screen
     * @param g
     */
    public void render(Graphics g) {
        if (!isPickedUp) {
            BufferedImage knifeImage = loadImage("png-transparent-drawing-black-pixel-art-sprite-online-and-offline-throwing-knife-angle-diagram.png");
            g.drawImage(knifeImage, (int) x, (int) y, (int) width, (int) height, null);
            drawHitBox(g);
        }
    }

    /**
     * knifeEquipSound method is used to play the sound when the player equips the knife
     */
    public void knifeEquipSound() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./res/knife-draw-48223.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            knifeEquipSound = AudioSystem.getClip();
            knifeEquipSound.open(audioStream);
            knifeEquipSound.start();
        }
        catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
}