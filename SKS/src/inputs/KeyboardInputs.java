package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;



    public KeyboardInputs(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            gamePanel.getGame().getPlayer().setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            gamePanel.getGame().getPlayer().setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.getGame().getPlayer().setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.getGame().getPlayer().setRight(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePanel.getGame().getPlayer().setAttacking(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            gamePanel.getGame().getPlayer().setJumping(true);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_SHIFT) {
            gamePanel.getGame().getPlayer().setRunning(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            gamePanel.getGame().getPlayer().setEquip(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            gamePanel.getGame().getPlayer().setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q){
            gamePanel.getGame().getPlayer().setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.getGame().getPlayer().setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.getGame().getPlayer().setRight(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePanel.getGame().getPlayer().setAttacking(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            gamePanel.getGame().getPlayer().setJumping(false);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_E) {
            gamePanel.getGame().getPlayer().setEquip(false);
        }
    }
}