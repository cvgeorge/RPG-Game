package net.connorgeorge.rpgproject.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

//enum for directions of gameobjects
public enum Direction
{
	LEFT, RIGHT, UP, DOWN, NONE;
	
	public static Direction getRandomDirection()
	{
		Random r = new Random();
		int randomInt = r.nextInt(5);
		
		switch(randomInt)
		{
		case 0:
			return LEFT;
		case 1:
			return RIGHT;
		case 2:
			return UP;
		case 3:
			return DOWN;
		case 4:
			return NONE;
		}
		
		return NONE;
	}
	
	public static Direction getDirectionTowards(Vector2 from, Vector2 to)
	{
		float yDif = to.y - from.y;
		float xDif = to.x - from.x;
		float angle = (float) Math.toDegrees(Math.atan2(yDif, xDif));
		
		if((angle >= 315 && angle <= 405) || (angle < 45 && angle > -45))
		{
			return RIGHT;
		}
		else if((angle >= 45 && angle < 135) || (angle <= -225 && angle > -315))
		{
			return UP;
		}
		else if((angle >= 135 && angle < 225) || (angle <= -135 && angle > -225))
		{
			return LEFT;
		}
		else if((angle >= 225 && angle < 315) || (angle <= -45 && angle > -135))
		{
			return DOWN;
		}
		else
		{
			return NONE;
		}
	}
}
