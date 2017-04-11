package net.connorgeorge.rpgproject.gameobjects.person.actor;

import net.connorgeorge.rpgproject.gameobjects.GameMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class EnemyActor extends Actor
{
	private float detectionRadius;
	
	public EnemyActor(Vector2 position, GameMap gameMap, float detectionRadius, TextureRegion texture)
	{
		super(position, gameMap);
		this.detectionRadius = detectionRadius;
		this.texture = texture;
	}
	
	public boolean isDetected(Actor a)
	{
		return position.dst(a.getPosition()) < detectionRadius;
	}
}
