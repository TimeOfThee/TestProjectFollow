package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import blocks.Block;
import logic.Handler;

public abstract class Creature extends Entity{
	
	public static final float defSPD=6.0f,defACC=2.0f,defDCC=0.3f;
	protected float spd,xMov,yMov,acc,dcc;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height,-1);
		this.spd=defSPD;
		this.acc=defACC; 
		this.dcc=defDCC;
		this.xMov=0;this.yMov=0;
	}
	public Creature(Handler handler, float x, float y, int width, int height,int layer) {
		super(handler, x, y, width, height,layer);
		this.spd=defSPD;
		this.acc=defACC; 
		this.dcc=defDCC;
		this.xMov=0;this.yMov=0;
	}

	@Override
	public abstract void update();

	@Override
	public abstract void render(Graphics g,float cx,float cy);

	@Override
	public abstract void die();
	
	public abstract void ai();
	
	@Override
	public void collideTo(Entity e) {
		
	}
	public void collideFrom(Entity e) {
		
	}
	public void collideTo(Block b) {
		
	}
	
	public void move(float mx,float my) {//fix hitbox jumping
		moveX(mx);
		moveY(my);
	}
	public void moveX(float mx) {
		/*for(int a=0;a<2;a++) {
			if(!checkEntCollision(mx/2,0) && !checkBloCollision(mx/2,0))x+=mx/2;
		}*/
		boolean step=false;
		
		if(checkStairCollision(mx,0)) {
			x+=mx;
		}else if(!checkEntCollision(mx,0) && !checkBloCollision(mx,0))x+=mx;
		else step=true;
		
		if(step) {
			for(int a=0;a<Math.abs(mx);a++) {
				if(mx>0) {
					if(checkStairCollision(1,0)) {
						x+=1;
					}else if(!checkEntCollision(1,0) && !checkBloCollision(1,0))x+=1;
					else return;
				}else if(mx<0) {
					 if(checkStairCollision(-1,0)) {
							x-=1;
					}else if(!checkEntCollision(-1,0) && !checkBloCollision(-1,0))x-=1;
					else return;
				}
			}
		}
		if(x<0)x=0;
		else if(x>handler.getLand().getLayout().getWidth()-width)x=handler.getLand().getLayout().getWidth()-width;
	}
	public void moveY(float my) {
		/*for(int a=0;a<2;a++) {
			if(!checkEntCollision(0,my/2) && !checkBloCollision(0,my/2))y+=my/2;
		}*/
		boolean step=false;
		if(!checkEntCollision(0,my) && !checkBloCollision(0,my)) {
			y+=my;
		}else step=true;
		
		if(step) {
			for(int a=0;a<Math.abs(my);a++) {
				if(my>0) {
					if(!checkEntCollision(0,1) && !checkBloCollision(0,1))y+=1;
					else return;
				}else if(my<0) {
					if(!checkEntCollision(0,-1) && !checkBloCollision(0,-1))y-=1;
					else return;
				}
			}
		}
		if(y<0)y=0;
		else if(y>handler.getLand().getLayout().getHeight()-height)y=handler.getLand().getLayout().getHeight()-height;
	}
	public void staticPush(Rectangle rect) {
		float cex=x+width/2,crx=rect.x+rect.width/2;
		float cey=y+height/2,cry=rect.y+rect.height/2;
		
		boolean dx=false,dy=false;
		
		if(cex>crx)dx=true;
		if(cey>cry)dy=true;
		
		if( Math.abs(cex-crx)<Math.abs(cey-cry) ) {
			if(dx) {
				x=rect.x+rect.width;
			}else{
				x=rect.x-width;
			}
		}else {
			if(dy) {
				y=rect.y+rect.height;
			}else {
				y=rect.y-height;
			}
		}
		
	}
	public boolean onBlock(){
		if(checkBloCollision(0, 2, false))return true;
		return false;
	}
	public boolean onEnt() {
		if(checkEntCollision(0,2,false))return true;
		return false;
	}
	public void push(float mx,float my) {
		move(mx,my);
	}
	public boolean isCreature() {return true;}
	
	public void setSPD(float spd) {this.spd=spd;}
}
