import entities.Player;
import entities.interactables.Knife;
import levels.LevelManager;
import main.Game;
import org.junit.jupiter.api.Test;
import utils.Methods;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testCanMoveHere() {
        BufferedImage collisionMap = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        collisionMap.setRGB(50, 50, (174 << 16) | (50 << 8) | 34); // Solid pixel

        assertTrue(Methods.canMoveHere(10, 10, 10, 10, collisionMap), "Should allow movement in non-solid area.");
        assertFalse(Methods.canMoveHere(50, 50, 10, 10, collisionMap), "Should block movement in solid area.");
        assertFalse(Methods.canMoveHere(-10, 10, 10, 10, collisionMap), "Should block movement out of bounds.");
    }

}

