package net.connorgeorge.rpgproject.gameobjects.battle;

import net.connorgeorge.rpgproject.gameobjects.battle.attacks.Attack;
import net.connorgeorge.rpgproject.gameobjects.person.Person;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;

public class AttackRunner
{
	private Attack attack;
	private Person attacker;
	private Person victim;
	
	public AttackRunner(Attack attack, Person attacker, Person victim)
	{
		super();
		this.attack = attack;
		this.attacker = attacker;
		this.victim = victim;
	}
	
	public boolean executeAttack()
	{
		if(attacker.getStats().getMp() > attack.getManaDrain())
		{
			attack.execute(attacker, victim);
			return true;
		}
		else
			return false;
	}
	
	public Attack getAttack()
	{
		return attack;
	}
	
	public void setAttack(Attack attack)
	{
		this.attack = attack;
	}
	
	public Person getAttacker()
	{
		return attacker;
	}
	
	public void setAttacker(Person attacker)
	{
		this.attacker = attacker;
	}
	
	public Person getVictim()
	{
		return victim;
	}
	
	public void setVictim(Person victim)
	{
		this.victim = victim;
	}
}
