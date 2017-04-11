package net.connorgeorge.rpgproject.gameobjects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;

import net.connorgeorge.rpgproject.GameOverDialog;
import net.connorgeorge.rpgproject.gameobjects.battle.Battle;
import net.connorgeorge.rpgproject.gameobjects.battle.Battle.BattleStatus;
import net.connorgeorge.rpgproject.gameobjects.collectible.Collectible;
import net.connorgeorge.rpgproject.gameobjects.collectible.HpIncrease;
import net.connorgeorge.rpgproject.gameobjects.collectible.MpIncrease;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.gameobjects.person.Person;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.gameobjects.person.actor.PlayerActor;
import net.connorgeorge.rpgproject.gameobjects.person.stats.PlayerStats;

import com.badlogic.gdx.math.Vector2;

//class that contains the entire state of the game. Idea is the logic of the game
//happens in gameState independently from displaying the game. A renderer simply displays what
//is happening in the GamSetate

public class GameState
{
	//eventually will want a list of npcs, can add stuff as needed
	private GameMap gameMap;
	private Player player;
	private List<EnemyPerson> enemies;
	private List<Collectible> collectibles;
	private boolean isFighting;
	private Battle battle;
	private float timePassed = 0; //time passed in seconds
	private static final float attackInterval = 2f;
	private boolean complete;

	public GameState(GameMap gameMap)
	{
		this.gameMap = gameMap;
		this.player = new Player(new PlayerActor(gameMap.getStartPosition(), gameMap), new PlayerStats(125, 80, 25));
		this.enemies = new LinkedList<EnemyPerson>();
		this.collectibles = new LinkedList<Collectible>();
		this.isFighting = false;
		this.battle = null;
		this.complete = false;
	}

	public GameMap getGameMap()
	{
		return gameMap;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Player getPlayer()
	{
		return player;
	}
	
	public boolean isComplete()
	{
		return complete;
	}
	
	public void flagComplete()
	{
		complete = true;
	}

	//update everything that needs a timeupdate here
	public void update(float delta)
	{
		if(!isFighting)
		{
			player.update(delta);
			
			for(Person p : enemies)
			{
				p.update(delta);
			}
			
			Iterator<Collectible> ic = collectibles.iterator();
			while(ic.hasNext())
			{
				Collectible c = ic.next();
				if(c.isCollision(player.getActor().getPosition()))
				{
					c.execute(player);
					ic.remove();
				}
			}
			
			List<EnemyPerson> fighters = getEnemiesInFight();
			
			if(fighters.size() > 0)
			{
				battle = new Battle(player, fighters);
				isFighting = true;
				player.getActor().stopMoving();
			}
		}
		else
		{
			player.battleUpdate(delta, battle);
			
			timePassed += delta;
			if(timePassed > attackInterval)
			{
				timePassed = 0;
				battle.doNextAttack();
			}
			
			BattleStatus status = battle.getBattleStatus();
			if(status == BattleStatus.DEFEAT)
			{
				new GameOverDialog(new JFrame(), "GAME OVER", "Game Over!  You found " + player.getGemsFound() + " gems.");
				System.exit(0);
			}
			else if(status == BattleStatus.WIN)
			{
				Random r = new Random();
				int doDrop = r.nextInt(3);
				if(doDrop < 2 && (battle.getEnemies().size() > 1 || r.nextInt(10) < 4))
				{
					int type = r.nextInt(2);
					Vector2 position = battle.getEnemies().get(0).getActor().getPosition();
					
					if(type == 0)
					{
						addCollectable(new HpIncrease(TextureManager.get().getHPTexture(), position, 30f));
					}
					else
					{
						addCollectable(new MpIncrease(TextureManager.get().getMPTexture(), position, 30f));
					}
				}
				
				for(EnemyPerson p : battle.getEnemies())
				{
					enemies.remove(p);
					battle = null;
					isFighting = false;
				}
			}
		}
	}
	
	public void addEnemyPlayer(EnemyPerson person)
	{
		enemies.add(person);
	}
	
	public List<EnemyPerson> getEnemies()
	{
		return enemies;
	}
	
	public void addCollectable(Collectible collectible)
	{
		collectibles.add(collectible);
	}
	
	public List<Collectible> getCollectibles()
	{
		return collectibles;
	}
	
	public List<EnemyPerson> getEnemiesInFight() //this should return a list of all people involved in a fight
	{
		Queue<Person> distQueue = new LinkedList<Person>();
		List<EnemyPerson> fighters = new LinkedList<EnemyPerson>();
		
		distQueue.add(player);
		
		boolean firstTime = true;
		while(!distQueue.isEmpty())
		{
			for(EnemyPerson p : enemies)
			{
				if(!fighters.contains(p) && !distQueue.contains(p) && p.getActor().isDetected(distQueue.element().getActor()))
				{
					distQueue.add(p);
				}
			}
			
			if(!distQueue.isEmpty())
			{
				if(!firstTime)
				{
					fighters.add((EnemyPerson) distQueue.remove());
				}
				else
				{
					firstTime = false;
					distQueue.remove();
				}
			}
		}
		
		return fighters;
	}
	
	public boolean isFighting()
	{
		return isFighting;
	}
	
	public Battle getBattle()
	{
		return battle;
	}
}
