package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import entities.Entity;
import logic.Handler;

public class ActionBlock extends Block{
	
	private int clor=0,fade=0;
	private boolean tog=true;
	int[] slayer=new int[] {Entity.midground};
	int point=0;

	public ActionBlock(Handler handler, float x, float y, int width, int height,int[] layers) {
		super(handler, x, y, width, height, -1);
		this.slayer=layers;
	}
	public ActionBlock(Handler handler, float x, float y, int width, int height,int layer, int[] layers) {
		super(handler, x, y, width, height, layer);
		this.slayer=layers;
	}

	@Override
	public void update() {
		clor=slayer[point]*50;
	}

	@Override
	public void render(Graphics g,float cx,float cy) {
		if(Handler.debug) {
			g.setColor(new Color(255-clor,clor,0,50));
			g.fillRect((int)(x+cx), (int)(y+cy), width, height);
		}
		
		//System.out.println(Handler.action);
		//System.out.println(Handler.actionB+" "+this);
		if(Handler.action) {
			if(Handler.actionB.equals(this)) {
				if(fade<100)fade+=10;
				if(fade>100)fade=100;
			}
		}else {
			if(fade>0)fade-=10;
			if(fade<0)fade=0;
		}
		if(fade>0) {
			int ex=(int)((handler.getPlayer().getX()+handler.getPlayer().getWidth()/2)-10+cx)-2,wy=(int)(handler.getPlayer().getY()-50+cy);
			g.setColor(new Color(120,120,250,fade));
			g.fillRoundRect( ex, wy, 20, 20,5,5);
			
			g.setColor(new Color(40,40,160,fade+100));
			g.drawPolyline(new int[] {ex+2,ex+5,ex+9,ex+13,ex+16}, new int[] {wy+2,wy+16,wy+2,wy+16,wy+2}, 5);
			
			g.setColor(new Color(120,120,250,fade));
			ex+=4;wy+=23;
			g.fillRoundRect( ex, wy, 20, 20,5,5);
			
			g.setColor(new Color(40,40,160,fade+100));
			g.drawPolyline(new int[] {ex+13,ex+3,ex+4,ex+14,ex+15,ex+5}, new int[] {wy+2,wy+2,wy+9,wy+9,wy+16,wy+16}, 6);
		}
	}
	
	public void die() {
		
	}

	@Override
	public void collideFrom(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isSolid() {return false;}
	@Override
	public boolean isAction() {return true;}
	
	public boolean action(String e) {
		if(e=="w") {
			if(point<slayer.length-1)point++;
			handler.getPlayer().setLayer(slayer[point]);
			return true;
		}else if(e=="s") {
			if(point>0)point--;
			handler.getPlayer().setLayer(slayer[point]);
			return true;
		}else {return false;}
	}
}