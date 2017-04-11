package net.connorgeorge.rpgproject.gameobjects.person.actor;

import net.connorgeorge.rpgproject.gameobjects.Direction;
import net.connorgeorge.rpgproject.gameobjects.GameMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class RandomAi extends EnemyActor
{
	public RandomAi(Vector2 position, GameMap gameMap, TextureRegion texture)
	{
		super(position, gameMap, 2f, texture);
		this.distancePerDelta = 2.5f;
	}

	@Override
	public void update(float delta)
	{
		moveTowardsTarget(delta);
		moveInDirection(Direction.getRandomDirection());
	}
}