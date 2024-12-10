import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel
{
    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    
    
    public GamePanel()
    {
        this.mouseInputs = new MouseInputs(this);
        
        importImg();
        loadAnimations();
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(this.mouseInputs);
        addMouseMotionListener(this.mouseInputs);
    }
    
    private void loadAnimations()
    {
        this.animations = new BufferedImage[6][6];
        
        for(int i = 0; i < this.animations.length; i++)
        {
            for(int j = 0; j < this.animations[i].length; j++)
            {
                this.animations[i][j] = img.getSubimage(j*33, i*32, 33, 32);
            }
        }
    }
    
    private void importImg()
    {
        InputStream is = getClass().getResourceAsStream("/player.png");
        
        try 
        {
            this.img = ImageIO.read(is);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void setPanelSize()
    {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    
    public void setDirection(int direction)
    {
        this.playerDir = direction;
        this.moving = true;
    }
    
    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }
    
    private void updateAnimationTick()
    {
        this.aniTick++;
        if(this.aniTick >= this.aniSpeed)
        {
            this.aniTick = 0;
            this.aniIndex++;
            if(this.aniIndex >= GetSpriteAmount(this.playerAction))
            {
                this.aniIndex = 0;
            }
        }
    }
    
    private void setAnimation()
    {
        if(this.moving)
        {
            this.playerAction = RUNNING;
        }
        else
        {
            this.playerAction = IDLE;
        }
    }
    
    private void updatePosition()
    {
        if(moving)
        {
            switch(playerDir)
            {
                case LEFT:
                    this.xDelta -= 5;
                    break;
                case UP:
                    this.yDelta -= 5;
                    break;
                case RIGHT:
                    this.xDelta += 5;
                    break;
                case DOWN:
                    this.yDelta += 5;
                    break;
            }
        }
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
        updateAnimationTick();
        
        setAnimation();
        updatePosition();
        
        g.drawImage(this.animations[playerAction][aniIndex], (int)this.xDelta, (int)this.yDelta,132, 128, null);
    }
        
}