package levels;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import static utils.LoadSave.*;

public class LevelManager {

    private Game game;
    private BufferedImage collisionMap;

    public LevelManager(Game game) {
        this.game = game;
        collisionMap = loadImage(LEVEL_ONE_HITBOX);
    }

    public void draw(Graphics g, String level){
        BufferedImage lvlToDraw = loadImage(level);

        g.drawImage(lvlToDraw, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    }

    public BufferedImage getCollisionMap() {
        return collisionMap;
    }
}
