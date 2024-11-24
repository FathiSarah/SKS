package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * This class is responsible for the creating the game window, the frame that contains the game panel.
 * It also listens to window focus events and act accordingly if the user switches to another window.
 */
public class GameWindow {
     private JFrame jframe;
     private boolean fullscreen = false;

    /**
    * Constructor for the GameWindow class.
    * @param gamePanel The game panel that will be added to the frame.
    */
     public GameWindow(GamePanel gamePanel) {
         jframe = new JFrame();

         jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jframe.add(gamePanel);
         jframe.setResizable(true);
         jframe.setPreferredSize(new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT));
         jframe.pack();
         jframe.setLocationRelativeTo(null);
         jframe.setVisible(true);

         jframe.addWindowFocusListener(new WindowFocusListener() {
             @Override
             public void windowGainedFocus(WindowEvent e) {
                    gamePanel.requestFocusInWindow();
             }

             @Override
             public void windowLostFocus(WindowEvent e) {
                 gamePanel.getGame().WindowFocusLost();
             }
         });

         jframe.addComponentListener(new java.awt.event.ComponentAdapter() {
             @Override
             public void componentResized(java.awt.event.ComponentEvent e) {
                 gamePanel.setPanelSize(jframe.getWidth(), jframe.getHeight());
                 gamePanel.revalidate();
             }
         });




     }

    /**
     * Method used to set the fullscreen mode of the game window.
     * @param fullscreen
     */
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (fullscreen) {
            jframe.setVisible(false);
            jframe.dispose();
            jframe.setUndecorated(true);
            gd.setFullScreenWindow(jframe);
        } else {
            jframe.setVisible(false);
            jframe.dispose();
            gd.setFullScreenWindow(null);
            jframe.setUndecorated(false);
            jframe.pack();
            jframe.setLocationRelativeTo(null);
        }
        jframe.setVisible(true);
        jframe.requestFocusInWindow();
    }



    public boolean isFullscreen() {
        return fullscreen;
    }



}
