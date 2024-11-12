package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean ePressed;

    public KeyboardInputs(GamePanel gamePanel) { // allow file to receive GamePanel
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_Z:
            upPressed = true;
            break;
            case KeyEvent.VK_Q:
            leftPressed = true;
            break;
            case KeyEvent.VK_S:
            downPressed = true;
            break;
            case KeyEvent.VK_D:
            rightPressed = true;
            break;
            case KeyEvent.VK_E:
            ePressed = true;
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_Z:
            upPressed = false;
            break;
            case KeyEvent.VK_Q:
            leftPressed = false;
            break;
            case KeyEvent.VK_S:
            downPressed = false;
            break;
            case KeyEvent.VK_D:
            rightPressed = false;
            break;
            case KeyEvent.VK_E:
            ePressed = false;
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    

    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isEPressed() {
        return ePressed;
    }

}
