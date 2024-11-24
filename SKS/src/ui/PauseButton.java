package ui;

import java.awt.*;

public class PauseButton {

    protected int x, y, width, height;
    private Rectangle bounds;

    public PauseButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds ();
    }
    private void createBounds() {
        bounds = new Rectangle (x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
