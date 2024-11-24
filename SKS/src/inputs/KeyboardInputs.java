package inputs;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.GameState;
import main.GamePanel;
import entities.Entity;
import main.GameWindow;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
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
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
        }
        if (e.getKeyCode() == KeyEvent.VK_G){
            gamePanel.getGame().getPlaying().getPlayer().setHidden(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
        }
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

}