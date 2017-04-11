package net.connorgeorge.rpgproject.gameobjects.person.actor;

import net.connorgeorge.rpgproject.gameobjects.GameMap;
import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.input.InputHandler;

import com.badlogic.gdx.math.Vector2;

public class PlayerActor extends Actor
{
	private InputHandler inputHandler;
	
	public PlayerActor(Vector2 position, GameMap gameMap)
	{
		super(position, gameMap);
		this.inputHandler = InputHandler.getInstance();
		this.texture = TextureManager.get().getPlayerTexture();
	}

	@Override
	public void update(float delta)
	{
		//will do nothing if we don't have target
		moveTowardsTarget(delta);
		//will do nothing if we DO have target
		moveInDirection(inputHandler.getCurrentDirection());
	}
}