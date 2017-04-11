package net.connorgeorge.rpgproject.gameobjects.person.stats;

import java.util.List;

import net.connorgeorge.rpgproject.gameobjects.battle.attacks.Attack;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.SpecificAttack;

public class Stats //eventually make this abstract?
{
	//TODO: Use command pattern, make an attack abstract class that has execute method. Each stats
	//object should have a list of these, so the player/npc can attack other people
	//I'm thinking subclasses just implement how they decide to attack others. Player subclass uses input keys, npc does something randomly
	
	//battle/fight will be composed of a collection of stats objects
	
	protected float maxHp;
	protected float maxMp;
	protected float hp;
	protected float mp;
	protected float strength;
	protected List<SpecificAttack> availableAttacks;

	public Stats(float hp, float mp, float strength, List<SpecificAttack> avaliableAttacks)
	{
		this.hp = hp;
		this.mp = mp;
		this.maxHp = hp;
		this.maxMp = mp;
		this.strength = strength;
		this.availableAttacks = avaliableAttacks;
	}
	
	public Attack chooseAttack()  //This is NOT done.  I just did some dummy stuff so it would compile
	{
		Attack a = new SpecificAttack(10,10);
		return a;
	}

	public float getHp()
	{
		return hp;
	}
	
	public void setHp(float hp)
	{
		if(hp < 0)
			this.hp = 0;
		else if(hp > maxHp)
			this.hp = maxHp;
		else	
			this.hp = hp;
	}
	
	public void changeHp(float amount)
	{
		setHp(hp + amount);
	}
	
	public float getMp()
	{
		return mp;
	}
	
	public void changeMp(float amount)
	{
		setMp(mp + amount);
	}
	
	public void setMp(float mp)
	{
		if(mp < 0)
			this.mp = 0;
		else if(mp > maxMp)
			this.mp = maxMp;
		else
			this.mp = mp;
	}
	
	public float getStrength()
	{
		return strength;
	}
	
	public void setStrength(float strength)
	{
		this.strength = strength;
	}

	public float getMaxHp()
	{
		return maxHp;
	}

	public void setMaxHp(float maxHp)
	{
		this.maxHp = maxHp;
	}

	public float getMaxMp()
	{
		return maxMp;
	}

	public void setMaxMp(float maxMp)
	{
		this.maxMp = maxMp;
	}
	
	public List<SpecificAttack> getAvailableAttacks()
	{
		return availableAttacks;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((availableAttacks == null) ? 0 : availableAttacks.hashCode());
		result = prime * result + Float.floatToIntBits(hp);
		result = prime * result + Float.floatToIntBits(maxHp);
		result = prime * result + Float.floatToIntBits(maxMp);
		result = prime * result + Float.floatToIntBits(mp);
		result = prime * result + Float.floatToIntBits(strength);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stats other = (Stats) obj;
		if (availableAttacks == null)
		{
			if (other.availableAttacks != null)
				return false;
		} else if (!availableAttacks.equals(other.availableAttacks))
			return false;
		if (Float.floatToIntBits(hp) != Float.floatToIntBits(other.hp))
			return false;
		if (Float.floatToIntBits(maxHp) != Float.floatToIntBits(other.maxHp))
			return false;
		if (Float.floatToIntBits(maxMp) != Float.floatToIntBits(other.maxMp))
			return false;
		if (Float.floatToIntBits(mp) != Float.floatToIntBits(other.mp))
			return false;
		if (Float.floatToIntBits(strength) != Float
				.floatToIntBits(other.strength))
			return false;
		return true;
	}
}
