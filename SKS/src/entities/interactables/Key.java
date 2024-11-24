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
 * Key class is a subclass of Items class. It is used to create keys in the game.
 * Keys are used by the player to unlock doors in the game.
 */
public class Key extends Items {

    private Clip keyPickUpSound;

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

    /**
     * keyPickUpSound method is used to play the sound when the player picks up the key
     */
    public void keyPickUpSound() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./res/mixkit-mechanical-crate-pick-up-3154.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            keyPickUpSound = AudioSystem.getClip();
            keyPickUpSound.open(audioStream);
            keyPickUpSound.start();
        }
        catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
}