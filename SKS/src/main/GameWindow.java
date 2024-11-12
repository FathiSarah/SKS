package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * This class is responsible for the creating the game window, the frame that contains the game panel.
 * It also listens to window focus events and act accordingly if the user switches to another window.
 */
public class GameWindow {
     private JFrame jframe;

    /**
    * Constructor for the GameWindow class.
    * @param gamePanel The game panel that will be added to the frame.
    */
     public GameWindow(GamePanel gamePanel) {
         jframe = new JFrame();
         jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         jframe.add(gamePanel);
         jframe.setLocationRelativeTo(null);
         jframe.setResizable(false);
         jframe.pack();
         jframe.setVisible(true);

         jframe.addWindowFocusListener(new WindowFocusListener() {
             @Override
             public void windowGainedFocus(WindowEvent e) {

             }

             @Override
             public void windowLostFocus(WindowEvent e) {
                 gamePanel.getGame().WindowFocusLost();
             }
         });
     }
}
