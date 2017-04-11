package net.connorgeorge.rpgproject.gameobjects.battle;

import net.connorgeorge.rpgproject.gameobjects.battle.attacks.Attack;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.SpecificAttack;

public class AttackUnit
{
	public SpecificAttack attack;
	public int attacked;
	public int attacker;
	
	public AttackUnit(SpecificAttack attack, int attacked)
	{
		this.attack = attack;
		this.attacked = attacked;
	}
}
