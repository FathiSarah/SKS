package levels;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.Game;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utils.LoadSave.*;

/**
 * Class used to manage levels in the game.
 */
public class LevelManager {

    private Game game;
    private BufferedImage collisionMap;


    public LevelManager(Game game) {
        this.game = game;
        collisionMap = loadImage(LEVEL_ONE_HITBOX);
    }

    /**
     * Method used to load a level image.
     * @param g
     * @param level
     */
    public void draw(Graphics g, String level){
        BufferedImage lvlToDraw = loadImage(level);

        g.drawImage(lvlToDraw, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    }

    public BufferedImage getCollisionMap() {
        return collisionMap;
    }

    public Game getGame() {
        return game;
    }
}