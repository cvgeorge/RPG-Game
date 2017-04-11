package net.connorgeorge.rpgproject.gameobjects.battle;

import java.util.List;
import java.util.Random;

import net.connorgeorge.rpgproject.SoundManager;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.gameobjects.person.Person;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.view.StatsRenderer;

public class Battle {
	
	List<EnemyPerson> enemyList;
	Player player;
	private int attackListPosition;
	
	public Battle(Player hero, List<EnemyPerson> enemies)
	{
		this.player = hero;
		this.enemyList = enemies;
		this.attackListPosition = -1; //start with player attacking
		player.getStats().clearAttackQueue();
	}
	
	public List<EnemyPerson> getEnemies()
	{
		return enemyList;
	}
	
	public boolean fightBattle()					//This function is a high level control of the battle.  It returns true if the player wins, and false if they lose
	{
		BattleStatus state;
		
		while(true)									//This loop continues until the battle is ended and the function returns
		{
			doNextAttack();							//This is a single battle round.  It handles all the attacks for that round
			
			state = getBattleStatus();					//After fighting, this checks the battle status
			
			if(state != BattleStatus.ONGOING)							//If the fight is over, return something
			{
				if(state == BattleStatus.WIN)
					return true;
				else
					return false;
			}
		}
		
	}

	public void doNextAttack()						//This handles a single round of attacks from the player and all enemies
	{
		if(attackListPosition == -1)
		{
			//get player attack
			AttackUnit a = player.getStats().popAttack();
			
			if(a != null) //do nothing if no attack
			{
				EnemyPerson attacked = enemyList.get(a.attacked);
				//System.out.println(a.attack.)
				AttackRunner attackRenderer = new AttackRunner(a.attack, player, attacked);
				if(player.getStats().getMp() > a.attack.getManaDrain())
				{
					if(a.attack.aSound != null)
						a.attack.aSound.playAttackSound();
					StatsRenderer.get().addAttackAnimation(a.attack.getAttackAnimation(player.getActor().getPosition(), attacked.getActor().getPosition(), attackRenderer));
				}
			}
		}
		else
		{
			//enemyList.get(attackListPosition).getStats().chooseAttack().Execute(player);
			Person enemy = enemyList.get(attackListPosition);
			while(enemy.getStats().getHp() <= 0)
			{
				attackListPosition++;
				if(attackListPosition >= enemyList.size())
				{
					attackListPosition = -2;
					enemy = null;
					break;
				}
				enemy = enemyList.get(attackListPosition);
			}
			
			if(enemy != null)
			{
				AttackRunner attackRenderer = new AttackRunner(enemy.getStats().chooseAttack(), enemy, player);
				StatsRenderer.get().addAttackAnimation(enemy.getStats().chooseAttack().getAttackAnimation(enemy.getActor().getPosition(), player.getActor().getPosition(), attackRenderer));
				Random r = new Random();
				int num = r.nextInt(4);
				switch(num)
				{
				case 0:
					SoundManager.get().playBubbles();
					break;
				case 1:
					SoundManager.get().playGun();
					break;
				case 2:
					SoundManager.get().playLaser();
					break;
				case 3:
					SoundManager.get().playMagic();
					break;
				}
			}
			
		}
		
		attackListPosition++;
		
		if(attackListPosition >= enemyList.size())
		{
			attackListPosition = -1;
		}
	}
	
	public static enum BattleStatus
	{
		WIN, DEFEAT, ONGOING
	}
	
	public BattleStatus getBattleStatus()  						//This function returns a 1 if the player is victorious, -1 if the player is defeated, and 0 if the battle is still going
	{
		boolean deadEnemies = true;					//This is used to check if enemies are dead
		if(player.getStats().getHp() <= 0)						//If the player has 0 or less health, they are dead and they lose
			return BattleStatus.DEFEAT;					
		for(int i = 0; i < enemyList.size(); i++)	//This loops through all the enemies
		{
			if(enemyList.get(i).getStats().getHp() > 0)		//If any enemies are alive, it will get into this statement and change the flag
				deadEnemies = false;
		}
		if(deadEnemies)								//If there were no living enemies, the player wins
			return BattleStatus.WIN;
		return BattleStatus.ONGOING;									//Otherwise, the player is alive and at least one enemy is alive, so the battle continues
	}
}
