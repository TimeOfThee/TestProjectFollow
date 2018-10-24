package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import blocks.Block;
import main.Game;
import logic.Handler;

public abstract class Entity {
	
	public static final int defHP=50,midground=2;
	
	protected Handler handler;
	protected float x,y;
	protected int hp;
	protected float width,height;
	protected Rectangle bounds;
	protected boolean alive=true,killable=false,collision;
	protected int layer;//smaller=further
	
	public Entity(Handler handler,float x,float y,int width, int height,int layer) {
		this.handler=handler;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		
		hp=defHP;
		this.layer=layer;
		this.collision=true;
		
		bounds=new Rectangle(0,0,width,height);
	}
	public abstract void update();
	public abstract void render(Graphics g,float cx,float cy);
	public abstract void die();
	
	public abstract void collideTo(Entity e);//do when this is colliding into an e
	public abstract void collideFrom(Entity e);//do when e is colliding into this
	public abstract void collideTo(Block b);//do when this is colliding into an e
	
	public boolean checkEntCollision(float xSkew,float ySkew) {
		boolean colide=false;
		if(!this.isPlayer() && (this.getLayer()==handler.getPlayer().getLayer() || layer==-1)) {
			if( getCollisionBounds(xSkew,ySkew).intersects(handler.getPlayer().getCollisionBounds(0,0)) ) {
				collideTo(handler.getPlayer());
				handler.getPlayer().collideFrom(this);
				colide=true;
			}
		}
		for(Entity e:handler.getLand().getLayout().getEntities()) {
			if(e.equals(this))continue;
			if(layer==-1 || layer==e.getLayer() || e.getLayer()==-1) {
				if( getCollisionBounds(xSkew,ySkew).intersects(e.getCollisionBounds(0,0)) ) {
					collideTo(e);
					e.collideFrom(this);
					colide=true;
				}
			}
		}
		return colide;
	}
	public boolean checkEntCollision(float xSkew,float ySkew,boolean react) {
		boolean colide=false;
		if(!this.isPlayer() && (this.getLayer()==handler.getPlayer().getLayer() || layer==-1)) {
			if( getCollisionBounds(xSkew,ySkew).intersects(handler.getPlayer().getCollisionBounds(0,0)) ) {
				if(react) {
					collideTo(handler.getPlayer());
					handler.getPlayer().collideFrom(this);
				}
				colide=true;
			}
		}
		for(Entity e:handler.getLand().getLayout().getEntities()) {
			if(e.equals(this))continue;
			if(layer==-1 || layer==e.getLayer() || e.getLayer()==-1) {
				if( getCollisionBounds(xSkew,ySkew).intersects(e.getCollisionBounds(0,0)) ) {
					if(react) {
						collideTo(e);
						e.collideFrom(this);
					}
					colide=true;
				}
			}
		}
		return colide;
	}
	public boolean checkBloCollision(float xSkew,float ySkew) {
		for(Block b:handler.getLand().getLayout().getBlocks()) {
			if(layer==b.getLayer() || b.getLayer()==-1 || layer==-1) {
				if( getCollisionBounds(xSkew,ySkew).intersects(b.getBounds()) && b.isSolid()) {
					this.collideTo(b);
					b.collideFrom(this);
					return true;
				}
			}
		}return false;
	}
	public boolean checkBloCollision(float xSkew,float ySkew,boolean react) {
		for(Block b:handler.getLand().getLayout().getBlocks()) {
			if(layer==b.getLayer() || b.getLayer()==-1 || layer==-1) {
				if( getCollisionBounds(xSkew,ySkew).intersects(b.getBounds()) && b.isSolid()) {
					if(react) {
						this.collideTo(b);
						b.collideFrom(this);
					}
					return true;
				}
			}
		}return false;
	}
	public boolean checkStairCollision(float xSkew,float ySkew) {
		for(Block b:handler.getLand().getLayout().getBlocks()) {
			if(b.isStair()) {
				if(layer==b.getLayer() || b.getLayer()==-1 || layer==-1) {
					if( getCollisionBounds(xSkew,ySkew).intersects(b.getBounds()) && b.isSolid()) {
						if( y+height-b.getY()<height/2 ) {
							this.collideTo(b);
							b.collideFrom(this);
							return true;
						}
					}
				}
			}
		}return false;
	}
	public Rectangle getCollisionBounds(float xSkew,float ySkew) {
		return new Rectangle( (int)(x+bounds.x+xSkew),(int)(y+bounds.y+ySkew),bounds.width,bounds.height );
	}
	
	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	
	public float getY() {return y;}
	public void setY(float y) {this.y = y;}
	
	public float getWidth() {return width;}
	public void setWidth(float width) {this.width = width;}
	
	public float getHeight() {return height;}
	public void setHeight(float height) {this.height = height;}
	
	public void scale(float scl) {
		this.width=this.width*scl;
		this.height=this.height*scl;
		this.bounds=new Rectangle(this.bounds.x,this.bounds.y,(int)this.width,(int)this.height);
	}
	
	public int getLayer() {return layer;}
	public void setLayer(int layer) {this.layer=layer;}
	
	public int getHP() {return hp;}
	public void setHP(int health) {this.hp = health;}
	public void damage(int amt) {
		hp-=amt;
		if(hp<=0) {alive=false;
			die();
		}
	}
	public Rectangle getBounds() {return bounds;}
	public void setBounds(Rectangle b) {bounds.setRect(b);}
	
	public boolean isAlive() {return alive;}
	public boolean isPlayer() {return false;}
	public boolean isKillable() {return killable;}
	public boolean isCollision() {return collision;}
	public boolean isCreature() {return false;}
	public void setAlive(boolean alive) {this.alive = alive;}
	public void setCollision(boolean c) {this.collision=c;}
	public void setRun(int run) {}
	
	public void push(float mx,float my) {}
}
