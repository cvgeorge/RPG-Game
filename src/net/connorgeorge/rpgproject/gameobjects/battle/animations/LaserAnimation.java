package net.connorgeorge.rpgproject.gameobjects.battle.animations;

import net.connorgeorge.rpgproject.gameobjects.battle.AttackRunner;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class LaserAnimation implements AttackAnimation
{
	private Vector2 start;
	private Vector2 end;
	private float timeElapsed;
	private final float MOVEPERDELTA = 4f;
	private Vector2 angleVector;
	private AttackRunner attackRunner;
	
	@Override
	public void initialize(Vector2 start, Vector2 end, AttackRunner attackRunner)
	{
		this.start = start.cpy().add(new Vector2(.5f, .5f));
		this.end = end.cpy().add(new Vector2(.5f, .5f));
		this.attackRunner = attackRunner;
		
		timeElapsed = 0;
		float yDif = end.y - start.y;
		float xDif = end.x - start.x;
		float angle = (float) Math.toDegrees(Math.atan2(yDif, xDif));
		angleVector = new Vector2(1,0);
		angleVector = angleVector.rotate(angle);
	}

	@Override
	public void animate(ShapeRenderer shapeRenderer, float delta)
	{
		timeElapsed += delta;
		float startDist = timeElapsed*MOVEPERDELTA;
		//float endDist = startDist+.5f;
		Vector2 startSpot = start.cpy().add(angleVector.cpy().scl(startDist));
		//Vector2 endSpot = start.cpy().add(angleVector.cpy().scl(endDist));
		
		shapeRenderer.setColor(1, 0, 1, 1);
		shapeRenderer.set(ShapeType.Filled);
		shapeRenderer.circle(startSpot.x, startSpot.y, .3f);
	}

	@Override
	public boolean isDone()
	{
		return timeElapsed*MOVEPERDELTA > start.dst(end)-.3f;
	}

	@Override
	public void executeAttack()
	{
		attackRunner.executeAttack();
	}

}
