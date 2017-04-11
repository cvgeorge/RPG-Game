package net.connorgeorge.rpgproject.gameobjects.battle.attacks;

import net.connorgeorge.rpgproject.gameobjects.battle.AttackRunner;
import net.connorgeorge.rpgproject.gameobjects.battle.animations.AttackAnimation;
import net.connorgeorge.rpgproject.gameobjects.person.Person;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public interface Attack {
	public void execute(Person attacker, Person Victim);
	public TextureRegion getTexture(int index);
	public AttackAnimation getAttackAnimation(Vector2 start, Vector2 end, AttackRunner attackRunner);
	public int getManaDrain();
}