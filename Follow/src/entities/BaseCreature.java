package entities;

import java.awt.Color;
import java.awt.Graphics;

import blocks.Block;
import logic.Handler;

public class BaseCreature extends Creature{
	
	int clor=200;
	boolean tog=true,active=false;
	int timer=0;
	float tarx=0,tary=0;

	public BaseCreature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	@Override
	public void update() {
		ai();
		
		move(xMov,yMov);
		if(tog) {
			clor+=2;
			if(clor>=250)tog=false;
		}else {
			clor-=2;
			if(clor<=150)tog=true;
		}
	}
	@Override
	public void render(Graphics g,float cx,float cy) {
		g.setColor(Color.green);
		if(Handler.debug) {
			g.drawLine((int)(x+width/2), (int)(y+height/2), (int)(tarx+width/2), (int)(tary+height/2));
			if(timer==0)g.drawOval((int)x+2, (int)y+2, (int)width-4, (int)height-4);
		}
		
		g.drawRect((int)(x+cx), (int)(y+cy), (int)width, (int)height);
		g.setColor(new Color(clor,255,clor,150));
		g.fillRect((int)(x+bounds.x+cx), (int)(y+bounds.y+cy), bounds.width, bounds.height);
	}
	@Override
	public void ai() {
		if(timer>0) {
			timer--;
			if(!Handler.gravity) {
				double mx=0,my=0;
				
				double xdis=tarx-x;
				double ydis=tary-y;
				double dis=Math.sqrt( xdis*xdis+ydis*ydis );
				
				if(dis>=spd) {
					mx=xdis/dis*spd;
					my=ydis/dis*spd;
					
					xMov=(float)mx;
					yMov=(float)my;
				}else {
					xMov=tarx-x;
					yMov=tary-y;
					timer=0;
				}
			}else{
				if(x<tarx-spd) {
					xMov+=acc;
				}else if(x>tarx+spd) {
					xMov-=acc;
				}else {
					xMov=tarx-x;
				}
				
				if(xMov>spd)xMov=spd;
				else if(xMov<-spd)xMov=-spd;
			}
		}else {
			if(xMov>1)xMov--;
			else if(xMov<-1)xMov++;
			else xMov=0;
			
			if(!Handler.gravity) {
				if(yMov>1)yMov--;
				else if(yMov<-1)yMov++;
				else yMov=0;
			}
			
			if((int)(Math.random()*100)==0 ) {
				tarx=(int)(Math.random()*300)-150+x;
				
				if(!Handler.gravity)tary=(int)(Math.random()*100)-50+y;
				else tary=y;
				
				if(tarx>handler.getGWidth())tarx=handler.getGWidth();
				else if(tarx<0)tarx=0;
				if(tary>handler.getGHeight())tary=handler.getGHeight();
				else if(tary<0)tary=0;
				timer=60;
			}
		}
		if(Handler.gravity)if(yMov<Handler.drag)yMov++;
	}
	@Override
	public void die() {
		System.out.println("BaseCreature Died.");
	}
	@Override
	public void collideTo(Entity e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void collideFrom(Entity e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void collideTo(Block b) {
		// TODO Auto-generated method stub
		
	}
}
