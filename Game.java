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
    
    public Game()
    {
        this.gamePanel = new GamePanel();
        this.gameFrame = new GameFrame(this.gamePanel);
        this.gamePanel.requestFocus();
        startGameLoop();
    }
    
    private void startGameLoop()
    {
        this.gameLoop = new Thread(this);
        this.gameLoop.start();
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
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while(true)
        {
            //we will check from the previous frame to now if that duration passed, if it did then it's time for a new frame
            // so we do a repaint
            now = System.nanoTime();
            if(now - lastFrame >= timePerFrame)
            {
                this.gamePanel.repaint();
                lastFrame = now;
                frames++;
            }
            
            //checking the FPS
            if(System.currentTimeMillis() - lastCheck >= 1000)
            {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }        
        }
    }
}