package blocks;

import java.awt.Color;
import java.awt.Graphics;

import entities.Entity;
import logic.Handler;

public class GBlock extends Block{
	
	private double clor=150;
	private boolean tog=true;
	private int tran=100;

	public GBlock(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height, -1);
	}
	public GBlock(Handler handler, float x, float y, int width, int height,int layer) {
		super(handler, x, y, width, height, layer);
	}
	public GBlock(Handler handler, float x, float y, int width, int height,int layer,boolean full) {
		super(handler, x, y, width, height, layer);
		if(full)tran=255;
	}

	@Override
	public void update() {
		if(tog) {
			clor+=0.8;
			if(clor>=240)tog=false;
		}else {
			clor-=0.8;
			if(clor<=220)tog=true;
		}
	}

	@Override
	public void render(Graphics g,float cx,float cy) {
		g.setColor(new Color((int)clor,(int)clor,(int)clor,tran));
		
		//g.setColor(new Color(100,0,0));
		
		g.fillRect((int)(x+cx)-1, (int)(y+cy)-1, width+3, height+2);
		g.setColor(new Color(150,150,150));
		g.drawRect((int)(x+cx), (int)(y+cy), width, height);
	}
	
	public void die() {
		
	}

	@Override
	public void collideFrom(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isSolid() {return false;}
}
