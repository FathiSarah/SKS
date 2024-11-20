package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import levels.LevelBase;


/**
 * This class is responsible for loading and saving the resources we need (images, sounds, level data, etc.).
 */
public class LoadSave {

    public static final String CHARACTER_ATLAS = "CharAtlas.png";
    public static final String LEVEL_ONE = "level1.png";
    public static final String LEVEL_ONE_HITBOX = "level1_hitBox.png";
    public static final String ENEMY1_ATLAS = "Enemy1Atlas.png";
    public static final String ENEMY2_ATLAS = "Enemy2Atlas.png";
    public static final String ENEMY3_ATLAS = "Enemy3Atlas.png";
    public static final String WARDROBE = "wardrobe.png";

    private LevelBase activeLevel;

    /**
     * Load an image from the resources' folder.
     * @param path The path to the image.
     * @return The image.
     */
    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("../res/" + path);

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }


}
