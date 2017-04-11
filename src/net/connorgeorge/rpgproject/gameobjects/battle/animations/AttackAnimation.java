package net.connorgeorge.rpgproject.gameobjects.battle.animations;

import net.connorgeorge.rpgproject.gameobjects.battle.AttackRunner;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public interface AttackAnimation
{
	public abstract void initialize(Vector2 start, Vector2 end, AttackRunner attackRunner);
	public abstract void animate(ShapeRenderer shapeRenderer, float delta);
	public boolean isDone();
	public void executeAttack();
}
