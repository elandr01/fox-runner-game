package inputs;

import java.awt.event.*;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
    private GamePanel gamePanel;

    //Constructor of Mouse Inputs
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            gamePanel.getGame().getPlayer().setRolling(true);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
    }

    @Override
    public void mouseMoved(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}
}