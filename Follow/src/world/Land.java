package world;
//missing world load
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

import main.Game;
import logic.Handler;

public class Land {

	private Handler handler;
	private Layout layout;
	private ArrayList<Layout> rooms;
	
	public Land(Handler handler,Layout layout) {
		this.handler=handler;
		rooms=new ArrayList<Layout>();
		rooms.add(layout);
		this.layout=rooms.get(0);
	}
	
	public void loadLand(Layout lay) {
		//add things
	}
	public void update() {
		//add things
		layout.update();
		if(handler.getPlayer().getX()<=0) {
			if(travel("le")) {
				layout.enterFrom(3);
				layout.getGC().update(layout.getWidth(), layout.getHeight());
			}
		}else if(handler.getPlayer().getX()>=layout.getWidth()-handler.getPlayer().getWidth()) {
			if(travel("ri")) {
				layout.enterFrom(2);
				layout.getGC().update(layout.getWidth(), layout.getHeight());
			}
		}
		
		if(handler.getPlayer().getY()<=0) {
			if(travel("up")) {
				layout.enterFrom(1);
				layout.getGC().update(layout.getWidth(), layout.getHeight());
			}
		}else if(handler.getPlayer().getY()>=layout.getHeight()-handler.getPlayer().getHeight()) {
			if(travel("do")) {
				layout.enterFrom(0);
				layout.getGC().update(layout.getWidth(), layout.getHeight());
			}
		}
	}
	public void render(Graphics g,float cx,float cy) {
		//add things
		layout.render(g,cx,cy);
	}
	public Handler getHandler() {return handler;}
	public Layout getLayout() {return layout;}
	public ArrayList<Layout> getRooms(){return rooms;}
	public void setLayout(Layout layout) {this.layout=layout;}
	
	public void travelTo(Layout lay) {
		this.layout=lay;
	}
	public boolean travel(String dir) {
		switch(dir) {
		case "up":
			if(layout.getUp()!=null) {
				layout=layout.getUp();
				return true;
			}else System.out.println("No layout. direction: "+dir);
			return false;
		case "do":
			if(layout.getDo()!=null) {
				layout=layout.getDo();
				return true;
			}else System.out.println("No layout. direction: "+dir);
			return false;
		case "le":
			if(layout.getLe()!=null) {
				layout=layout.getLe();
				return true;
			}else System.out.println("No layout. direction: "+dir);
			return false;
		case "ri":
			if(layout.getRi()!=null) {
				layout=layout.getRi();
				return true;
			}else System.out.println("No layout. direction: "+dir);
			return false;
		default:
			System.out.println("No layout. direction: "+dir);
			return false;
		}
	}
}