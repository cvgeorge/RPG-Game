package net.connorgeorge.rpgproject.gameobjects.person.actor;

import net.connorgeorge.rpgproject.gameobjects.Direction;
import net.connorgeorge.rpgproject.gameobjects.GameMap;
import net.connorgeorge.rpgproject.gameobjects.MapChunk;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

//A character that moves around the map and is displayed on the screen
//possible subclasses: the player, npc's
public abstract class Actor
{
	public static final float SIZE = 1f; //size relative to grid element
	
	protected Vector2 position; //current position
	protected Vector2 target; //where we would like to travel to
	private Rectangle bounds; //size on screen
	private static Vector2 unitVector = new Vector2(1,0); //unit vector used in some direction calculations
	private GameMap gameMap; //current map. Used for collision detection
	protected float distancePerDelta; //how many blocks to travel per second
	protected TextureRegion texture = null;
	
	public Actor(Vector2 position, GameMap gameMap)
	{
		this.position = position.cpy();
		this.bounds = new Rectangle();
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.target = null; //when target is null, we have no target
		distancePerDelta = 4.5f;
		
		this.gameMap = gameMap;
	}
	
	//What to do after delta time (s), in subclasses
	public abstract void update(float delta);
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setPosition(Vector2 pos)
	{
		this.position = pos;
	}
	
	public void setGameMap(GameMap map)
	{
		this.gameMap = map;
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	//If the player is done moving, move in the specified direction
	public void moveInDirection(Direction dir)
	{
		//if player has a target, it is still moving
		if(target != null)
			return;

		//floor just in case
		float currentX = (float) Math.floor(position.x);
		float currentY = (float) Math.floor(position.y);

		//starts movement by setting target. Actor will move next time update is called
		if(dir == Direction.LEFT)
			target = new Vector2(currentX - 1f, currentY); //one block to left, right, etc
		else if(dir == Direction.RIGHT)
			target = new Vector2(currentX + 1f, currentY);
		else if(dir == Direction.UP)
			target = new Vector2(currentX, currentY + 1f);
		else if(dir == Direction.DOWN)
			target = new Vector2(currentX, currentY - 1f);
	}
	
	public void stopMoving()
	{
		target = null;
	}
	
	//given a time interval, move towards current target
	protected void moveTowardsTarget(float delta)
	{
		if(target == null) //must have target to move
			return;
		
		//can't go through blocks
		if(gameMap.getTile((int)target.x, (int)target.y) == MapChunk.BLOCK)
		{
			target = null;
			return;
		}

		float distanceToTravel = delta*distancePerDelta;
		float distance = position.dst(target);
		
		//if we will reach target, just go to target
		if(distanceToTravel > distance)
		{
			position = target.cpy();
			target = null;
		}
		else //otherwise, move in the direction of the target
		{
			//point a unit vector in the direction of the target, and multiply the scalar distance to travel
			float yDif = target.y - position.y;
			float xDif = target.x - position.x;
			
			float angle = (float) Math.toDegrees(Math.atan2(yDif, xDif)); //atan2 is safe way to get angle. Won't divide by 0 and such
			
			//rotate unit vector by rotate amount, and scale it by how far you will travel in that time interval
			Vector2 moveVector = unitVector.cpy().rotate(angle).scl(distanceToTravel);
			
			//move in direction
			position.add(moveVector);
		}
	}
	
	public TextureRegion getTexture()
	{
		return texture;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bounds == null) ? 0 : bounds.hashCode());
		result = prime * result + Float.floatToIntBits(distancePerDelta);
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Actor other = (Actor) obj;
		if (bounds == null)
		{
			if (other.bounds != null)
				return false;
		} else if (!bounds.equals(other.bounds))
			return false;
		if (Float.floatToIntBits(distancePerDelta) != Float
				.floatToIntBits(other.distancePerDelta))
			return false;
		if (position == null)
		{
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (target == null)
		{
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
}
