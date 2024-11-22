package Gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Menu implements Statemethods{

    private final int buttonWidth = 200;
    private final int buttonHeight = 50;

    private final int buttonPlayX = 400;
    private final int buttonPlayY = 100;

    private final int buttonSettingsX = 400;
    private final int buttonSettingsY = 300;

    private final int buttonExitX = 400;
    private final int buttonExitY = 500;

    private Clip menuMusic;
    private Gamestate gamestate;

    public Menu() {
        update();
    }

    @Override
    public void update() {
        if (gamestate.state == Gamestate.state.MENU) {
        stopMusic(); 
        playMusic();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(buttonPlayX, buttonPlayY, buttonWidth, buttonHeight);
        g.drawString("Start Game", buttonPlayX + (buttonWidth / 2), buttonPlayY + (buttonHeight / 2));
        g.drawRect(buttonSettingsX, buttonSettingsY, buttonWidth, buttonHeight);
        g.drawString("Settings", buttonSettingsX + (buttonWidth / 2), buttonSettingsY + (buttonHeight / 2));
        g.drawRect(buttonExitX, buttonExitY, buttonWidth, buttonHeight);
        g.drawString("Exit", buttonExitX + (buttonWidth / 2), buttonExitY + (buttonHeight / 2));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= buttonPlayX && e.getX() <= buttonPlayX + buttonWidth &&
        e.getY() >= buttonPlayY && e.getY() <= buttonPlayY + buttonHeight) {
            System.out.println("Play button clicked");
            Gamestate.state = Gamestate.state.PLAYING;
        }

        if (e.getX() >= buttonSettingsX && e.getX() <= buttonSettingsX + buttonWidth && e.getY() >= buttonSettingsY && e.getY() <= buttonSettingsY + buttonHeight) {
            Gamestate.state = Gamestate.state.SETTINGS;
        }

        if (e.getX() >= buttonExitX && e.getX() <= buttonExitX + buttonWidth && e.getY() >= buttonExitY && e.getY() <= buttonExitY + buttonHeight) {
            System.exit(0);
        }
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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void playMusic() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./res/Free-Dramatic-Dramatic-Game-or-Menu-Music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            menuMusic = AudioSystem.getClip();
            menuMusic.open(audioStream);
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch (Exception e) {
                System.out.println(e.getMessage());
        }
    }
    public void stopMusic() {
        if (menuMusic != null && menuMusic.isRunning()) {
            menuMusic.stop();
        }
    }
}
