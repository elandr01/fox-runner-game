package inputs;

import java.awt.event.*;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
    private GamePanel gamePanel;

    //Constructor of Mouse Inputs
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        switch(Gamestate.state){
            case PLAYING: gamePanel.getGame().getPlaying().mouseClicked(e);break;
            default: break;

        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
    }

    @Override
    public void mouseMoved(MouseEvent e){
        switch(Gamestate.state){
            case MENU: gamePanel.getGame().getMenu().mouseMoved(e);break;
            case PLAYING: gamePanel.getGame().getPlaying().mouseMoved(e);break;
            default: break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e){
        switch(Gamestate.state){
            case MENU: gamePanel.getGame().getMenu().mousePressed(e);break;
            case PLAYING: gamePanel.getGame().getPlaying().mousePressed(e);break;
            default: break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(Gamestate.state){
            case MENU: gamePanel.getGame().getMenu().mouseReleased(e);break;
            case PLAYING: gamePanel.getGame().getPlaying().mouseReleased(e);break;
            default: break;
        }
    }
}