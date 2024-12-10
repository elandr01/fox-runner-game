package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {



    private GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel){

        //Set Fixed Size
        this.setResizable(false);

        //Set Default Settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fox Runner");
        ImageIcon icon = new ImageIcon("res/textures/Background.png");
        this.setIconImage(icon.getImage());

        //ADDING THE GAME PANEL AND SETTING THE WINDOW TO BE VISIBLE
        this.gamePanel = gamePanel;
        this.add(gamePanel);
        this.pack(); // fit the size of the window based on the component
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();

            }
        });
    }


}

