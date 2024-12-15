package main;

import entities.Player;
import java.awt.Graphics;
import levels.LevelManager;

/*
everything will start from here and this will contain everything to make our game
*/
public class Game implements Runnable
{
    private GameFrame gameFrame;
    private GamePanel gamePanel;
    private Thread gameLoop;
    //set amount of frames per sec
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;
    
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;    
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);     
    public final static int GAME_WIDTH = (TILES_SIZE * TILES_IN_WIDTH);     
    public final static int GAME_HEIGHT = (TILES_SIZE * TILES_IN_HEIGHT);     
    
    
    public Game()
    {
        initClasses();
        this.gamePanel = new GamePanel(this);
        this.gameFrame = new GameFrame(this.gamePanel);
        this.gamePanel.requestFocus();
        startGameLoop();  
    }
    
    private void initClasses()
    {
        this.levelManager = new LevelManager(this); 
        this.player = new Player(200,200, (int)(32 * SCALE), (int)(33 * SCALE));
        this.player.loadLvlData(this.levelManager.getCurrentLevel().getLevelData());
    }
    
    private void startGameLoop()
    {
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
    }
    
    private void update()
    {
        this.player.update(); 
        this.levelManager.update(); 
        
    }
    
    public void render(Graphics g)
    {
        this.levelManager.draw(g);    
        this.player.render(g);    
    }

    public void run() 
    {    
        /*
        how long each frame needs to last
        will need to use nanosecs in order to be as precise as possible
        when playing a game, even a milisec is too long when it comes to 
        being aware of what is happening to your surroundings
        HENCE why we'll use nanosecs
        */
        double timePerFrame = 1000000000.0 / this.FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while(true)
        {
            //we will check from the previous frame to now if that duration passed, if it did then it's time for a new frame
            // so we do a repaint
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime-previousTime)/timePerUpdate;
            deltaF += (currentTime-previousTime)/timePerFrame;
            previousTime = currentTime;
            
            if(deltaU >= 1)
            {
                update();
                updates++;
                deltaU--;
            }
            
            if(deltaF >= 1)
            {
                this.gamePanel.repaint();
                deltaF--;
                frames++;
            }
            
            //checking the FPS
            if(System.currentTimeMillis() - lastCheck >= 1000)
            {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }        
        }
    }
    
    public void windowFocusLost()
    {
        this.player.resetDirBooleans();
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
}