package main;//null is amazing

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import entities.Player;
import graphics.Display;
import logic.GameCamera;
import logic.Handler;
import logic.KeyManager;
import logic.MouseManager;
import states.GState;
import states.GameS;
import world.Land;
import world.layouts.Blank;

public class Game implements Runnable{
	
	private Display display;
	private int width,height;
	public String title;
	public static int psx=30,psy=50;
	
	private boolean running=false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private static GState curr,gameS;
	private Player player;
	
	private GameCamera gamC;
	
	private Handler handler;
	
	public Game(String title,int width,int height) {
		this.width=width;
		this.height=height;
		this.title=title;
		
		keyManager=new KeyManager();
		mouseManager=new MouseManager();
	}
	private void init() {
		display=new Display(title,width,height);
		display.getJFrame().addKeyListener(keyManager);
		display.getJFrame().addMouseListener(mouseManager);
		display.getJFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		handler=new Handler(this);
		this.player=new Player(handler, 500, 300, psx, psy);
		handler.loadAssets();
		//												V when switching start lands, other part in LandAssets
		gameS=new GameS(handler,handler.getLandscape().station);
		curr=gameS;
	}
	private void update() {//--------------------------------------------------------
		keyManager.update();//<Important
		handler.update();
	}
	private void render() {//------------------------------------------------------------------------
		bs=display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;//return if you don't want errors
		}
		g=bs.getDrawGraphics();
		
		g.clearRect(0,0,width,height);
		//System.out.println(curr.getLand().getLayout().getGC().getCX()+" "+curr.getLand().getLayout().getGC().getCY());
		handler.render(g, -curr.getLand().getLayout().getGC().getCX() +curr.getLand().getLayout().getXScale(),
				-curr.getLand().getLayout().getGC().getCY() +curr.getLand().getLayout().getYScale());
		
		bs.show();//reveal the buffers
		g.dispose();//gets rid of the graphics object properly
	}
	public void run() {
		
		init();
		//this gets complicated
		int fps=60;
		double timePerTick=1000000000/fps;
		double delta=0;
		long now;
		long lastTime=System.nanoTime();
		long timer=0;
		int ticks=0;
		//this gets complicated
		while(running) {
			now=System.nanoTime();
			delta+=(now-lastTime)/timePerTick;
			timer+=now-lastTime;
			lastTime=now;
			
			if(delta>=1) {
				update();//----------------------------------
				render();
				ticks++;
				delta--;
			}
			if (timer>1000000000) {
				//System.out.println(ticks);
				ticks=0;
				timer=0;
			}
		}
		
		stop();
		
	}
	//use synchronized for interacting with threads directly
	public synchronized void start() {
		if(running)return;//stops the game from running if already running
		running=true;
		thread=new Thread(this);
		thread.start();//.start will call run()
	}
	public synchronized void stop() {
		if(!running)return;//stops the game from stopping if already stopped
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public KeyManager getKeyManager() {return keyManager;}
	public MouseManager getMouseManager() {return mouseManager;}
	public GameCamera getGamC() {return gamC;}
	//public GameCamera getGameCamera() {return gamecam;}
	
	public static GState getCurrState() {return curr;}
	
	public Player getPlayer() {return player;}
}
