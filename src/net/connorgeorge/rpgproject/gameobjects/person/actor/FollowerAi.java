package net.connorgeorge.rpgproject.gameobjects.person.actor;

import net.connorgeorge.rpgproject.gameobjects.Direction;
import net.connorgeorge.rpgproject.gameobjects.GameMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class FollowerAi extends EnemyActor
{
	private Actor following;
	private float followDistance;
	
	public FollowerAi(Vector2 position, GameMap gameMap, Actor following, float d, TextureRegion texture)
	{
		super(position, gameMap, 2f, texture);
		this.following = following;
		this.followDistance = d;
		this.distancePerDelta = 8f;
	}

	@Override
	public void update(float delta)
	{
		if(followDistance < this.position.dst(following.getPosition()))
		{
			moveInDirection(Direction.getDirectionTowards(position, following.getPosition()));
		}
		
		moveTowardsTarget(delta);
	}
}
