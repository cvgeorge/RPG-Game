package net.connorgeorge.rpgproject.screens;

import net.connorgeorge.rpgproject.gameobjects.GameState;
import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.gameobjects.person.actor.Actor;
import net.connorgeorge.rpgproject.gameobjects.person.actor.FollowerAi;
import net.connorgeorge.rpgproject.gameobjects.person.actor.RandomAi;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;

public class EnemyFactory {
	
	GameState gameState;
	
	public EnemyFactory(GameState gs)
	{
		gameState = gs;
	}
	
	public Stats generateStats(Enemy enemy)
	{
		float hp, mp, strength;
		switch(enemy)
		{
		case DEMON:
			hp = 75;
			break;
		case ZOMBIE:
			hp = 30;
			break;
		case GHOUL:
			hp = 15;
			break;
		case SKULL:
			hp = 5;
			break;
		case FOLLOWER:
			hp = 1;
			break;
		default: 
			hp = 1;
		}
		mp = 1000;
		strength = 1;
		
		return new Stats(hp, mp, strength, null);
	}
	
	public EnemyPerson generateEnemy(Enemy enemy)
	{
		EnemyPerson myEnemy;
		RandomAi RAI;
		switch(enemy)
		{
		case DEMON: 
			RAI = new RandomAi(gameState.getGameMap().getRandomFreeSpace(), gameState.getGameMap(), TextureManager.get().getEnemyTexture1());
			myEnemy = new EnemyPerson(RAI, generateStats(enemy));
			break;
		case ZOMBIE:
			RAI = new RandomAi(gameState.getGameMap().getRandomFreeSpace(), gameState.getGameMap(), TextureManager.get().getEnemyTexture2());
			myEnemy = new EnemyPerson(RAI, generateStats(enemy));
			break;
		case GHOUL:
			RAI = new RandomAi(gameState.getGameMap().getRandomFreeSpace(), gameState.getGameMap(), TextureManager.get().getEnemyTexture3());
			myEnemy = new EnemyPerson(RAI, generateStats(enemy));
			break;
		case SKULL:
			RAI = new RandomAi(gameState.getGameMap().getRandomFreeSpace(), gameState.getGameMap(), TextureManager.get().getEnemyTexture4());
			myEnemy = new EnemyPerson(RAI, generateStats(enemy));
			break;
		default:
			RAI = new RandomAi(gameState.getGameMap().getRandomFreeSpace(), gameState.getGameMap(), TextureManager.get().getEnemyTexture1());
			myEnemy = new EnemyPerson(RAI, generateStats(enemy));
			break;
		}
		
		return myEnemy;
	}
	
	public EnemyPerson generateFollower(Actor enemy)
	{
		FollowerAi FAI = new FollowerAi(enemy.getPosition(), gameState.getGameMap(), enemy, 1.3f, TextureManager.get().getEnemyTexture5());
		return new EnemyPerson(FAI, generateStats(Enemy.FOLLOWER));
	}
}
