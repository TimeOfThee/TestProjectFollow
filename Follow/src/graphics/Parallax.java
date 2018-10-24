package graphics;

import java.awt.Rectangle;

import logic.GameCamera;
import logic.Handler;

public class Parallax {
	
	private int layer;//the layer of focus, this layer is mostly unchanged
	private float scale=1.01f;//size change
	private Handler handler;
	private GameCamera gamc;
	
	public Parallax(Handler handler,int layer) {
		this.layer=layer;
		this.handler=handler;
		this.gamc=handler.getGame().getGamC();
	}
	public void update(int layer) {
		this.layer=layer;
	}
	public Rectangle paRender(Rectangle ret, int layer) {
		if(layer==-1)layer=handler.getPlayer().getLayer();
		return resize(relocate(ret,layer),layer);
	}
	
	public Rectangle resize(Rectangle from,int lay) {
		int dist=lay-layer;
		Rectangle ret=new Rectangle(from);
		
		if(dist<0) {//back
			dist=dist*-1;
			ret.width=(int)(ret.width/(scale*dist));
			//ret.height=(int)(ret.height/(scale*dist));
		}else if(dist>0) {//front
			ret.width=(int)(ret.width*(scale*dist));
			//ret.height=(int)(ret.height*(scale*dist));
		}
		
		return ret;
	}
	
	public Rectangle relocate(Rectangle from,int lay) {
		int dist=lay-layer;
		Rectangle ret=new Rectangle(from);
		
		if(dist<0) {//back
			dist=dist*-1;
			ret.x=(int)(ret.x/(scale*dist));
		}else if(dist>0) {//front
			ret.x=(int)(ret.x*(scale*dist));
		}
		
		return ret;
	}
	
	public int getLayer() {return this.layer;}
	public void setLayer(int layer) {this.layer=layer;}
}
