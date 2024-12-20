package entities;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {
        private BufferedImage[][] animations;
        private int aniTick, aniIndex, aniSpeed = 15;
        //global variable to contains player actions
        private int playerAction = IDLE;
        private boolean left, up, right, down;
        private boolean moving = false, rolling = false, jumping = false, crouching = false, dizzy = false,resting = false;
        private float playerSpeed = 1.0f * Game.SCALE;
        private int[][] lvlData;

        private float xDrawOffset = 7 * Game.SCALE;
        private float yDrawOffset = 10 * Game.SCALE;


        //JUMPING / GRAVITY
        private float airSpeed = 0f;
        private float gravity = 0.04f * Game.SCALE;
        private float jumpSpeed = -2.25f * Game.SCALE;
        private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
        private boolean inAir = false;

	public Player(float x, float y, int width, int height) 
        {
            super(x, y, width, height);
            loadAnimations();
            initHitbox(x, y, (int) (15 * Game.SCALE), (int) (20 * Game.SCALE));

	}

	public void update() 
        {
            updatePos();
            updateAnimationTick();
            setAnimation();
	}

	public void render(Graphics g) 
        {
            g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
//		drawHitbox(g);
	}

	private void updateAnimationTick() 
        {
            aniTick++;
            if (aniTick >= aniSpeed) 
            {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= getSpriteAmount(playerAction)) 
                {
                    aniIndex = 0;

                }

            }

	}

	private void setAnimation() 
        {
            int startAni = playerAction;

            if (moving)
            {
                playerAction = RUNNING;
            }
            else
            {
                playerAction = IDLE;
            }

            if (inAir) 
            {
                if (airSpeed < 0)
                {
                    playerAction = JUMPING;
                }

            }
            if (startAni != playerAction)
            {
                resetAniTick();
            }
	}

	private void resetAniTick() 
        {
            aniTick = 0;
            aniIndex = 0;
	}

	private void updatePos() 
        {
            moving = false;

            if (jumping)
            {
                jump();
            }
            if (!left && !right && !inAir)
            {
                return;
            }

            float xSpeed = 0;

            if (left)
            {
                xSpeed -= playerSpeed;
            }
            if (right)
            {
                xSpeed += playerSpeed;
            }

            if (!inAir)
            {
                if (!isEntityOnFloor(hitbox, lvlData))
                {
                    inAir = true;
                }
            }
            if (inAir) 
            {
                if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) 
                {
                    hitbox.y += airSpeed;
                    airSpeed += gravity;
                    updateXPos(xSpeed);
                } 
                else 
                {
                    hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                    if (airSpeed > 0)
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
            if (inAir)
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
            if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) 
            {
                hitbox.x += xSpeed;
            } 
            else 
            {
                hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
            }

	}

	private void loadAnimations() 
        {

            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++)
            {
                for (int i = 0; i < animations[j].length; i++)
                {
                    animations[j][i] = img.getSubimage(i * 33, j * 32, 33, 32);
                }
            }

	}

	public void loadLvlData(int[][] lvlData) 
        {
            this.lvlData = lvlData;
            if (!isEntityOnFloor(hitbox, lvlData))
            {
                inAir = true;
            }

	}

	public void resetDirBooleans() 
        {
            left = false;
            right = false;
            up = false;
            down = false;
	}


	public boolean isLeft() 
        {
            return left;
	}

	public void setLeft(boolean left) 
        {
            this.left = left;
	}

	public boolean isUp() 
        {
            return up;
	}

	public void setUp(boolean up)
        {
            this.up = up;
	}

	public boolean isRight()
        {
            return right;
	}

	public void setRight(boolean right) 
        {
            this.right = right;
	}

	public boolean isDown() 
        {
            return down;
	}

	public void setDown(boolean down) 
        {
            this.down = down;
	}

	public void setJump(boolean jump) 
        {
            this.jumping = jump;
	}

}