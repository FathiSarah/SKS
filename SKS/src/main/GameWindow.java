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
            jframe.setVisible(false); // Hide frame to apply changes
            jframe.dispose(); // Dispose frame to apply changes
            jframe.setUndecorated(true); // Remove decorations for fullscreen
            gd.setFullScreenWindow(jframe); // Set the frame to fullscreen mode
        } else {
            jframe.setVisible(false); // Hide frame to apply changes
            jframe.dispose(); // Dispose frame to apply changes
            gd.setFullScreenWindow(null); // Exit fullscreen mode
            jframe.setUndecorated(false); // Restore decorations
            jframe.pack(); // Adjust size
            jframe.setLocationRelativeTo(null); // Center window
        }
        jframe.setVisible(true); // Show the frame
        jframe.requestFocusInWindow(); // Ensure the JFrame has focus
    }



    public boolean isFullscreen() {
        return fullscreen;
    }



}
