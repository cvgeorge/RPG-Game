package net.connorgeorge.rpgproject;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

//sets up things that are needed to run the project on a desktop environment
//could make another one of these for android, and other platforms
public class RPGProjectDesktop
{
	//48px per grid square, 16 horizontal squares 9 vertical squares. 16:9 hd ratio
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	public static void main(String[] args)
	{
		new LwjglApplication(new RPGProject(), "RPG Project", SCREEN_WIDTH, SCREEN_HEIGHT);
	}
}
