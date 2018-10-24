package world.layouts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Player;
import logic.Handler;
import main.Launcher;
import world.Layout;

public class Blank4 extends Layout{
	
	int clor=0;
	boolean tog=true;
	
	public Blank4(Handler handler) {
		super(handler,Launcher.wid,Launcher.hei);
	}

	@Override
	public void init() {
		//fill with ent.add/blocks.add
	}

	@Override
	public void update() {
		gc.update(width, height);
		
		Player pla=handler.getPlayer();
		
		clor=pla.getLayer()*50;
		boolean cancel=false;
		
		for(int a=0;a<togs.size();a++) {
			if(pla.getCollisionBounds(0, 0).intersects(togs.get(a))) {
				if(tog) {
					if(togls.get(a).length==2) {
						if(pla.getLayer()==togls.get(a)[0] )pla.setLayer( togls.get(a)[1] );
						else if(pla.getLayer()==togls.get(a)[1] )pla.setLayer( togls.get(a)[0] );
						tog=false;
					}else {
						pla.setLayer(togls.get(a)[0]);
						tog=false;
					}
				}
				cancel=true;
			}
		}
		if(!cancel)tog=true;
	}

	@Override
	public void render(Graphics g,float cx,float cy) {
		g.setColor(new Color(250,250,250));
		g.fillRect(0, 0, width, height);
		
		for(int a=0;a<width;a+=20) {
			if(a%100==0) {
				g.setColor(new Color(190,190,190));
			}else if(a%50==0) {
				g.setColor(new Color(210,210,210));
			}else {
				g.setColor(new Color(230,230,230));
			}
			g.drawLine(a, 0, a, height);
		}
		for(int a=0;a<height;a+=20) {
			if(a%100==0) {
				g.setColor(new Color(190,190,190));
			}else if(a%50==0) {
				g.setColor(new Color(210,210,210));
			}else {
				g.setColor(new Color(230,230,230));
			}
			g.drawLine(0, a, width, a);
		}

		g.setColor(new Color(255-clor,clor,0,50));
		for(Rectangle r:togs)
			g.fillRect(r.x, r.y, r.width, r.height);
		
		g.setColor(Color.black);
		g.drawString(handler.getMouseManager().getMX()-(int)cx+" "+(handler.getMouseManager().getMY()-(int)cy), handler.getMouseManager().getMX()-22, handler.getMouseManager().getMY()-5);
	}
	
}
