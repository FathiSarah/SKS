package tests;

import org.junit.jupiter.api.Test;
import utils.Methods;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class TestsMethods {

    @Test
    void testCanMoveHere() {
        BufferedImage collisionMap = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        collisionMap.setRGB(50, 50, (174 << 16) | (50 << 8) | 34); // Solid pixel

        assertTrue(Methods.canMoveHere(10, 10, 10, 10, collisionMap), "should be able to move here since it's not the defined collision color");
        assertFalse(Methods.canMoveHere(50, 50, 10, 10, collisionMap), "shouldn't be able to move here since it's the defined collision color");
        assertFalse(Methods.canMoveHere(-10, 10, 10, 10, collisionMap), "Should block movement since we are out of bounds");
    }


    @Test
    void testIsEntityOnFloor() {
        BufferedImage collisionMap = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        collisionMap.setRGB(30, 52, (174 << 16) | (50 << 8) | 34); // Solid floor pixel

        Rectangle2D.Float hitBox = new Rectangle2D.Float(30, 41, 10, 10);

        assertTrue(Methods.isEntityOnFloor(hitBox, collisionMap), "Entity should be on the floor.");
        hitBox.y = 10;
        assertFalse(Methods.isEntityOnFloor(hitBox, collisionMap), "Entity should not be on the floor.");
    }


    @Test
    void testIsOnSameLevel() {
        Rectangle2D.Float npcHitBox = new Rectangle2D.Float(10, 20, 10, 30);
        Rectangle2D.Float charHitBox = new Rectangle2D.Float(15, 30, 10, 20);

        assertTrue(Methods.isOnSameLevel(npcHitBox, charHitBox), "Hitboxes should be on the same y + height value");
        charHitBox.y = 55; // y from 55 to 65
        assertFalse(Methods.isOnSameLevel(npcHitBox, charHitBox), "Hitboxes should not be on the same level vertically");
    }



}