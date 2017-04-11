package net.connorgeorge.rpgproject.gameobjects.person;

import net.connorgeorge.rpgproject.gameobjects.battle.Battle;
import net.connorgeorge.rpgproject.gameobjects.person.actor.PlayerActor;
import net.connorgeorge.rpgproject.gameobjects.person.stats.PlayerStats;
import net.connorgeorge.rpgproject.input.InputHandler;
import net.connorgeorge.rpgproject.input.InputHandler.ActionType;

public class Player extends Person //nothing different for now. Will change when attacking is implemented
{
	private int gemsFound = 0;
	
	public Player(PlayerActor playerActor, PlayerStats stats)
	{
		super(playerActor, stats);
	}
	
	@Override
	public PlayerStats getStats()
	{
		return (PlayerStats) stats; //I don't like doing it this way
	}
	
	public void battleUpdate(float delta, Battle battle)
	{
		PlayerStats ps = getStats();
		
		InputHandler in = InputHandler.getInstance();
		if(in.getActionType() == ActionType.ADDATTACK)
		{
			if(in.getAttackSelected() != 0 && in.getEnemySelected() != 0 && in.getEnemySelected()-1 < battle.getEnemies().size())
			{
				ps.pushAttack(in.getAttackSelected()-1, in.getEnemySelected()-1);
			}
			in.resetActions();
		}
		else if(in.getActionType() == ActionType.POPATTACK)
		{
			ps.removeTopAttack();
			in.resetActions();
		}
	}
	
	public void foundGem()
	{
		gemsFound++;
	}
	
	public int getGemsFound()
	{
		return gemsFound;
	}
}
