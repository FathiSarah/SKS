package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.LoadSave;

import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;


class LoadSaveTest {
    @Test
    void testLoadImage_Success() {
        BufferedImage image = LoadSave.loadImage(LoadSave.CHARACTER_ATLAS);

        assertNotNull(image, "Image should be loaded successfully.");
    }

    @Test
    void testLoadImage_FileNotFound() {
        BufferedImage image = LoadSave.loadImage("non_existent_image.png");

        assertNull(image, "Image should be null when the file is not found.");
    }

    @Test
    void testLoadImage_InvalidPath() {
        BufferedImage image = LoadSave.loadImage("invalid_path/bed.png");

        assertNull(image, "Image should be null when the path is invalid.");
    }

}
