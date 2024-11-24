package utils;

import entities.Player;
import entities.interactables.Key;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import main.Game;

/**
 * This class contains methods that are used in multiple classes.
 */
public class Methods {

    private static Player player;

    /**
     * Method used to check if an entity can move to a certain position.
     * @param x x-coordinate of the entity.
     * @param y y-coordinate of the entity.
     * @param width width of the entity.
     * @param height height of the entity.
     * @param CollisionMap The collision map of the level.
     * @return True if the entity can move to the position, false otherwise.
     */
    public static boolean canMoveHere(float x, float y, float width, float height, BufferedImage CollisionMap) {
        float scaledX = x / Game.SCALE;
        float scaledY = y / Game.SCALE;
        int scaledWidth = (int) (width / Game.SCALE);
        int scaledHeight = (int) (height / Game.SCALE);

        if (!isSolid(scaledX, scaledY, CollisionMap) && !isSolid(scaledX + scaledWidth, scaledY, CollisionMap) && !isSolid(scaledX, scaledY + scaledHeight, CollisionMap) && !isSolid(scaledX + scaledWidth, scaledY + scaledHeight, CollisionMap)) {
            return true;
        }
        return false;
    }

    /**
     * Method used to check if a certain position is solid (i.e. if the pixel is of a certain color, telling us it's a wall or a floor)
     * @param x x-coordinate of the position.
     * @param y y-coordinate of the position.
     * @param CollisionMap The collision map of the level.
     * @return True if the position is solid, false otherwise.
     */
    private static boolean isSolid(float x, float y,  BufferedImage CollisionMap) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }

        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        int pixelX = (int) x;
        int pixelY = (int) y;

        int pixelColor = CollisionMap.getRGB(pixelX, pixelY);

        int red = (pixelColor >> 16) & 0xff;
        int green = (pixelColor >> 8) & 0xff;
        int blue = (pixelColor) & 0xff;

        if (red == 174 && green == 50 && blue == 34) {
            return true;
        }
        else if (red == 0 && green == 255 && blue == 25 && !player.getKeyIsPickedUp()) {
            return true;
        }
        return false;
    }

    // singleton pattern
    public static void setPlayer(Player playerInstance) {
        player = playerInstance;
    }

    /**
     * Method used to check if an entity is on the floor or in the air.
     * @param hitBox The hitbox of the entity.
     * @param CollisionMap The collision map of the level.
     * @return True if the entity is on the floor, false otherwise.
     */
    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, BufferedImage CollisionMap) {
        float scaledX = hitBox.x / Game.SCALE;
        float scaledY = hitBox.y / Game.SCALE;
        int scaledWidth = (int) (hitBox.width / Game.SCALE);
        int scaledHeight = (int) (hitBox.height / Game.SCALE);

        if (!isSolid(scaledX, scaledY + scaledHeight + 1, CollisionMap) && !isSolid(scaledX + scaledWidth, scaledY + scaledHeight + 1, CollisionMap)) {
            return false;
        }
        return true;
    }

    /**
     * Method used to check if an entity is on the same level as the player.
     * @param npcHitBox
     * @param charHitBox
     * @return
     */
    public static boolean isOnSameLevel(Rectangle2D.Float npcHitBox, Rectangle2D.Float charHitBox) {

        float playerBottom = charHitBox.y + charHitBox.height;
        float npcBottom = npcHitBox.y + npcHitBox.height;

        return (playerBottom == npcBottom);
    }
}
