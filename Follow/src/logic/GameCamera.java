package logic;

import java.awt.Rectangle;

import main.Game;
import main.Launcher;

public class GameCamera {
	
	private Handler handler;
	private float cx,cy,tx,ty;
	private Rectangle sketch=new Rectangle(0,0,0,0);

	int xsc=0,ysc=0;
	
	int wid=Launcher.wid;
	int hei=Launcher.hei;
	int[] o1=new int[2],o2=new int[2];
	boolean tog=true;
	
	public GameCamera(Handler handler,float tx,float ty) {
		this.handler=handler;
		setTarget(tx, ty);
		cx=tx;cy=ty;
	}
	public void update(int sx,int sy) {//sx,sy=size of room
		xsc=handler.getLand().getLayout().getXScale();
		ysc=handler.getLand().getLayout().getYScale();
		
		float ttx=tx-wid/2;
		float tty=ty-hei/2;
		
		if(cx>ttx+1 || cx<ttx-1) {cx=(cx+ttx)/2;}
		else {cx=ttx;}
		
		if(cx<=0)cx=0;
		else if(cx>=sx-wid) cx=sx-wid;
		
		if(cy>tty+1 || cy<tty-1) {cy=(cy+tty)/2;}
		else {cy=tty;}
		
		if(cy<=0)cy=0;
		else if(cy>=sy-hei) cy=sy-hei;
		
		MouseManager mm=handler.getMouseManager();
		if(mm.isM1()) {
			if(o1[0]<o2[0])System.out.print(o1[0]+",");
			else System.out.print(o2[0]+",");
			if(o1[1]<o2[1])System.out.print(o1[1]+",");
			else System.out.print(o2[1]+",");
			System.out.println(Math.abs(o2[0]-o1[0])+","+Math.abs(o2[1]-o1[1]));
			
			if(tog) {
				o1[0]=(int)(mm.getMX()+cx-xsc);
				o1[1]=(int)(mm.getMY()+cy-ysc);
				tog=false;
			}else {
				o2[0]=(int)(mm.getMX()+cx-xsc);
				o2[1]=(int)(mm.getMY()+cy+ysc);
			}
		}else tog=true;
		
		if(o1[0]<o2[0]) {
			sketch.x=o1[0];
			sketch.width=o2[0]-o1[0];
		}else {
			sketch.x=o2[0];
			sketch.width=o1[0]-o2[0];
		}
		
		if(o1[1]<o2[1]) {
			sketch.y=o1[1];
			sketch.height=o2[1]-o1[1];
		}else {
			sketch.y=o2[1];
			sketch.height=o1[1]-o2[1];
		}
	}
	
	public void setTarget(float tx,float ty) {this.tx=tx;this.ty=ty;}
	public float getTX() {return tx;}
	public float getTY() {return ty;}
	public float getCX() {return cx;}
	public float getCY() {return cy;}
	public void setCX(float cx) {this.cx=cx;}
	public void setCY(float cy) {this.cy=cy;}
	
	public Rectangle getView() {
		return new Rectangle((int)cx,(int)cy,wid,hei);
	}
	public Rectangle getSketch() {
		return sketch;
	}
}
