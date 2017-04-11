package net.connorgeorge.rpgproject.gameobjects.person.stats;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import net.connorgeorge.rpgproject.SoundManager;
import net.connorgeorge.rpgproject.gameobjects.battle.AttackUnit;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.Attack;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.AttackFactory;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.AttackSound;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.SpecificAttack;

public class PlayerStats extends Stats
{
	public static final int ATTACKQUEUESIZE = 4;
	private Deque<SpecificAttack> attackList;
	private Deque<Integer> attackIndexes;
	private Deque<Integer> personAttackList;
	
	public PlayerStats(float hp, float mp, float strength)
	{
		super(hp, mp, strength, new LinkedList<SpecificAttack>());
		
		this.attackList = new LinkedList<SpecificAttack>();
		this.personAttackList = new LinkedList<Integer>();
		this.attackIndexes = new LinkedList<Integer>();
	
		//set default attacks here		
		setAttacks();
	}
	
	private void setAttacks()
	{
		AttackFactory AF = new AttackFactory();
		availableAttacks.add(AF.generateAttack(5, 5, 1));				//Low risk, low reward
		availableAttacks.get(0).aSound = new AttackSound()
		{
			@Override
			public void playAttackSound()
			{
				SoundManager.get().playLaser();
			}
		};
		availableAttacks.add(AF.generateAttack(20, 20, 1));				//More Powerful Low risk low reward
		availableAttacks.get(1).aSound = new AttackSound()
		{	
			@Override
			public void playAttackSound()
			{
				SoundManager.get().playBubbles();
			}
		};
		availableAttacks.add(AF.generateAttack(10, 10, 3));							//High risk high reward
		availableAttacks.get(2).aSound = new AttackSound()
		{
			@Override
			public void playAttackSound()
			{
				SoundManager.get().playGun();		
			}
		};
		availableAttacks.add(AF.generateAttack(30, 30, 10));							//VERY high risk, VERY high reward
		availableAttacks.get(3).aSound = new AttackSound()
		{
			@Override
			public void playAttackSound()
			{
				SoundManager.get().playMagic();		
			}
		};
	}
	
	public void pushAttack(int attackIndex, int enemyStatsIndex)
	{
		if(attackList.size() >= ATTACKQUEUESIZE)
			return;
		
		if(attackIndex >= availableAttacks.size())
			return;
		
		attackList.add(availableAttacks.get(attackIndex));
		attackIndexes.add(attackIndex);
		personAttackList.add(enemyStatsIndex);
	}
	
	public AttackUnit popAttack()
	{
		if(attackList.size() == 0)
			return null;
		
		attackIndexes.remove();
		return new AttackUnit(attackList.remove(), personAttackList.remove());
	}
	
	public void clearAttackQueue()
	{
		this.attackList.clear();
		this.attackIndexes.clear();
		this.personAttackList.clear(); 
	}
	
	public void removeTopAttack()
	{
		attackList.pollLast();
		personAttackList.pollLast();
		attackIndexes.pollLast();
	}
	
	public List<AttackUnit> getAttackUnitList()
	{
		List<AttackUnit> list = new LinkedList<AttackUnit>();
		
		for(int i = 0; i < attackList.size(); i++)
		{
			//bad practice but don't want to change design too much at this point
			SpecificAttack attack = ((LinkedList<SpecificAttack>)attackList).get(i);
			int enemy = ((LinkedList<Integer>)personAttackList).get(i);
			list.add(new AttackUnit(attack, enemy));
		}
		
		return list;
	}
	
	public List<Integer> getAttackIndexesList()
	{
		List<Integer> list = new LinkedList<Integer>();
		
		for(int i = 0; i < attackIndexes.size(); i++)
		{
			int index = ((LinkedList<Integer>)attackIndexes).get(i);
			list.add(index);
		}
		
		return list;
	}
}
