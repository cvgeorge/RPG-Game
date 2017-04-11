package net.connorgeorge.rpgproject.gameobjects.battle.attacks;

import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.gameobjects.battle.AttackRunner;
import net.connorgeorge.rpgproject.gameobjects.battle.animations.AttackAnimation;
import net.connorgeorge.rpgproject.gameobjects.battle.animations.LaserAnimation;
import net.connorgeorge.rpgproject.gameobjects.person.Person;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpecificAttack implements Attack {
	private AttackAnimation attackAnimation;
	private int manaDrain, healthDamage;
	public AttackSound aSound;

	public SpecificAttack(int mana, int health)
	{
		this.attackAnimation = new LaserAnimation();
		manaDrain = mana;
		healthDamage = health;
	}
	
	public int getManaDrain()
	{
		return manaDrain;
	}
	
	@Override
	public void execute(Person attacker, Person victim) {
		Stats victimStats = victim.getStats();
		
		attacker.getStats().changeMp(-1 * manaDrain);
		victimStats.changeHp(-1 * healthDamage);
		victimStats.changeMp(5);
		//attacker.getStats().changeMp(-10);
		//victimStats.changeHp(-10);
	}
	
	@Override
	public TextureRegion getTexture(int index)
	{
		switch(index)
		{
		case 0:
			return TextureManager.get().getWeaponTexture1();	
		case 1:
			return TextureManager.get().getWeaponTexture2();
		case 2:
			return TextureManager.get().getWeaponTexture3();
		default:
			return TextureManager.get().getWeaponTexture4();
		}
	}
	
	@Override
	public AttackAnimation getAttackAnimation(Vector2 start, Vector2 end, AttackRunner attackRunner)
	{
		attackAnimation.initialize(start, end, attackRunner);
		return attackAnimation;
	}
}