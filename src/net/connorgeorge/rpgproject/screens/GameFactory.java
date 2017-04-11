package net.connorgeorge.rpgproject.screens;

import java.util.Random;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.connorgeorge.rpgproject.gameobjects.GameState;
import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.gameobjects.collectible.Goal;
import net.connorgeorge.rpgproject.gameobjects.collectible.HpIncrease;
import net.connorgeorge.rpgproject.gameobjects.collectible.MpIncrease;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.utils.MapGenerator;

public class GameFactory {
	
	int demons, followers, zombies, ghouls, skulls, healthPotions, manaPotions;
	
	public GameFactory(int numDemons, int numFollowers, int numZombies, int numGhouls, int numSkulls, int hp, int mp)
	{
		demons = numDemons;
		followers = numFollowers;
		zombies = numZombies;
		ghouls = numGhouls;
		skulls = numSkulls;
		healthPotions = hp;
		manaPotions = mp;
	}
	
	public static int generateRandomInteger(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public GameState generateConsumables(GameState gameState)
	{
		for(int i = 0; i < healthPotions; i++)
			gameState.addCollectable(new HpIncrease(TextureManager.get().getHPTexture(), gameState.getGameMap().getRandomFreeSpace(), generateRandomInteger(1,15)));
		for(int i = 0; i < manaPotions; i++)
			gameState.addCollectable(new MpIncrease(TextureManager.get().getMPTexture(), gameState.getGameMap().getRandomFreeSpace(), generateRandomInteger(1,15)));
		return gameState;
	}
	
	public GameState generateGoal(GameState gameState)
	{
		gameState.addCollectable(new Goal(TextureManager.get().getEndTexture(), gameState.getGameMap().getRandomFreeSpace(), gameState));
		return gameState;
	}
	
	public GameState generateEnemies(GameState gamestate)
	{
		int enemy, numFollowers;
		EnemyPerson enemyPerson;
		
		EnemyFactory ef = new EnemyFactory(gamestate);
		List<EnemyPerson> leaderOptions;
		
		numFollowers = 0;
		
		while(demons > 0)
		{
			if(followers > 0)
				numFollowers = generateRandomInteger(0,3);
			enemyPerson = ef.generateEnemy(Enemy.DEMON);
			gamestate.addEnemyPlayer(enemyPerson);
			for(int i = 0; i < numFollowers && followers > 0; i++)
			{
				EnemyPerson follower = ef.generateFollower(enemyPerson.getActor());
				gamestate.addEnemyPlayer(follower);
				enemyPerson = follower;
				followers--;
			}
			demons--;
		}
		while(zombies > 0)
		{
			if(followers > 0)
				numFollowers = generateRandomInteger(0,3);
			enemyPerson = ef.generateEnemy(Enemy.ZOMBIE);
			gamestate.addEnemyPlayer(enemyPerson);
			for(int i = 0; i < numFollowers && followers > 0; i++)
			{
				EnemyPerson follower = ef.generateFollower(enemyPerson.getActor());
				gamestate.addEnemyPlayer(follower);
				enemyPerson = follower;
				followers--;
			}
			zombies--;
		}
		while(ghouls > 0)
		{
			if(followers > 0)
				numFollowers = generateRandomInteger(0,3);
			enemyPerson = ef.generateEnemy(Enemy.GHOUL);
			gamestate.addEnemyPlayer(enemyPerson);
			for(int i = 0; i < numFollowers && followers > 0; i++)
			{
				EnemyPerson follower = ef.generateFollower(enemyPerson.getActor());
				gamestate.addEnemyPlayer(follower);
				enemyPerson = follower;
				followers--;
			}
			ghouls--;
		}
		while(skulls > 0)
		{
			if(followers > 0)
				numFollowers = generateRandomInteger(0,3);
			enemyPerson = ef.generateEnemy(Enemy.SKULL);
			gamestate.addEnemyPlayer(enemyPerson);
			for(int i = 0; i < numFollowers && followers > 0; i++)
			{
				EnemyPerson follower = ef.generateFollower(enemyPerson.getActor());
				gamestate.addEnemyPlayer(follower);
				enemyPerson = follower;
				followers--;
			}
			skulls--;
		}
		
		
		return gamestate;
	}

}
