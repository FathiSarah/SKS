package inputs;

import Gamestates.Gamestate;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    public MouseInputs() {
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        switch(Gamestate.state) {
            case MENU:
                break;
            case PLAYING:
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
    }
}
