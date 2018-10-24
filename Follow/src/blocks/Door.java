
package blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import logic.Handler;
import world.Layout;

public class Door extends Block{
	
	private Layout room;	
	private int clor=0,fade=0,spx=0,spy=0,disx=0,disy=0;
	private String dir;
	private float aspect;
	private boolean sizeD=false;
	/**
	 * @param aspect if(sizeD)size[scale] else reversed(0-3)[null,x,y,xy]
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param layer
	 * @param room
	 * @param dir
	 * @param spawnx
	 * @param spawny
	 * @param sizeD
	 */
	public Door(Handler handler, float x, float y, int width, int height, int layer,Layout room,String dir,int spawnx, int spawny,float aspect,boolean sizeD) {
		super(handler, x, y, width, height, layer);
		this.room=room;
		this.dir=dir;
		this.aspect=aspect;
		spx=spawnx;
		spy=spawny;
		this.sizeD=sizeD;
	}
	
	public Layout getLay() {return room;}
	public void setLay(Layout room) {this.room= room;}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(Graphics g, float cx, float cy) {
		if(Handler.debug) {
			g.setColor(new Color(255-clor,clor,0,50));
			g.fillRect((int)(x+cx), (int)(y+cy), width, height);
		}
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
			int ex=(int)((handler.getPlayer().getX()+handler.getPlayer().getWidth()/2)-10+cx),wy=(int)(handler.getPlayer().getY()-25+cy);
			g.setColor(new Color(120,120,250,fade));
			g.fillRoundRect( ex, wy, 20, 20,5,5);
			g.setColor(new Color(40,40,160,fade+100));
			
			if(dir=="w") {
				g.drawPolyline(new int[] {ex+2,ex+5,ex+9,ex+13,ex+16}, new int[] {wy+2,wy+16,wy+2,wy+16,wy+2}, 5);
			}else if(dir=="s") {
				g.drawPolyline(new int[] {ex+13,ex+3,ex+4,ex+14,ex+15,ex+5}, new int[] {wy+2,wy+2,wy+9,wy+9,wy+16,wy+16}, 6);
			}else if(dir=="d") {
				g.drawPolyline(new int[] {ex+3,ex+15,ex+15,ex+3,ex+3}, new int[] {wy+2,wy+4,wy+14,wy+16,wy+2}, 5);
			}else if(dir=="a") {
				g.drawPolyline(new int[] {ex+3,ex+3,ex+13,ex+15,ex+14,ex+3}, new int[] {wy+16,wy+2,wy+2,wy+16,wy+9,wy+9}, 6);
			}else {
				g.setColor(new Color(160,40,40,fade+100));
				g.fillRoundRect( ex, wy, 20, 20,5,5);
			}
		}
	}
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void collideFrom(Entity e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isSolid() {return false;}
	@Override
	public boolean isAction() {return true;}
	public int getDisX() {return disx;}
	public int getDisY() {return disy;}
	
	public boolean action(String e) {
		if(e==dir) {
			if(sizeD) {
				handler.getPlayer().scale(aspect);
				
				handler.getLand().travelTo(room);
				
				room.spawnPlayer(spx, spy);
			
				return true;
			}else {
				this.disx=(int)(handler.getPlayer().getX()-x);
				this.disy=(int)(handler.getPlayer().getY()-y);
				handler.getLand().travelTo(room);
				if(aspect==3) {
					room.spawnPlayer((spx+width)-disx-handler.getPlayer().getWidth(),(spy+height)-disy-handler.getPlayer().getHeight());
				}else if(aspect==2) {
					room.spawnPlayer(spx+disx,(spy+height)-disy-handler.getPlayer().getHeight());
				}else if(aspect==1) {
					room.spawnPlayer((spx+width)-disx-handler.getPlayer().getWidth(),spy+disy);
				}else {
					room.spawnPlayer(spx+disx, spy+disy);
				}
				return true;
			}
		}else {return false;}
	}
}
