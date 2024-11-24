package entities.interactables;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import main.Game;
import static utils.LoadSave.loadImage;

public class Key extends Items {

    private Clip keyPickUpSound;

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