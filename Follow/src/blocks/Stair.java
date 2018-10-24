package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import logic.Handler;

public class Stair extends Block{
	
	private double clor=150;
	private boolean tog=true;

	public Stair(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height, -1);
	}
	public Stair(Handler handler, float x, float y, int width, int height,int layer) {
		super(handler, x, y, width, height, layer);
	}
	public Stair(Handler handler, float x, float y, int width, int height,int layer,boolean visible) {
		super(handler, x, y, width, height, layer);
		this.visible=visible;
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
		
		/*Rectangle ren=Handler.para.paRender(new Rectangle((int)x,(int)y,(int)width,(int)height),layer);
		
		g.setColor(new Color((int)clor,(int)clor,(int)clor,100));
		g.fillRect((int)(ren.x+cx), (int)(ren.y+cy), ren.width, ren.height);
		g.setColor(new Color(150,150,150));
		g.drawRect((int)(ren.x+cx), (int)(ren.y+cy), ren.width, ren.height);*/
		
		g.setColor(new Color((int)clor,(int)clor,(int)clor,100));
		g.fillRect((int)(x+cx), (int)(y+cy), width, height);
		g.setColor(new Color(150,150,150));
		g.drawRect((int)(x+cx), (int)(y+cy), width, height);
	}
	
	public void die() {
		
	}

	@Override
	public void collideFrom(Entity e) {
		if(e.getY()+e.getHeight()>y+2)
			e.setY(e.getY()-2);
		else
			e.setY(y-e.getHeight());
	}
	
	@Override
	public boolean isSolid() {return true;}
	public boolean isStair() {return true;}
}
