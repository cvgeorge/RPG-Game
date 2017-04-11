package net.connorgeorge.rpgproject.gameobjects.battle.attacks;

import net.connorgeorge.rpgproject.screens.GameFactory;

public class AttackFactory {
	public SpecificAttack generateAttack(int roughDamage, int roughCost, int modificationFactor)
	{
		int damageModifier, costModifier;
		
		damageModifier = GameFactory.generateRandomInteger(0, (int) (modificationFactor * .1 * roughDamage));
		costModifier = GameFactory.generateRandomInteger(0, (int) (modificationFactor * .1 * roughCost));
		
		damageModifier -= damageModifier/(((int) .2 * roughDamage) + 1);
		costModifier -= costModifier/(((int) .2 * roughCost) + 1);
		
		roughDamage += damageModifier;
		roughCost += costModifier;
		
		if(roughDamage < 0)			//This just ensures you don't heal your enemy
			roughDamage = 0;
		
		return new SpecificAttack(roughCost, roughDamage);
	}
}
