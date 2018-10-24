package main;
import main.Game;

public class Launcher {
	
	public final static int wid=1000,hei=600;
	
	public static void main(String[] srgs) {
		Game game=new Game("Title",wid,hei);
		game.start();
	}
}