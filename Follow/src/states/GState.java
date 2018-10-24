package states;

import java.awt.Graphics;

import logic.Handler;
import world.Land;

public abstract class GState{

	protected Handler handler;
	protected Land land;
	
	public GState(Handler handler,Land land) {
		this.handler=handler;
		this.land=land;
	}
	public abstract void update();
	public abstract void render(Graphics g,float cx,float cy);
	
	public Land getLand() {return land;}
	public void setLand(Land l) {this.land=l;}
}
