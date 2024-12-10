package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    //global variable to contains player actions
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, rolling = false, jumping = false;
    private float playerSpeed = 2.0f;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex] ,(int)x, (int)y, 256 ,160, null);
    }

    private void updateAnimationTick(){
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if(animationIndex >= getSpriteAmount(playerAction)){
                animationIndex = 0;
                rolling = false;
            }

        }

    }

    private void setAnimation(){
        int startAnimation = playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(rolling){
            playerAction = ROLLING;
        }

        if(startAnimation != playerAction){
           resetAnimationTick();
        }
    }

    private void resetAnimationTick(){
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePos(){

        moving = false;

        if(left && !right){
            x -= playerSpeed;
            moving = true;
        }
        else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if(up && !down) {
            y -= playerSpeed;
            moving = true;
        }
        else if(down && !up){
            y += playerSpeed;
            moving = true;
        }

    }


    private void loadAnimations() {

        InputStream is = getClass().getResourceAsStream("/textures/Spritesheet.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[11][6]; // the rows and columns are based of the spritesheet

            for (int i = 0; i < animations.length; ++i) {
                for (int j = 0; j < animations[i].length; ++j) {
                    animations[i][j] = img.getSubimage(j * 32, i * 33, 32, 33);
                }//end of inner loop
            }//end of outer loop

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//end of loadAnimations method

    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setRolling(boolean rolling){
        this.rolling = rolling;
    }

    //ACCESSOR AND MUTATOR METHODS

    public boolean isUp(){return up;}
    public void setUp(boolean up){this.up = up;}

    public boolean isRight(){return right;}
    public void setRight(boolean right){this.right = right;}

    public boolean isDown(){return down;}
    public void setDown(boolean down){this.down = down;}

    public boolean isLeft(){return left;}
    public void setLeft(boolean left){this.left = left;}


}//end of Player class