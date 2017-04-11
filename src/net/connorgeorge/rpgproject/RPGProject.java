package net.connorgeorge.rpgproject;

import net.connorgeorge.rpgproject.screens.GameScreen;

import com.badlogic.gdx.Game;

//simply creates the project
public class RPGProject extends Game
{
	@Override
	public void create()
	{
		setScreen(new GameScreen());
	}
}
