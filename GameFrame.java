//where the window/JFrame for the game will be created
//we will make it seperate from everyuthing just to make everything look smoother (organization wise)
import javax.swing.*;
public class GameFrame extends JFrame
{
    public GameFrame(GamePanel gamePanel)
    {
        JFrame frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setLocationRelativeTo(null);//to make the window centered everytime
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}