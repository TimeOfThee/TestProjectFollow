package world.layouts;

import blocks.ActionBlock;
import blocks.BaseBlock;
import blocks.Door;
import blocks.GBlock;
import blocks.Stair;
import entities.BaseCreature;
import entities.Box;
import logic.Handler;
import states.GState;
import states.GameS;
import world.Land;
import world.Layout;

public class LandAssets {
	
	private Handler handler;
	int pwi,phi;
	
	public LandAssets(Handler handler) {
		this.handler=handler;

		pwi=(int)handler.getPlayer().getWidth()-1;
		phi=(int)handler.getPlayer().getHeight()-1;
		init();//go into Game when changing start lands
	}
	
	public Land lvl0;
	public Land station;
	
	public void initT() {//size= 1000 600
		lvl0 =new Land(handler,new Blank(handler));
		lvl0.getLayout().spawnPlayer(300,400);
	//room 0
		lvl0.getRooms().get(0).addBlock(new BaseBlock(handler,-1,550,1001,50));
		lvl0.getRooms().get(0).addBlock(new BaseBlock(handler,250,500,150,50));
		lvl0.getRooms().get(0).addBlock(new BaseBlock(handler,920,525,81,25));
		
		lvl0.getRooms().get(0).addBlock(new BaseBlock(handler,320,200,150,50));
		lvl0.getRooms().get(0).addBlock(new BaseBlock(handler,320,150,70,50,0));
		lvl0.getRooms().get(0).addBlock(new BaseBlock(handler,400,150,70,50,4));
		
		//ents
		lvl0.getRooms().get(0).addEntity(new BaseCreature(handler, 640, 400, 50, 50));
		lvl0.getRooms().get(0).addEntity(new BaseCreature(handler, 500, 350, 100, 100));
	//room 1
		lvl0.getRooms().add(new Blank2(handler));
		lvl0.getRooms().get(1).addBlock(new BaseBlock(handler,-1,525,201,25));
		lvl0.getRooms().get(1).addBlock(new BaseBlock(handler,-1,550,201,50));
		lvl0.getRooms().get(1).addBlock(new BaseBlock(handler,200,525,1300,125));
		
		lvl0.getRooms().get(1).addBlock(new BaseBlock(handler,200,500,300,25));
		lvl0.getRooms().get(1).addBlock(new BaseBlock(handler,500,475,500,50));
		lvl0.getRooms().get(1).addBlock(new BaseBlock(handler,1000,510,501,15));
		
		lvl0.getRooms().get(1).addEntity(new Box(handler,530,425,30,30));
	//room2
		lvl0.getRooms().add(new Blank3(handler));
		
		lvl0.getRooms().get(2).addBlock(new BaseBlock(handler,350,550,149,50));
		lvl0.getRooms().get(2).addBlock(new BaseBlock(handler,0,510,200,15));
		lvl0.getRooms().get(2).addBlock(new BaseBlock(handler,0,525,350,75));
	//room3
		lvl0.getRooms().add(new Blank(handler));
		
		lvl0.getRooms().get(3).addBlock(new BaseBlock(handler,0,400,400,50));
		lvl0.getRooms().get(3).addBlock(new BaseBlock(handler,600,400,400,50));
		
	//connections up do le ri
		lvl0.getRooms().get(0).setConnection(lvl0.getRooms().get(3), null, lvl0.getRooms().get(2), lvl0.getRooms().get(1));
		lvl0.getRooms().get(1).setConnection(null, null, lvl0.getRooms().get(0), lvl0.getRooms().get(2));
		lvl0.getRooms().get(2).setConnection(null, null, lvl0.getRooms().get(1), lvl0.getRooms().get(0));
		lvl0.getRooms().get(3).setConnection(null, lvl0.getRooms().get(0), lvl0.getRooms().get(3), lvl0.getRooms().get(3));
	}
	
	public void init() {
		station =new Land(handler,new SstationR3(handler,1000,600));//size 1000 600
		station.getLayout().spawnPlayer(300,400);
		Layout r0=station.getRooms().get(0);
		r0.setDisGround(0, false);//right?
		r0.setDisGround(380, true);//right?
	//r0
		//blocks
		r0.addBlock(new BaseBlock(handler,0,500,610,5,2));
		r0.addBlock(new BaseBlock(handler,850,420,150,5,2));
		r0.addBlock(new BaseBlock(handler,850,420,150,5,1));
		
		mstair(r0,"r",610,500,30,10,8,2);
		
		r0.addBlock(new BaseBlock(handler,290,340,240,5,1));
		r0.addBlock(new BaseBlock(handler,290,340,240,5,0));
		mpost(r0, 290, 340, 2);
		r0.addBlock(new BaseBlock(handler,290,280,10,60,1,Handler.debug,0));
		r0.addBlock(new BaseBlock(handler,290,280,10,60,0,Handler.debug,0));
		
		mstair(r0,"l",850,420,40,10,8,1);
		
		r0.addBlock(new BaseBlock(handler,820,220,180,5,0));
		
		mstair(r0,"r",460,340,30,10,12,0);
		
		r0.addBlock(new BaseBlock(handler,980,0,20,20,0));
		r0.addBlock(new BaseBlock(handler,995,225,5,195,2));
		r0.addBlock(new BaseBlock(handler,995,225,5,195,1));
		//togA
		r0.addTogA(850+pwi,320,145-pwi*2,100,2,1);
		r0.addTogA(300+pwi,240,160-pwi*2,100,1,0);
		r0.addTogA(980,120,20,100,0);
	//r1----------------------------------------------------------------------------
		station.getRooms().add(new SstationR1(handler));
		Layout r1=station.getRooms().get(1);//1500 1000
		r1.setDisGround(20, false);//right?
		r1.setDisGround(20, true);//right?
		
		//blocks
		r1.addBlock(new BaseBlock(handler,0,780,1500,20,-1));
		r1.addBlock(new BaseBlock(handler,0,0,20,580,-1));
		r1.addBlock(new BaseBlock(handler,1480,0,20,580,-1));
		
		mstair(r1,"l",800,780,20,10,22,1);
		r1.addBlock(new BaseBlock(handler,20,560,340,5,1));
		r1.addBlock(new BaseBlock(handler,20,560,980,5,0));
		mpost(r1,990,560,0);
		r1.addBlock(new BaseBlock(handler,990,500,10,60,0,Handler.debug,0));
		
		//Gblocks
		r1.addBlock(new GBlock(handler,1150,740,200,40,1));
		r1.addBlock(new GBlock(handler,560,420,300,140,1));
		r1.addBlock(new GBlock(handler,630,480,160,60,1));
		
		//togA
		r1.addBlock(new ActionBlock(handler,805,680,195,100,1,new int[] {2,1}));
		
		r1.addTogA(0, 680, 20, 100, 2);
		r1.addTogA(20+pwi, 460, 360-pwi*2, 100, 1, 0);
		r1.addTogA(1480, 680, 20, 100, 2);
	//r2----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,3000,1000));
		Layout r2=station.getRooms().get(2);//3000,1000
		r2.setDisGround(180, false);//right?
		r2.setDisGround(0, true);//right?
		
		//blocks
		r2.addBlock(new Stair(handler,940,900,260,10,-1));
		r2.addBlock(new BaseBlock(handler,1200,910,520,5,-1));
		r2.addBlock(new Stair(handler,1720,900,1280,10,-1));
		
		mstair(r2, "l", 940, 900, 80, 10, 8, -1);
		r2.addBlock(new BaseBlock(handler,0,820,300,5,-1));
		r2.addBlock(new BaseBlock(handler,0,0,10,620,-1));
		
		//GBlocks
			//building01
		r2.addBlock(new BaseBlock(handler,1980,280,580,10,0,true,2));
		r2.addBlock(new GBlock(handler,1980,280,580,470,1,false));
		r2.addBlock(new GBlock(handler,1980,870,440,30,1,true));
		r2.addBlock(new GBlock(handler,2300,750,120,120,1,true));
		r2.addBlock(new GBlock(handler,2500,750,60,150,1,true));
		r2.addBlock(new GBlock(handler,2420,750,80,30,1,true));
		r2.addBlock(new ActionBlock(handler,2420+pwi/2,780,80-pwi,120,new int[] {2,1}));//door
		r2.addBlock(new GBlock(handler,1980,750,320,120,1));
		
		r2.addBlock(new Stair(handler,1995,880,345,20,1,Handler.debug));
		r2.addBlock(new BaseBlock(handler,1980,760,5,140,1,Handler.debug,0));
		r2.addBlock(new BaseBlock(handler,2555,800,5,100,1,Handler.debug,0));
		
		r2.addBlock(new Stair(handler,2500,270,80,10,0));
		r2.addBlock(new Stair(handler,2560,260,40,10,0));
		r2.addBlock(new Stair(handler,2590,250,10,10,0));
		
		mpost(r2, 1980, 280, 0);
		r2.addBlock(new BaseBlock(handler, 1980, 220, 10, 60, 0, Handler.debug, 0));
			//building02
		r2.addBlock(new Stair(handler,2600,240,580,10,0));
		r2.addBlock(new GBlock(handler,2600,240,580,660,1));
		
			//lamps
		mlamp(r2,1770,900,1);
		mlamp(r2,1120,900,1);
		
		//togA
		r2.addTogA(1770, 800, 30, 100, 2);
		r2.addTogA(1120, 800, 30, 100, 2);
	//r3----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,3000,1000));
		Layout r3=station.getRooms().get(3);//3000,1000
		r3.setDisGround(0, false);//right?
		r3.setDisGround(0, true);//right?
		
		//blocks
		r3.addBlock(new Stair(handler,0,900,2820,10,-1));			
		r3.addBlock(new BaseBlock(handler,2820,910,180,5,-1));
		mlamp(r3, 2740, 900, 0);
		//building 1
		r3.addBlock(new BaseBlock(handler,600,250,20,650,0));
		r3.addBlock(new GBlock(handler,0,240,620,660,1));
		r3.addBlock(new BaseBlock(handler,0,240,620,10,0,true,1));//run
		//building 2
		r3.addBlock(new BaseBlock(handler,800,260,20,640,1));
		r3.addBlock(new BaseBlock(handler,800,260,20,640,0));
		r3.addBlock(new BaseBlock(handler,1780,250,20,650,1));
		r3.addBlock(new BaseBlock(handler,1780,250,20,650,0));
		
		r3.addBlock(new BaseBlock(handler,820,740,260,10,1));
		mstair(r3, "l", 1400, 900, 20, 10, 16, 1);
		r3.addBlock(new BaseBlock(handler,820,660,80,80,1));
		r3.addBlock(new BaseBlock(handler,900,690,50,50,1));
		r3.addBlock(new ActionBlock(handler,1400+pwi/2,840,380-pwi,60,new int[] {2,1}));
		
		r3.addBlock(new BaseBlock(handler,960,610,820,10,1));
		
		r3.addBlock(new BaseBlock(handler,800,250,700,10,0,true,1));//run
		mstair(r3, "r", 1560-16,610,16,10, 11, 1);
		r3.addBlock(new BaseBlock(handler,1720,500,60,10,-1));
		mstair(r3, "l", 1720,500,16,10, 10, 0);
		r3.addBlock(new BaseBlock(handler,1500,400,60,10,-1));
		r3.addBlock(new BaseBlock(handler,1490,260,10,140,-1));
		mstair(r3, "r", 1560,400,16,10, 10, 1);
		r3.addBlock(new BaseBlock(handler,1720,300,60,10,-1));
		mstair(r3, "l", 1720,300,16,10, 5, 0);
		r3.addBlock(new BaseBlock(handler,1500,250,140,10,0));
		
		r3.addBlock(new GBlock(handler,1600,250,80,360,0,true));
		
		r3.addBlock(new GBlock(handler,800,250,1000,650,1));
		
		//building 3
		r3.addBlock(new GBlock(handler,1800,750,700,150,0));
		r3.addBlock(new GBlock(handler,2500,720,60,180,0));
		r3.addBlock(new GBlock(handler,2510,680,40,40,0));
		//togA
		r3.addTogA(620,840,180,60, 2);
		r3.addTogA(1800,760,700,1, 2);
		r3.addTogA(1720+pwi,440,60-pwi*2,60, 1,0);
		r3.addTogA(1500+pwi,340,60-pwi*2,60, 0,1);
		r3.addTogA(1720+pwi,240,60-pwi*2,60, 1,0);
	//r4----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,3000,1000));
		Layout r4=station.getRooms().get(4);//3000,1000
		r4.setDisGround(0, false);//right?
		r4.setDisGround(0, true);//right?
		
		r4.addBlock(new Stair(handler,180,900,2820,10,-1));			
		r4.addBlock(new BaseBlock(handler,0,910,180,5,-1));
		mlamp(r4, 230, 900, 0);
		
		//building 1
		r4.addBlock(new GBlock(handler,900,720,60,180,1));
		r4.addBlock(new GBlock(handler,900+10,680,40,40,1));
		r4.addBlock(new GBlock(handler,960,750,1000,150,1));
		r4.addBlock(new GBlock(handler,1960,720,60,180,1));
		r4.addBlock(new GBlock(handler,1960+10,680,40,40,1));
		
		r4.addBlock(new GBlock(handler,2220,720,60,180,1));
		r4.addBlock(new GBlock(handler,2220+10,680,40,40,1));
		r4.addBlock(new GBlock(handler,2280,750,720,150,1));
		r4.addBlock(new GBlock(handler,3280,720,60,180,1));
		r4.addBlock(new GBlock(handler,3280+10,680,40,40,1));
			//back
		r4.addBlock(new GBlock(handler,2040,680,160,200,0));
		
		r4.addBlock(new GBlock(handler,1260,620,600,100,0));
		r4.addBlock(new GBlock(handler,1260,640,140,40,0));
		r4.addBlock(new GBlock(handler,1720,640,140,40,0));
		r4.addBlock(new GBlock(handler,1440,640,240,40,0));
		
		r4.addBlock(new BaseBlock(handler,1260,700,600,10,0));
		r4.addBlock(new BaseBlock(handler,1860,620,10,80,0,Handler.debug,0));
		r4.addBlock(new BaseBlock(handler,1250,620,10,80,0,Handler.debug,0));
		r4.addBlock(new BaseBlock(handler,1260,610,600,10,0,Handler.debug,0));
	//r5----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,1500,1000));
		Layout r5=station.getRooms().get(5);//1500,1000
		r5.setDisGround(0, false);//right?
		r5.setDisGround(160, true);//right?
		
		//---------------------------------------------------------------------------------debug shortcut
		//station.travelTo(r5);
		//r5.spawnPlayer(740,845);
		
		r5.addBlock(new Stair(handler,180,900,1320,10,-1));			
		r5.addBlock(new BaseBlock(handler,0,910,180,5,-1));
	//building 1
		r5.addBlock(new GBlock(handler,440,720,60,180,3));
		r5.addBlock(new GBlock(handler,440+10,680,40,40,3));

		r5.addBlock(new BaseBlock(handler,1400,840,100,60,-1));
		mstair(r5, "dl", 1400, 840, 40, 10, 6, 2);
		r5.addBlock(new BaseBlock(handler,1480,0,20,660,-1));
	//r6----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,2000,800));
		Layout r6=station.getRooms().get(6);//2000,800
		r6.setDisGround(20, false);//right?
		r6.setDisGround(0, true);//right?
		
		r6.addBlock(new BaseBlock(handler, 0, 780, 2000, 5,-1));
		r6.addBlock(new BaseBlock(handler, 0,500,600,20,-1));
		r6.addBlock(new BaseBlock(handler, 0,520,20,80,-1));
		r6.addBlock(new BaseBlock(handler, 580,0,20,500,-1));

		r6.addBlock(new Stair(handler, 720,760,1280,20,-1));
		r6.addBlock(new Stair(handler, 700,770,20,10,-1));
		
		r6.addBlock(new BaseBlock(handler, 600,440,200,10,-1));
		mstair(r6, "dr", 800, 440, 20, 10, 18, 1);
		r6.addBlock(new BaseBlock(handler, 1160,620,280,10,-1));
		mstair(r6,"dl",1160,620,20,10,14,2);
		mstair(r6,"dr",1440,620,20,10,14,2);
		//back
		r6.addBlock(new BaseBlock(handler, 800,440,900,10,0));
		r6.addBlock(new GBlock(handler, 1100,300,20,140,0));
		r6.addBlock(new GBlock(handler, 1220,300,20,140,0));
		r6.addBlock(new GBlock(handler, 1120,300,100,10,0));
		r6.addBlock(new Stair(handler, 1700,430,100,10,0));
		r6.addBlock(new Stair(handler, 1800,420,200,10,0));
		
		//forth
		r6.addBlock(new GBlock(handler, 1920,0,80,600,4,!Handler.debug));
		
		r6.addTogA(540,680,20,100, 2);
		r6.addTogA(620,680,20,100, 3);
		
		r6.addBlock(new ActionBlock(handler,1160+pwi,560,220-pwi*2,60,1,new int[] {2,1}));
		r6.addTogA(600+pwi,380,220-pwi*2,60, 1,0);

		r6.addTogA(1420,560,20,60, 2);
		
		r6.addBlock(new ActionBlock(handler,720+pwi/2,680,160-pwi,80,1,new int[] {3,2}));
		r6.addBlock(new ActionBlock(handler,1720+pwi/2,680,160-pwi,80,1,new int[] {3,2}));
		
	//r7----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,1000,600));
		Layout r7=station.getRooms().get(7);//1000,600
		r7.setDisGround(0, false);//right?
		r7.setDisGround(0, true);//right?	

		r7.addBlock(new BaseBlock(handler, 0,590,800,10,-1));
		

		r7.addBlock(new BaseBlock(handler, 0,450,200,10,-1));
		r7.addBlock(new BaseBlock(handler, 800,110,10,480,-1));
		r7.addBlock(new BaseBlock(handler, 190,110,10,340,-1));
		r7.addBlock(new BaseBlock(handler, 200,100,600,10,-1));
		
		r7.addBlock(new GBlock(handler, 720,550,60,40,0));
		r7.addBlock(new GBlock(handler, 660,570,40,20,0));
		r7.addBlock(new GBlock(handler, 660,520,10,50,0));
		
		for(int b=460;b>299;b-=160) {
			r7.addBlock(new GBlock(handler, b,360,160,230,0));
			for(int a=520;a>369;a-=50) {
				r7.addBlock(new GBlock(handler, b+10,a,140,40,0));
			}
		}
		r7.addTogA(100,520,20,70, 0);
		r7.addTogA(180,520,20,70, 2);
	//r8----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,1000,600));
		Layout r8=station.getRooms().get(8);//1000,600
		r8.setDisGround(20, false);//right?
		r8.setDisGround(0, true);//right?	
	
		r8.addBlock(new BaseBlock(handler, 0,590,940,10,-1));
		
		r8.addBlock(new BaseBlock(handler, 0,450,100,10,-1));
		r8.addBlock(new BaseBlock(handler, 100,100,20,360,-1));
		r8.addBlock(new BaseBlock(handler, 120,80,820,20,-1));
		r8.addBlock(new BaseBlock(handler, 940,100,20,490,-1));

		r8.addBlock(new GBlock(handler, 600,400,120,160,0));
		
		r8.addBlock(new Stair(handler,560,560,200,30,-1));
		r8.addBlock(new Stair(handler,520,570,40,20,-1));
		r8.addBlock(new Stair(handler,760,570,40,20,-1));
		r8.addBlock(new Stair(handler,480,580,40,10,-1));
		r8.addBlock(new Stair(handler,800,580,40,10,-1));

		r8.addTogA(100,520,20,70, 0);
		r8.addTogA(180,520,20,70, 2);
		

	//r9----------------------------------------------------------------------------
		station.getRooms().add(new SstationR3(handler,1000,600));
		Layout r9=station.getRooms().get(9);//1000,600
		r9.setDisGround(0, false);//right?
		r9.setDisGround(0, true);//right?
		
		r9.addBlock(new BaseBlock(handler, 0, 560, 860, 5,-1));
		r9.addBlock(new BaseBlock(handler, 0,220,1000,10,0));

		r9.addBlock(new BaseBlock(handler, 860,230,10,330,3));
		r9.addBlock(new GBlock(handler, 800,440,60,120,1));
		r9.addBlock(new GBlock(handler, 560,510,240,50,1));
		r9.addBlock(new GBlock(handler, 480,500,80,60,1));
		r9.addBlock(new GBlock(handler, 580,430,200,40,1));
		
		r9.addBlock(new GBlock(handler,0,230,860,170,2));
		
		r9.addTogA(60,500,20,60, 3);
	//connections updoleri
		
		//make door r3->r4
		r3.addBlock(new Door(handler,2100,840,640,60, 2, r4, "w",260,845,1,false));//go to r4, activate with w, spawn at doorOri, 
																					//reversed? 0=no,1=X,2=Y,3=XY
		//r4->r3
		r4.addBlock(new Door(handler,260,840,640,60, 2, r3, "w",2100,845,1,false));
		//r4<->r5
		r4.addBlock(new Door(handler,2020,840,200,60, 2, r5, "w",240,845,0,false));
		r5.addBlock(new Door(handler,240,840,200,60, 2, r4, "w",2020,845,0,false));
		
		//size door r6 <-> r4
		r6.addBlock(new Door(handler,1120,380,100,60, 2, r4, "w",1810,658,0.8f,true));
		r4.addBlock(new Door(handler,1855,660,5,40, 2, r6, "d",1120,380,1.25f,true));
		
		//size door r4 <-> r7,r8
		r4.addBlock(new Door(handler,1300,660,20,40, 2, r7, "w",30,538,1.25f,true));//...scale by?
		r7.addBlock(new Door(handler,0,520,5,70, 2, r4, "a",1300,658,0.8f,true));
		r4.addBlock(new Door(handler,1560,660,20,40, 2, r8, "w",30,538,1.25f,true));
		r8.addBlock(new Door(handler,0,520,5,70, 2, r4, "a",1560,658,0.8f,true));
		
		r0.setConnection(null, null, null, r1);
		r1.setConnection(null, null, r0, r2);
		r2.setConnection(null, null, r1, r3);
		r3.setConnection(null, null, r2, null);
		
		r5.setConnection(null, null, null, r6);
		r6.setConnection(null, null, r5, r9);
		
		r9.setConnection(null, null, r6, null);
	}
	/** 
	 * 
	 * @param r oom
	 * @param dir l,r,dl,dr
	 * @param x y sx sy
	 * @param length how many
	 * @param layer 
	 */
	private void mstair(Layout r,String dir, int x, int y, int sx, int sy, int length,int layer) {
		
		if(dir=="l") {
			x-=sx;
			y-=sy;
			for(int a=0;a<length;a++) {
				r.addBlock(new Stair(handler,x-(a*sx),y-(a*sy),sx,sy,layer));
			}
		}else if(dir=="dr"){
			for(int a=0;a<length;a++) {
				r.addBlock(new Stair(handler,x+(a*sx),y+(a*sy),sx,sy,layer));
			}
		}else if(dir=="dl") {
			x-=sx;
			for(int a=0;a<length;a++) {
				r.addBlock(new Stair(handler,x-(a*sx),y+(a*sy),sx,sy,layer));
			}
		}else {
			y-=sy;
			for(int a=0;a<length;a++) {
				r.addBlock(new Stair(handler,x+(a*sx),y-(a*sy),sx,sy,layer));
			}
		}
	}
	private void mlamp(Layout r,int x,int y,int layer) {
		y-=30;
		r.addBlock(new GBlock(handler,x,y,30,30,1));
		r.addBlock(new GBlock(handler,x+5,y-290,20,290,1));
	}
	private void mpost(Layout r,int x,int y, int layer) {
		r.addBlock(new GBlock(handler,x+2,y-50,6,50));
		r.addBlock(new GBlock(handler,x,y-60,10,10));
	}
}
