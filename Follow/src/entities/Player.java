package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import blocks.Block;
import logic.Handler;
import logic.KeyManager;
import logic.MouseManager;
import main.Game;

public class Player extends Creature{
	
	int clor=150,llayer=Entity.midground;
	boolean tog=true;
	int run=0,delay=0;
	
	private KeyManager kM;
	private MouseManager mM;

	public Player(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height,Entity.midground);
		this.kM=handler.getKeyManager();
		this.mM=handler.getMouseManager();
	}

	@Override
	public void update() {
		ai();
		if(onBlock()) {
			if(run==0||yMov>=Handler.drag) {
				if(this.spd>Creature.defSPD) {
					this.setSPD(this.spd-0.5f);
				}else {
					this.setSPD(Creature.defSPD);
				}
			}else if(run==1) {
				if(this.spd<Creature.defSPD+5) {
					this.setSPD(this.spd+0.5f);
				}else {
					this.setSPD(Creature.defSPD+5);
				}
			}else {
				if(this.spd>Creature.defSPD-1.5) {
					this.setSPD(this.spd-0.5f);
				}else {
					this.setSPD(Creature.defSPD-1.5f);
				}
			}
			
			/*if(!running||yMov>=Handler.drag) {
				if(this.spd>Creature.defSPD) {
					this.setSPD(this.spd-0.5f);
				}else {
					this.setSPD(Creature.defSPD);
				}
			}else {
				if(this.spd<Creature.defSPD+5) {
					this.setSPD(this.spd+0.5f);
				}else {
					this.setSPD(Creature.defSPD+5);
				}
			}*/
		}
		run=0;
		move(xMov,yMov);
		if(tog) {
			clor+=2;
			if(clor>=250)tog=false;
		}else {
			clor-=2;
			if(clor<=150)tog=true;
		}
	}
	public void ai() {
		
		if(kM.kD) {
			if(onBlock()||onEnt())xMov+=acc;
			else xMov+=dcc;
		}
		if(kM.kA) {
			if(onBlock()||onEnt())xMov-=acc;
			else xMov-=dcc;
		}
		if((kM.kD && kM.kA)||(!kM.kD && !kM.kA)) {
			if(xMov>acc) {
				if(onBlock()||onEnt())xMov-=acc;
				else xMov-=dcc;
			}else if(xMov<-acc) {
				if(onBlock()||onEnt())xMov+=acc;
				else xMov+=dcc;
			}else {
				xMov=0;
			}
		}
		if(xMov>spd)xMov=spd;
		else if(xMov<-spd)xMov=-spd;
		
		if(handler.debug) {
			if(kM.kI) {
				y-=handler.drag+2;
				yMov=handler.drag;
			}
			if(kM.kK) {
				y+=2;
			}
			if(kM.kJ) {
				x-=2;
			}
			if(kM.kL) {
				x+=2;
			}
			
			if(kM.kSP)run=1;
			
			if(kM.k0) {
				layer=0;
			}else if(kM.k1) {
				layer=1;
			}else if(kM.k2) {
				layer=2;
			}else if(kM.k3) {
				layer=3;
			}else if(kM.k4) {
				layer=4;
			}
		}
		
		if(onBlock()||onEnt())yMov=0;
		if(kM.justPressed(KeyEvent.VK_W)) {
			//actBlock
			if(delay<=0)
				if(Handler.action) {
					if(Handler.actionB.action("w")) {delay=30;return;}
				}
				
			if(Handler.debug)yMov=-10;
			else {
				if(onBlock()||onEnt()) {yMov=-10;}
			}
		}else if(yMov<Handler.drag) {
			yMov++;
		}
		if(kM.justPressed(KeyEvent.VK_S) && delay<=0) {
			//actBlock
			if(Handler.action) {
				if(Handler.actionB.action("s")){delay=30;return;}
			}
		}
		if(delay<=0) {
			if(kM.kD) {
				//actBlock
				if(Handler.action) {
					if(Handler.actionB.action("d")){delay=30;return;}
				}
			}
			if(kM.kA) {
				//actBlock
				if(Handler.action) {
					if(Handler.actionB.action("a")){delay=30;return;}
				}
			}
		}else {
			delay--;
		}
	}

	@Override
	public void render(Graphics g,float cx,float cy) {
		g.setColor(Color.blue);
		g.drawRect((int)(x+cx)-1, (int)(y+cy)-1, bounds.width+1, bounds.height+1);
		float ex=(x+width/2)+cx,wy=(y+height/2)+cy;
		g.drawString(Integer.toString(this.layer), (int)ex-3, (int)wy+5);
		
		g.setColor(new Color(clor,clor,255,200));
		g.fillRect((int)(x+bounds.x+cx), (int)(y+bounds.y+cy), bounds.width, bounds.height);
	}

	@Override
	public void die() {
		System.out.println("Player died.");
	}
	public boolean isPlayer() {return true;}
	public void setRun(int run) {this.run=run;}
}
