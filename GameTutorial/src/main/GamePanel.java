package main;

import inputs.MouseInputs;
import inputs.KeyboardInputs;
import java.awt.*;

import javax.swing.*;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;


public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private Game game;
    
    public GamePanel(Game game)
    {
        this.mouseInputs = new MouseInputs(this);
        this.game = game;
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(this.mouseInputs);
        addMouseMotionListener(this.mouseInputs);
    }
    
    private void setPanelSize()
    {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }
    
    public void updateGame()
    {
        
    }
    
    /*
    we will use a paintComponent method and we need to pass a graphics object to the method so that we are able to draw on the JPanel
    after that we are able to draw on the JPanel
    */
    public void paintComponent(Graphics g)
    {
        //calling the super class, JPanel's own paint component method
        //to clean up the frame and avoid glitches
        //as if it is cleaning the surface and allowing us to paint again with no problem
        super.paintComponent(g);
        this.game.render(g);
        
    }
        
    public Game getGame()
    {
        return this.game;
    }
}
