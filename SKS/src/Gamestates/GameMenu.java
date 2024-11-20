package Gamestates;

import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class GameMenu extends State implements Statemethods{
    public GameMenu(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawString("MENU", Game.GAME_WIDTH / 2, 200);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            System.out.println("ENTER");
            Gamestate.state = Gamestate.PLAYING;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
