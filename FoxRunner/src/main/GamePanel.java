package main;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.image.*;
import java.io.*;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel{

    //Mouse inputs
    private MouseInputs mouseInputs;
    private Game game;



    public GamePanel(Game game){
        addKeyListener(new KeyboardInputs(this));
        mouseInputs = new MouseInputs(this);
        this.game = game;

        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();


    }




    private void setPanelSize(){
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }


    public void updateGame(){

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame(){
        return game;
    }
}