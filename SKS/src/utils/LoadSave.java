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
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";




    public static final String LEVEL_ONE_HITBOX = "level1_hitBox.png";
    public static final String ENEMY1_ATLAS = "Enemy1Atlas.png";
    public static final String ENEMY2_ATLAS = "Enemy2Atlas.png";
    public static final String ENEMY3_ATLAS = "Enemy3Atlas.png";

    public static final String WARDROBE = "wardrobe.png";
    public static final String BED = "bed.png";
    public static final String FRIDGE = "fridge.png";
    public static final String COUCH = "couch.png";
    public static final String OPENED_DOOR = "open_door.png";
    public static final String CLOSED_DOOR= "closed_door.png";

    private LevelBase activeLevel;

    /**
     * Load an image from the resources' folder.
     * @param path The path to the image.
     * @return The image.
     */
    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        InputStream is = null;

        try {
            is = LoadSave.class.getResourceAsStream("../res/" + path);
            if (is == null) {
                System.err.println("Resource not found: " + path);
                return null;
            }
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return image;
    }



}
