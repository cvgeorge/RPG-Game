package net.connorgeorge.rpgproject.gameobjects.collectible;

import net.connorgeorge.rpgproject.SoundManager;
import net.connorgeorge.rpgproject.gameobjects.person.Player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Collectible
{
	private TextureRegion texture;
	private Vector2 position;
	
	public Collectible(TextureRegion texture, Vector2 position)
	{
		this.texture = texture;
		this.position = position;
	}
	
	public abstract void execute(Player player);
	
	public boolean isCollision(Vector2 playerLocation)
	{
		if(position.epsilonEquals(playerLocation, .8f))
			SoundManager.get().playGet();
		return position.epsilonEquals(playerLocation, .8f);
	}

	public TextureRegion getTexture()
	{
		return texture;
	}

	public Vector2 getPosition()
	{
		return position;
	}
}
