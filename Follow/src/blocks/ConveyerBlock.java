package blocks;

import java.awt.Color;
import java.awt.Graphics;

import entities.Entity;
import logic.Handler;

public class ConveyerBlock extends Block{
	
	private double clor=150;
	private boolean tog=true;
	private float mx,my;

	public ConveyerBlock(Handler handler, float x, float y, int width, int height,float mx, float my) {
		super(handler, x, y, width, height, -1);
		this.mx=mx;
		this.my=my;
	}
	public ConveyerBlock(Handler handler, float x, float y, int width, int height,float mx,float my,int layer) {
		super(handler, x, y, width, height, layer);
		this.mx=mx;
		this.my=my;
	}
	public ConveyerBlock(Handler handler, float x, float y, int width, int height,float mx,float my,int layer,boolean visible) {
		super(handler, x, y, width, height, layer);
		this.visible=false;
		this.mx=mx;
		this.my=my;
	}

	@Override
	public void update() {
		if(tog) {
			clor+=0.8;
			if(clor>=180)tog=false;
		}else {
			clor-=0.8;
			if(clor<=120)tog=true;
		}
	}

	@Override
	public void render(Graphics g,float cx,float cy) {
		g.setColor(new Color((int)clor,(int)clor,(int)clor,100));
		g.fillRect((int)(x+cx), (int)(y+cy), width, height);
		g.setColor(new Color(150,150,150));
		g.drawRect((int)(x+cx), (int)(y+cy), width, height);
	}
	
	public void die() {
		
	}

	@Override
	public void collideFrom(Entity e) {
		e.push(mx, my);
	}
	
	@Override
	public boolean isSolid() {return true;}
}
