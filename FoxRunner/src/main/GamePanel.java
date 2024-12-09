package main;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.image.*;
import java.io.*;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private BufferedImage img, subImg;

    public GamePanel(){
        addKeyListener(new KeyboardInputs(this));
        mouseInputs = new MouseInputs(this);

       importImg();

        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();


    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/textures/Spritesheet.png");

        try{
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                is.close();
            }catch(IOException e){e.printStackTrace();}
        }

    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);

    }

    public void changeXDelta(int val){this.xDelta += val;}
    public void changeYDelta(int val){this.yDelta += val;}
    public void setPosition(int x, int y){xDelta = x;yDelta = y;}


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        subImg = img.getSubimage(0*33,10*32,33,32);
        g.drawImage(subImg ,(int)xDelta, (int)yDelta, 128 ,80, null);

    }
}
