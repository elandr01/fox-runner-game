package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.*;
import static main.Game.SCALE;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;


public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    //global variable to contains player actions
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private boolean moving = false, rolling = false, jumping = false;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;

    private float xDrawOffset = 7 * Game.SCALE;
    private float yDrawOffset = 10 * Game.SCALE;


    //JUMPING / GRAVITY
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    public Player(float x, float y,int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x,y,15*Game.SCALE,20*Game.SCALE);

    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex] ,(int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width ,height, null);
//        drawHitbox(g);
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

        if(inAir){
            if(airSpeed < 0)
                playerAction = JUMPING;
            else
                playerAction = ROLLING;

        }

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

        if(jumping)
            jump();
        if(!left && !right && !inAir)
            return;

        float xSpeed = 0;

        if(left)
            xSpeed -= playerSpeed;
        if(right)
            xSpeed += playerSpeed;

        if(!inAir)
            if(!isEntityOnFloor(hitbox, lvlData))
                inAir = true;



       if(inAir){

           if(canMoveHere(hitbox.x,hitbox.y + airSpeed, hitbox.width, hitbox.height,lvlData)){
               hitbox.y += airSpeed;
               airSpeed += gravity;
               updateXPos(xSpeed);
           }
           else{

               hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
               if(airSpeed > 0)
                   resetInAir();
               else
                   airSpeed = fallSpeedAfterCollision;

               updateXPos(xSpeed);
           }
       }
       else
           updateXPos(xSpeed);
        moving = true;

    }

    private void jump(){
        if(inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir(){
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed){
        if(canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            hitbox.x += xSpeed;

        else
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);


    }



    private void loadAnimations() {

            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[11][6]; // the rows and columns are based of the spritesheet

            for (int i = 0; i < animations.length; ++i) {
                for (int j = 0; j < animations[i].length; ++j) {
                    animations[i][j] = img.getSubimage(j * 32, i * 33, 32, 33);
                }//end of inner loop
            }//end of outer loop
   }//end of loadAnimations method

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
        if(!isEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }

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

    public void setJump(boolean jumping){this.jumping = jumping;}

    public boolean isUp(){return up;}
    public void setUp(boolean up){this.up = up;}

    public boolean isRight(){return right;}
    public void setRight(boolean right){this.right = right;}

    public boolean isDown(){return down;}
    public void setDown(boolean down){this.down = down;}

    public boolean isLeft(){return left;}
    public void setLeft(boolean left){this.left = left;}


}//end of Player class