package world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import blocks.Block;
import blocks.Door;
import entities.Entity;
import entities.Player;
import logic.GameCamera;
import logic.Handler;
import main.Launcher;

public abstract class Layout {
	
	protected Handler handler;
	protected ArrayList<Entity> ents;
	protected ArrayList<Block> blocks;
	protected int width,height,xscale=0,yscale=0,disGroundL=0,disGroundR=0;
	protected float cx,cy;
	protected Layout conUp,conDo,conLe,conRi;
	protected GameCamera gc;
	
	protected ArrayList<Rectangle> togs;
	protected ArrayList<int[]> togls;
	protected ArrayList<Door> doors;
	
	public Layout(Handler handler,int wid,int hei) {
		this.handler=handler;
		this.width=wid;
		this.height=hei;
		this.ents=new ArrayList<Entity>();
		this.blocks=new ArrayList<Block>();
		this.doors=new ArrayList<Door>();
		init();

		setConnection(null,null,null,null);
		
		Player player=handler.getPlayer();
		this.gc=new GameCamera(handler,player.getX()+player.getWidth()/2,player.getY()+player.getHeight()/2);
		
		togs=new ArrayList<Rectangle>();
		togls=new ArrayList<int[]>();
	}
	/**
	 * Update background
	 */
	public abstract void update();
	/**
	 * Render background
	 */
	public abstract void render(Graphics g,float cx,float cy);
	
	public abstract void init();//fill with add

	public void enterFrom(int dir) {//updoleri
		switch(dir) {
		case 0:
			spawnPlayer(handler.getPlayer().getX(),1);
			break;
		case 1:
			spawnPlayer(handler.getPlayer().getX(),height-1-handler.getPlayer().getHeight());
			break;
		case 2:
			spawnPlayer(1,handler.getPlayer().getY());
			break;
		case 3:
			spawnPlayer(width-1-handler.getPlayer().getWidth(),handler.getPlayer().getY());
			break;
		default:
			
		}
	}
	
	public void setConnection(Layout up,Layout don,Layout le,Layout ri) {
		this.conDo=don;
		this.conUp=up;
		this.conLe=le;
		this.conRi=ri;
	}
	
	public ArrayList<Entity> getEntities() {return ents;}
	public void addEntity(Entity e) {ents.add(e);}
	
	public ArrayList<Block> getBlocks() {return blocks;}
	public void addBlock(Block b) {blocks.add(b);}
	
	public void addTogA(int x,int y,int sx,int sy,int l1,int l2) {
		this.togs.add(new Rectangle(x,y,sx,sy));
		this.togls.add(new int[] {l1,l2});
	}
	public void addTogA(int x,int y,int sx,int sy,int layer) {
		this.togs.add(new Rectangle(x,y,sx,sy));
		this.togls.add(new int[] {layer});
	}
	/**
	 * @param aspect if(sizeD)size[scale] else reversed(0-3)[null,x,y,xy]
	 * @param x
	 * @param y
	 * @param sx
	 * @param sy
	 * @param layer
	 * @param room
	 * @param spx
	 * @param spy
	 * @param sizeD
	 */
	public void addDoor(float x,float y,int sx,int sy,int layer,Layout room,int spx,int spy,float aspect,boolean sizeD) {
		doors.add(new Door(handler,x,y,sx,sy,layer,room,"w",spx,spy,aspect,sizeD));
	}

	public void spawnPlayer(float x,float y) {
		handler.getPlayer().setX(x);
		handler.getPlayer().setY(y);
	}
	
	public void setDisGround(int dis,boolean right) {
		if(right) {
			disGroundR=dis;
		}else {
			disGroundL=dis;
		}
	}
	public int getDisGround(boolean right) {
		if(right)
			return disGroundR;
		else
			return disGroundL;
	}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getXScale() {return xscale;}
	public void setXScale(int s) {this.xscale=s;}
	public int getYScale() {return yscale;}
	public void setYScale(int s) {this.yscale=s;}
	
	public GameCamera getGC() {return gc;}
	
	public Layout getUp() {return conUp;}
	public Layout getDo() {return conDo;}
	public Layout getLe() {return conLe;}
	public Layout getRi() {return conRi;}
}
