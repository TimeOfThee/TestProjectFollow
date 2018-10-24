package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import logic.Handler;

public class BaseBlock extends Block{
	
	private double clor=150;
	private boolean tog=true;
	private int run=0;

	public BaseBlock(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height, -1);
	}
	public BaseBlock(Handler handler, float x, float y, int width, int height,int layer) {
		super(handler, x, y, width, height, layer);
	}
	public BaseBlock(Handler handler, float x, float y, int width, int height,int layer,boolean visible,int run) {
		super(handler, x, y, width, height, layer);
		this.visible=visible;
		this.run=run;
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
		
		if(layer==-1) g.setColor(new Color((int)clor/2,200,(int)clor/2,100));
		else g.setColor(new Color((int)clor,(int)clor,(int)clor,100));
		
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
		if(run!=0) {
			if(e.isPlayer()) {
				e.setRun(run);
			}
		}
	}
	
	@Override
	public boolean isSolid() {return true;}
}
