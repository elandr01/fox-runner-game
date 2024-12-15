package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static utilz.HelpMethods.*;
import static utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;

public class Player extends Entity
{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean left, right, up, down, jump;
    private float playerSpeed = 2.0f;
    private boolean moving = false, rolling = false;
    private int[][] lvlData;
    
    //Jumping / Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    
    private float xDrawOffset = 7 * Game.SCALE;
    private float yDrawOffset = 10 * Game.SCALE;
    
    public Player(float x, float y, int width, int height) 
    {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x,y,15*Game.SCALE,20*Game.SCALE);
        
    }
    
    public void update()
    {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    
    public void render(Graphics g)
    {
        g.drawImage(animations[playerAction][aniIndex] ,(int)(hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), width ,height, null);
//        drawHitBox(g);
    }
    private void updateAnimationTick()
    {
        this.aniTick++;
        if(this.aniTick >= this.aniSpeed)
        {
            this.aniTick = 0;
            this.aniIndex++;
            if(this.aniIndex >= getSpriteAmount(this.playerAction))
            {
                this.aniIndex = 0;
                this.rolling = false;
            }
        }
    }
    
    private void resetAniTick()
    {
        aniTick = 0;
        aniIndex = 0;
    }

    private void setAnimation()
    {
        int startAni = playerAction;
        
        if(this.moving)
        {
            this.playerAction = RUNNING;
        }
        else
        {
            this.playerAction = IDLE;
        }
        if(inAir)
        {
            if(airSpeed < 0)
            {
                playerAction = ROLLING;
            }
//            else
//            {
//                playerAction = HURTING2;
//            }
        }
        
        if(this.rolling)
        {
            playerAction = ROLLING;
        }
        if(startAni != playerAction)
        {
            resetAniTick();
        }
    }

    private void updatePosition()
    {
        this.moving = false;
        if(jump)
        {
            jump();
        }
        if(!left && !right && !inAir)
        {
            return;
        }
        
        float xSpeed = 0;
        if(this.left)
        {
            xSpeed -= this.playerSpeed;
        }
        if(this.right)
        {
            xSpeed += this.playerSpeed;
        }
        if(!inAir)
        {
            if(!isEntityOnFloor(hitBox, lvlData))
            {
                inAir = true;
            }
        }
        
        if(inAir)
        {
            if(canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData))
            {
                hitBox.y += airSpeed;
                airSpeed += gravity;
               updateXPos(xSpeed); 
            }
            else
            {
                hitBox.y = getEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if(airSpeed > 0)
                {
                    resetInAir();
                }
                else
                {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        }
        else
        {
            updateXPos(xSpeed);
        }
        moving = true;
    }
    
    private void jump()
    {
        if(inAir)
        {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }
    
    private void resetInAir()
    {
        inAir = false;
        airSpeed = 0;
    }
    
    private void updateXPos(float xSpeed)
    {
        if(canMoveHere(hitBox.x+xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData))
        {
            hitBox.x += xSpeed;
        }
        else
        {
            hitBox.x = getEntityXPosNextToWall(hitBox, xSpeed);
        }
    }
    
    
    private void loadAnimations()
    {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        this.animations = new BufferedImage[11][6];
 
        for(int i = 0; i < this.animations.length; i++)
        {
            for(int j = 0; j < this.animations[i].length; j++)
            {
                this.animations[i][j] = img.getSubimage(j*33, i*32, 33, 32);
            }
        }
    }
    
    public void loadLvlData(int[][] lvlData)
    {
        this.lvlData = lvlData;
        if(!isEntityOnFloor(hitBox, lvlData))
        {
            inAir = true;
        }
    }
    
    public void resetDirBooleans()
    {
        this.left = false;
        this.up = false;
        this.down = false;
        this.right = false;
    }
    
    public void setJump(boolean jumping)
    {
        this.jump = jumping;
    }
    
    public void setRolling(boolean rolling)
    {
        this.rolling = rolling;
    }

    public boolean getLeft() 
    {
        return left;
    }

    public void setLeft(boolean left) 
    {
        this.left = left;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) 
    {
        this.right = right;
    }

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) 
    {
        this.up = up;
    }

    public boolean getDown() 
    {
        return down;
    }

    public void setDown(boolean down) 
    {
        this.down = down;
    }
    
    
}