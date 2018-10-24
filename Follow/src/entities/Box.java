package entities;

import java.awt.Color;
import java.awt.Graphics;

import blocks.Block;
import logic.Handler;

public class Box extends Creature{
	
	int clor=200;
	boolean tog=true,active=false;
	int timer=0;
	float friction=2;

	public Box(Handler handler, float x, float y, int width, int height) {
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
		g.setColor(Color.yellow);
		
		g.drawRect((int)(x+cx), (int)(y+cy), (int)width, (int)height);
		g.setColor(new Color(clor,clor,50,150));
		g.fillRect((int)(x+bounds.x+cx), (int)(y+bounds.y+cy), bounds.width, bounds.height);
	}
	@Override
	public void ai() {
		if(xMov>1)xMov--;
		else if(xMov<-1)xMov++;
		else xMov=0;
		
		if(Handler.gravity) {
			if((onEnt()||onBlock()) && yMov>0)yMov=0;
			if(yMov<Handler.drag)yMov++;
		}
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
		
		if(getCollisionBounds(0,-2).intersects(e.getCollisionBounds(0,0)))return;
		
		if(e.getX()>x+width/2) {
			if(xMov>-friction) {
				xMov-=2;
			}
		}else {
			if(xMov<friction) {
				xMov+=2;
			}
		}
	}
	@Override
	public void collideTo(Block b) {
		// TODO Auto-generated method stub
		
	}
}