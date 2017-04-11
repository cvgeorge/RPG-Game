package net.connorgeorge.rpgproject.gameobjects.displaymessage;

import net.connorgeorge.rpgproject.gameobjects.GameState;

public class NextLevelDisplayMessage extends DisplayMessage
{
	private GameState gameState;
	
	public NextLevelDisplayMessage(String message, float timeout, GameState gameState)
	{
		super(message, timeout);
		this.gameState = gameState;
	}
	
	@Override
	public void callOnComplete()
	{
		gameState.flagComplete();
	}
}
