package net.connorgeorge.rpgproject.gameobjects.person;

import net.connorgeorge.rpgproject.gameobjects.person.actor.EnemyActor;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;


public class EnemyPerson extends Person
{
	public EnemyPerson(EnemyActor enemyActor, Stats stats)
	{
		super(enemyActor, stats);
	}
	
	@Override
	public EnemyActor getActor()
	{
		return (EnemyActor) actor;
	}
	
	public void setActor(EnemyActor actor)
	{
		this.actor = actor;
	}
}
