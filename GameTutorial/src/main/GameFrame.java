package main;

//where the window/JFrame for the game will be created
//we will make it seperate from everyuthing just to make everything look smoother (organization wise)
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.*;
//import jdk.internal.org.jline.terminal.TerminalBuilder;
public class GameFrame extends JFrame
{
    public GameFrame(GamePanel gamePanel)
    {
        JFrame frame = new JFrame();
        frame.setTitle("Fox Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);//to make the window centered everytime
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowFocusListener()
        {
            @Override
            public void windowLostFocus(WindowEvent e)
            {
                gamePanel.getGame().windowFocusLost();
            }
            @Override
            public void windowGainedFocus(WindowEvent e)
            {
                
            }
            
        });
    }
}