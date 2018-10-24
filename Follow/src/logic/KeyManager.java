package logic;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys,pressed,nopress;
	public boolean kW,kA,kS,kD,kUP,kDO,kLE,kRI,kSP,kEN,kI,kK,kJ,kL;
	public boolean k0,k1,k2,k3,k4;
	
	public KeyManager(){
		keys=new boolean[256];
		pressed=new boolean[keys.length];
		nopress=new boolean[keys.length];
		update();
	}
	public void update() {
		for(int i=0;i<keys.length;i++) {
			if(nopress[i] && !keys[i])
				nopress[i]=false;
			else if(pressed[i]) {
				nopress[i]=true;
				pressed[i]=false;
			}
			if(!nopress[i] && keys[i]) {
				pressed[i]=true;
			}
		}
		
		kW=keys[KeyEvent.VK_W];
		kS=keys[KeyEvent.VK_S];
		kA=keys[KeyEvent.VK_A];
		kD=keys[KeyEvent.VK_D];
		kUP=keys[KeyEvent.VK_UP];
		kDO=keys[KeyEvent.VK_DOWN];
		kLE=keys[KeyEvent.VK_LEFT];
		kRI=keys[KeyEvent.VK_RIGHT];
		kSP=keys[KeyEvent.VK_SPACE];
		kEN=keys[KeyEvent.VK_ENTER];
		kI=keys[KeyEvent.VK_I];
		kK=keys[KeyEvent.VK_K];
		kJ=keys[KeyEvent.VK_J];
		kL=keys[KeyEvent.VK_L];
		
		k0=keys[KeyEvent.VK_0];
		k1=keys[KeyEvent.VK_1];
		k2=keys[KeyEvent.VK_2];
		k3=keys[KeyEvent.VK_3];
		k4=keys[KeyEvent.VK_4];
	}
	public boolean justPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return pressed[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()<0||arg0.getKeyCode()>=keys.length)
			return;
		keys[arg0.getKeyCode()]=true;
		//System.out.println(arg0.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()<0||arg0.getKeyCode()>=keys.length)
			return;
		keys[arg0.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
