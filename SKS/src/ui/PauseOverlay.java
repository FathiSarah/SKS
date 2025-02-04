package ui;

import gamestates.GameState;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.URMButtons.*;

public class PauseOverlay {

    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;

    public PauseOverlay (Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButton();
        createUrmButtons();
    }
    private void createUrmButtons(){
        int menuX = (int) (500 * Game.SCALE);
        int replayX = (int) (600 * Game.SCALE);
        int unpauseX = (int) (700 * Game.SCALE);
        int bY = (int) (600* Game.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE * 2, URM_SIZE * 2, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE * 2, URM_SIZE * 2, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE * 2, URM_SIZE * 2, 0);

    }

    private void createSoundButton() {
        int soundX = (int) (700 * Game.SCALE);
        int musicY = (int) (270 * Game.SCALE);
        int sfxY = (int) (370 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE * 2, SOUND_SIZE * 2);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE * 2, SOUND_SIZE * 2);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.loadImage(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE * 2);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE * 2);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = Game.GAME_HEIGHT / 2 - bgH / 2;
    }
    public  void update() {
        musicButton.update();
        sfxButton.update();

        menuB.update();
        replayB.update();
        unpauseB.update();
    }

    public void draw(Graphics g) {
        //background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        // sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        //UrmButtons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());

        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        } else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                GameState.state = GameState.MENU;
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed())
                System.out.println("replay lvl!");
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed())
                playing.unpauseGame();
        }

        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();


    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if (isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMouseOver(true);
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return (b.getBounds().contains(e.getX(), e.getY()));
    }
}
