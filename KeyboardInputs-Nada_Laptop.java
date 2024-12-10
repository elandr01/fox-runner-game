
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener
{
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void keyTyped(KeyEvent e) 
    {
        
    }


    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            this.gamePanel.setDirection(UP);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A)
        {
            this.gamePanel.setDirection(LEFT);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S)
        {
            this.gamePanel.setDirection(DOWN);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            this.gamePanel.setDirection(RIGHT);
        }
    }


    public void keyReleased(KeyEvent e) 
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                this.gamePanel.setMoving(false);
            break;
        }
    }
    
}