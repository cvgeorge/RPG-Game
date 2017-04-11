package net.connorgeorge.rpgproject.gameobjects.person;

import java.util.List;

import net.connorgeorge.rpgproject.gameobjects.person.actor.Actor;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;

//this is a person in the game. Can be instantiated as a Player(THE player) or NPC for instance
public class Person
{
	protected Actor actor;
	protected Stats stats;
	protected List<Stats> enemies;
	
	public Person(Actor actor, Stats stats)
	{
		this.actor = actor;
		this.stats = stats;
		enemies = null;
	}
	
	//TODO need an abstract attack method or something
	
	public void update(float delta)
	{
		actor.update(delta);
	}
	
	public Actor getActor()
	{
		return actor;
	}
	
	public void setActor(Actor actor)
	{
		this.actor = actor;
	}
	
	public Stats getStats()
	{
		return stats;
	}
	
	public void setStats(Stats stats)
	{
		this.stats = stats;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actor == null) ? 0 : actor.hashCode());
		result = prime * result + ((enemies == null) ? 0 : enemies.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
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
		Person other = (Person) obj;
		if (actor == null)
		{
			if (other.actor != null)
				return false;
		} else if (!actor.equals(other.actor))
			return false;
		if (enemies == null)
		{
			if (other.enemies != null)
				return false;
		} else if (!enemies.equals(other.enemies))
			return false;
		if (stats == null)
		{
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		return true;
	}
}
