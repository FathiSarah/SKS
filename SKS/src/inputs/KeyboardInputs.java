package inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;
import entities.Entity;
import main.GameWindow;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private Entity entity;
    private GameWindow gameWindow;



    public KeyboardInputs(GamePanel gamePanel, GameWindow gameWindow) {
        this.gamePanel = gamePanel;
        this.gameWindow = gameWindow;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            gamePanel.getGame().getPlaying().getPlayer().setAction(true);
        }
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            gamePanel.getGame().getPlaying().getPlayer().setEquip(true);
        }
        if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
            Entity.debug = !Entity.debug;
        }
        if(e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {
            gameWindow.setFullscreen(!gameWindow.isFullscreen());
            System.out.println("Fullscreen");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            gamePanel.getGame().getPlaying().getPlayer().setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            gamePanel.getGame().getPlaying().getPlayer().setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.getGame().getPlaying().getPlayer().setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.getGame().getPlaying().getPlayer().setRight(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePanel.getGame().getPlaying().getPlayer().setAttacking(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            gamePanel.getGame().getPlaying().getPlayer().setJumping(true);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_SHIFT) {
            gamePanel.getGame().getPlaying().getPlayer().setRunning(true);
        }




    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            gamePanel.getGame().getPlaying().getPlayer().setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_Q){
            gamePanel.getGame().getPlaying().getPlayer().setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            gamePanel.getGame().getPlaying().getPlayer().setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gamePanel.getGame().getPlaying().getPlayer().setRight(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePanel.getGame().getPlaying().getPlayer().setAttacking(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            gamePanel.getGame().getPlaying().getPlayer().setJumping(false);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_SHIFT) {
            gamePanel.getGame().getPlaying().getPlayer().setRunning(false);
        }
        if (e.getKeyCode() ==  KeyEvent.VK_E) {
            gamePanel.getGame().getPlaying().getPlayer().setEquip(false);
        }

    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

}