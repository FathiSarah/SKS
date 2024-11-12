package utils;

import main.Game;

import java.awt.image.BufferedImage;

public class Methods {

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

        return red == 174 && green == 50 && blue == 34;

    }
}
