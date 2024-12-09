package main;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {



    private GamePanel gamePanel;

    public GameWindow(GamePanel gamePanel){

        //Set Fixed Size
        this.setResizable(false);

        //Set Default Settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Fox Runner");

        //ADDING THE GAME PANEL AND SETTING THE WINDOW TO BE VISIBLE
        this.gamePanel = gamePanel;
        this.add(gamePanel);
        this.pack(); // fit the size of the window based on the component
        this.setVisible(true);
    }


}


