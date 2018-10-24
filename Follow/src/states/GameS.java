package states;

import java.awt.Color;
import java.awt.Graphics;

import blocks.BaseBlock;
import entities.BaseCreature;
import logic.Handler;
import world.Land;
import world.layouts.Blank;

public class GameS extends GState{
	
	public static boolean debug=false;
	private Land land;

	public GameS(Handler handler,Land land) {
		super(handler,land);		
	}
	
	@Override
	public void update() {
		//specific things
	}

	@Override
	public void render(Graphics g,float cx,float cy) {
		//specific things (Aura/overlay)
	}
}
