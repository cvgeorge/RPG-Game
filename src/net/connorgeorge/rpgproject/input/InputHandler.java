package net.connorgeorge.rpgproject.input;

import java.util.HashMap;
import java.util.Map;

import net.connorgeorge.rpgproject.gameobjects.Direction;

import com.badlogic.gdx.Input.Keys;

//singleton. Processes input given from inputListener.
//Basically this class is given the current keyboard state. Objects can ask it
//what actions are being requested based on keyboard input. See get current direction
//method for instance
public class InputHandler
{
	private static InputHandler handler = null;
	
	private enum KeyLabel
	{
		LEFT, RIGHT, UP, DOWN
	}
	
	public static enum ActionType
	{
		NONE, ADDATTACK, POPATTACK
	}
	
	private static Map<KeyLabel, Boolean> keys;
	private int enemySelected = 0;
	private int attackSelected = 0;
	private ActionType actionType;
	
	private InputHandler()
	{
		keys = new HashMap<KeyLabel, Boolean>();
		keys.put(KeyLabel.LEFT, false);
		keys.put(KeyLabel.RIGHT, false);
		keys.put(KeyLabel.UP, false);
		keys.put(KeyLabel.DOWN, false);
		this.enemySelected = 0; //zero is case that selection is not made
		this.attackSelected = 0;
		this.actionType = ActionType.NONE;
	}
	
	public static synchronized InputHandler getInstance()
	{
		if(handler == null)
		{
			handler = new InputHandler();
		}
		
		return handler;
	}
	
	public void sendInputOn(int key)
	{
		triggerInput(key, true);
	}
	
	public void sendInputOff(int key)
	{
		triggerInput(key, false);
	}
	
	private void triggerInput(int key, boolean on)
	{
		switch(key)
		{
		case Keys.LEFT:
			keys.put(KeyLabel.LEFT, on);
			break;
		case Keys.RIGHT:
			keys.put(KeyLabel.RIGHT, on);	
			break;	
		case Keys.UP:
			keys.put(KeyLabel.UP, on);
			break;
		case Keys.DOWN:
			keys.put(KeyLabel.DOWN, on);
			break;
		case Keys.NUM_1:
			enemySelected = 1;
			break;
		case Keys.NUM_2:
			enemySelected = 2;
			break;
		case Keys.NUM_3:
			enemySelected = 3;
			break;
		case Keys.NUM_4:
			enemySelected = 4;
			break;
		case Keys.NUM_5:
			enemySelected = 5;
			break;
		case Keys.NUM_6:
			enemySelected = 6;
			break;
		case Keys.NUM_7:
			enemySelected = 7;
			break;
		case Keys.NUM_8:
			enemySelected = 8;
			break;
		case Keys.NUM_9:
			enemySelected = 9;
			break;
		case Keys.NUM_0:
			enemySelected = 0;
			break;
		case Keys.Q:
			attackSelected = 1;
			break;
		case Keys.W:
			attackSelected = 2;
			break;
		case Keys.E:
			attackSelected = 3;
			break;
		case Keys.R:
			attackSelected = 4;
			break;
		case Keys.T:
			attackSelected = 0;
			break;
		case Keys.ENTER:
			if(on)
				actionType = ActionType.ADDATTACK;
			break;
		case Keys.BACKSPACE:
			if(on)
				actionType = ActionType.POPATTACK;
			break;
		}
	}
	
	public Direction getCurrentDirection()
	{
		if(countMoveKeys() == 1)
			return pressedMoveKey();
		else
			return Direction.NONE;
	}
	
	//counts how many of the movement keys are pressed. We don't want
	//to move if multiple are pressed (as of now)
	private int countMoveKeys()
	{
		int count = 0;
		if(keys.get(KeyLabel.UP)) count++;
		if(keys.get(KeyLabel.DOWN)) count++;
		if(keys.get(KeyLabel.LEFT)) count++;
		if(keys.get(KeyLabel.RIGHT)) count++;
		return count;
	}
	
	private Direction pressedMoveKey()
	{
		if(keys.get(KeyLabel.UP)) return Direction.UP;
		if(keys.get(KeyLabel.DOWN)) return Direction.DOWN;
		if(keys.get(KeyLabel.LEFT)) return Direction.LEFT;
		if(keys.get(KeyLabel.RIGHT))  return Direction.RIGHT;
		return Direction.NONE;
	}
	
	public void resetActions()
	{
		actionType = ActionType.NONE;
		attackSelected = 0;
		enemySelected = 0;
	}
	
	public ActionType getActionType()
	{
		return actionType;
	}
	
	public int getAttackSelected()
	{
		return attackSelected;
	}
	
	public int getEnemySelected()
	{
		return enemySelected;
	}
}