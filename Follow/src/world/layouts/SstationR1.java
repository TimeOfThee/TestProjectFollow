package world.layouts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Player;
import logic.Handler;
import main.Launcher;
import world.Layout;

public class SstationR1 extends Layout{
	
	int clor=0;
	boolean tog=true;
	
	public SstationR1(Handler handler) {
		super(handler,1500,800);
	}

	@Override
	public void init() {
		//fill with ent.add/blocks.add
	}

	@Override
	public void update() {
		Player player=handler.getPlayer();
		gc.setTarget(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2);
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
			g.drawLine(a+(int)cx, 0+(int)cy, a+(int)cx, height);
		}
		for(int a=0;a<height;a+=20) {
			if(a%100==0) {
				g.setColor(new Color(190,190,190));
			}else if(a%50==0) {
				g.setColor(new Color(210,210,210));
			}else {
				g.setColor(new Color(230,230,230));
			}
			g.drawLine(0+(int)cx, a+(int)cy, width+xscale, a+(int)cy);
		}

		if(handler.debug) {
			g.setColor(new Color(255-clor,clor,0,50));
			for(Rectangle r:togs)
				g.fillRect(r.x+(int)cx, r.y+(int)cy, r.width, r.height);
		}
		
		g.setColor(Color.black);
		g.drawString(handler.getMouseManager().getMX()-(int)cx+" "+(handler.getMouseManager().getMY()-(int)cy), handler.getMouseManager().getMX()-22, handler.getMouseManager().getMY()-5);
	}
	@Override
	public void enterFrom(int dir) {//updoleri
		tog=true;
		switch(dir) {
		case 0://up
			spawnPlayer(handler.getPlayer().getX(),1);
			break;
		case 1://do
			spawnPlayer(handler.getPlayer().getX(),height-1-handler.getPlayer().getHeight());
			break;
		case 2://le
			spawnPlayer(1,handler.getPlayer().getY()+ (800-20)-(600-380));
			break;
		case 3://ri
			spawnPlayer(width-1-handler.getPlayer().getWidth(),handler.getPlayer().getY()+ (800-20)-(1000-180));
			break;
		default:
			
		}
	}
}
