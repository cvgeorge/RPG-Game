package net.connorgeorge.rpgproject.gameobjects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

//map of game gameobjects are in
public class GameMap
{
	private MapChunk[][] map;
	private Vector2 startPosition;
	private Vector2 endPosition;
	private TextureRegion blockTexture;
	
	public GameMap(MapChunk[][] map, Vector2 startPosition, Vector2 endPosition, TextureRegion blockTexture)
	{
		this.map = map;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		this.blockTexture = blockTexture;
	}
	
	public MapChunk getTile(int x, int y)
	{
		//outside of map is all blocks for now
		if(x < 0 || x >= map.length || y < 0 || y >= map[0].length)
		{
			return MapChunk.BLOCK;
		}
		else
		{
			return map[x][y];
		}
	}

	public MapChunk[][] getMap()
	{
		return map;
	}

	public void setMap(MapChunk[][] map)
	{
		this.map = map;
	}

	public Vector2 getStartPosition()
	{
		return startPosition.cpy();
	}

	public void setStartPosition(Vector2 startPosition)
	{
		this.startPosition = startPosition;
	}

	public Vector2 getEndPosition()
	{
		return endPosition.cpy();
	}

	public void setEndPosition(Vector2 endPosition)
	{
		this.endPosition = endPosition;
	}
	
	public TextureRegion getBlockTexture()
	{
		return blockTexture;
	}
	
	public void setBlockTexture(TextureRegion blockTexture)
	{
		this.blockTexture = blockTexture;
	}

	public Vector2 getRandomFreeSpace()
	{
		Random r = new Random();
		int xPos = 0; int yPos = 0;
		do
		{
			xPos = r.nextInt(map.length);
			yPos = r.nextInt(map[0].length);
		}while(map[xPos][yPos] != MapChunk.NONE);
		
		return new Vector2((float) xPos, (float) yPos);
	}
}
