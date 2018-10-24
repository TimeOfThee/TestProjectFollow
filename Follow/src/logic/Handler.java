package logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import blocks.Block;
import entities.Entity;
import entities.Player;
import graphics.Parallax;
import main.Game;
import world.Land;
import world.Layout;
import world.layouts.LandAssets;

public class Handler {
	
	public static boolean debug=false;
	
	private Game game;
	public LandAssets landscape;
	public static boolean gravity=true,action=false;
	public static Block actionB;
	public static int drag=20;
	
	public static Parallax para;
	
	public Handler(Game game) {
		this.game=game;	
		
		para=new Parallax(this,Entity.midground);
	}
	public void loadAssets() {
		landscape=new LandAssets(this);
	}
	
	public void update() {
		Game.getCurrState().update();
		Land land=getLand();
		
		land.update();
		getPlayer().update();
		//para.update(getPlayer().getLayer());
		
		action=false;
		for(Block b:land.getLayout().getBlocks()) {
			b.update();
			if(!action  && b.isAction())
				if(game.getPlayer().getCollisionBounds(0, 0).intersects(b.getBounds())) {
					action=true;
					actionB=b;
					//System.out.println("in action");
				}
		}
		for(Entity e:land.getLayout().getEntities()) {
			e.update();
		}
		
		//upd ui
	}
	public void render(Graphics g,float cx,float cy) {
		Game.getCurrState().render(g,cx,cy);
		Land land=getLand();
		
		land.render(g,cx,cy);
		
		GameCamera gc=getLand().getLayout().getGC();
		
		if(debug) {
			g.setColor(Color.red);
			g.drawRect((int)(gc.getView().x+cx),(int)(gc.getView().y+cy),gc.getView().width-1,gc.getView().height-1);
			int a=-83;
			for(int b=0;b<land.getRooms().size();b++) {
				if(land.getRooms().get(b).equals(getLand().getLayout()))a=b;
			}
			g.drawString(Integer.toString(a), 2, 11);
		}
		
		//layers exist, use loop
		int layers=4;
		for(int l=0;l<layers+1;l++) {
			if(l==game.getPlayer().getLayer())game.getPlayer().render(g,cx,cy);
			for(Entity e:land.getLayout().getEntities()) {
				if(l==layers && e.getLayer()==-1)e.render(g, cx, cy);
				else if(e.getLayer()==l)e.render(g,cx,cy);
			}
			for(Block b:land.getLayout().getBlocks()) {
				if(!b.isVisible())continue;				
				if( !b.getBounds().intersects(getLand().getLayout().getGC().getView()) )continue;
				
				if(l==layers && b.getLayer()==-1)b.render(g, cx, cy);
				if(b.getLayer()==l)b.render(g,cx,cy);
			}
		}
		//ren ui
		if(debug) {
			g.setColor(new Color(0,0,255,50));
			Rectangle pl=getPlayer().getCollisionBounds(0,0);
			g.drawRect((int)(pl.x+cx)-1, (int)(pl.y+cy)-1, pl.width+1, pl.height+1);
			
			g.setColor(Color.green.darker());
			pl=getLand().getLayout().getGC().getSketch();
			g.drawRect((int)(pl.x+cx), (int)(pl.y+cy), pl.width, pl.height);
			
			String str=( Integer.toString(pl.x)+" , "+Integer.toString(pl.y)+" , "+Integer.toString(pl.width)+" , "+Integer.toString(pl.height));
			
			g.drawString(str, (int)(pl.x+cx), (int)(pl.y+cy)-1);
		}
	}
	
	public Game getGame() {return game;}
	public void setGame(Game game) {this.game = game;}
	public Player getPlayer() {return game.getPlayer();}

	//public GameCamera getGameCamera() {return game.getGameCamera();}
	public KeyManager getKeyManager() {return game.getKeyManager();}
	public MouseManager getMouseManager() {return game.getMouseManager();}
	
	public int getGWidth() {return game.getWidth();}
	public int getGHeight() {return game.getHeight();}
	
	public Land getLand() {return Game.getCurrState().getLand();}
	public LandAssets getLandscape() {return landscape;}
}
