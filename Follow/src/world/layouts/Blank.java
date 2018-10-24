package world.layouts;

import java.awt.Color;
import java.awt.Graphics;

import logic.Handler;
import main.Launcher;
import world.Layout;

public class Blank extends Layout{
	
	public Blank(Handler handler) {
		super(handler,Launcher.wid,Launcher.hei);
	}

	@Override
	public void init() {
		//fill with ent.add/blocks.add
	}

	@Override
	public void update() {
		gc.update(width, height);
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
		g.setColor(Color.black);
		g.drawString(handler.getMouseManager().getMX()+" "+handler.getMouseManager().getMY(), handler.getMouseManager().getMX()-22, handler.getMouseManager().getMY()-5);
	}
}
