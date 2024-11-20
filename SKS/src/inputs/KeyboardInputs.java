package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gamestates.Gamestate;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;



    public KeyboardInputs(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
            gamePanel.getGame().getPlaying().getPlayer().setAction(true);
        }
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
            gamePanel.getGame().getPlaying().getPlayer().setEquip(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
        }
        if (e.getKeyCode() == KeyEvent.VK_G){
            gamePanel.getGame().getPlayer().setHidden(true);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
        }
        if (e.getKeyCode() == KeyEvent.VK_G){
            gamePanel.getGame().getPlayer().setHidden(false);
        }

    }
}