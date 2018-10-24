package blocks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import entities.Entity;
import logic.Handler;

public abstract class Block {
	
	protected Handler handler;
	protected float x,y;
	protected int width,height,layer;
	protected boolean contact=false,alive=true,visible=true;
	protected Rectangle bounds;
	
	public Block(Handler handler,float x,float y,int width,int height, int layer) {//add png input later
		this.handler=handler;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.bounds=new Rectangle((int)x,(int)y,width,height);
		this.layer=layer;
	}
	public abstract void update();
	public abstract void render(Graphics g,float cx,float cy);
	public abstract void die();
	public abstract void collideFrom(Entity e) ;
	
	public boolean action(String e) {return false;}
	public boolean actionW() {return false;}
	public boolean actionS() {return false;}
	
	public float getX() {return x;}
	public void setX(float x) {this.x=x;}
	public float getY() {return y;}
	public void setY(float y) {this.y=y;}
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width=width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height=height;}
	public float getLayer() {return layer;}
	public void setLayer(int l) {this.layer=l;}
	
	public Rectangle getBounds() {return bounds;}
	
	public boolean isContact() {return contact;}
	public void setContact(boolean s) {this.contact=s;}
	public boolean isAlive() {return alive;}
	public void setAlive(boolean s) {this.alive=s;}
	public boolean isVisible() {return visible;}
	public void setVisible(boolean s) {this.visible=s;}
	
	public boolean isSolid() {return true;}
	public boolean isStair() {return false;}
	public boolean isPush() {return false;}
	public boolean isLight() {return false;}
	public boolean isAction() {return false;}
}
