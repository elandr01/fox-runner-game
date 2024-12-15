package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity
{
    //classes that extend from Entity can use these variables
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;
    public Entity(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    protected void drawHitBox(Graphics g)
    {
        //for debugging hitbox
        g.setColor(Color.pink);
        g.drawRect((int)this.hitBox.x, (int)this.hitBox.y, (int)this.hitBox.width, (int)this.hitBox.height);
    }
    
    protected void initHitBox(float x, float y, float width, float height)
    {
        this.hitBox = new Rectangle2D.Float(x, y, width, height);
    }
    
//    protected void updateHitBox()
//    {
//        this.hitBox.x = (int)this.x;
//        this.hitBox.y = (int)this.y;
//    }
    
    public Rectangle2D.Float getHitbox()
    {
        return this.hitBox;
    }
}