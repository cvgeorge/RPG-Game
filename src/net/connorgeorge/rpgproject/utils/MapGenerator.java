package net.connorgeorge.rpgproject.utils;

import java.util.Random;

import net.connorgeorge.rpgproject.gameobjects.GameMap;
import net.connorgeorge.rpgproject.gameobjects.MapChunk;
import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.screens.GameFactory;

import com.badlogic.gdx.math.Vector2;

//static class that makes a random map based on parameters.
public class MapGenerator
{
	static int myWidth, myHeight;
	
	public static void showMap(MapChunk[][] map, int width, int height)
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(map[i][j] == MapChunk.BLOCK)
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public static MapChunk[][] makeMap(MapChunk[][] map, int xPos, int yPos, boolean[][] visited)
	{
		int[] direction = new int[4];
		int number;
		for(int i = 0; i < 4; i++)
			direction[i] = -1;
		
		for(int i = 0; i < 4; i++)
		{
			if(i == 0)
			{
				direction[i] = GameFactory.generateRandomInteger(0, 3);
			}
			else if(i == 1)
			{
				do
				{
					direction[i] = GameFactory.generateRandomInteger(0, 3);
				}while(direction[i] == direction[0]);
			}
			else if(i == 2)
			{
				do
				{
					direction[i] = GameFactory.generateRandomInteger(0, 3);
				}while(direction[i] == direction[0] || direction[i] == direction[1]);
			}
			else if(i == 3)
			{
				do
				{
					direction[i] = GameFactory.generateRandomInteger(0, 3);
				}while(direction[i] == direction[0] || direction[i] == direction[1] || direction[i] == direction[2]);
			}
				
		}
		
		visited[xPos][yPos] = true;
		//System.out.println(xPos + "," + yPos);
		
		//showMap(map, 7, 7);
		
		for(int i = 0; i < 4; i++)
		{
			switch(direction[i])
			{
			case 0:
				if(yPos + 2 < myHeight)
				{
					if(visited[xPos][yPos + 2] == false)
					{
						map[xPos][yPos + 1] = MapChunk.NONE;
						makeMap(map, xPos,  yPos + 2, visited);
					}
				}
				break;
			case 1:
				if(yPos - 2 > 0)
				{
					if(visited[xPos][yPos - 2] == false)
					{
						map[xPos][yPos - 1] = MapChunk.NONE;
						makeMap(map, xPos,  yPos - 2, visited);
					}
				}
				break;
			case 2:
				if(xPos + 2 < myWidth)
				{
					if(visited[xPos + 2][yPos] == false)
					{
						map[xPos + 1][yPos] = MapChunk.NONE;
						makeMap(map, xPos + 2,  yPos, visited);
					}
				}
				break;
			case 3:
				if(xPos - 2 > 0)
				{
					if(visited[xPos - 2][yPos] == false)
					{
						map[xPos - 1][yPos] = MapChunk.NONE;
						makeMap(map, xPos - 2,  yPos, visited);
					}
				}
				break;
			}
		}
		
		return map;
	}
	
	public static GameMap generateMap(int width, int height, int randomness)
	{
		if(width % 2 == 0)
			width++;
		if(height % 2 == 0)
			height++;
		MapChunk[][] map = new MapChunk[width][height];
		boolean[][] visitedSquares = new boolean[width][height];
		int xPos, yPos;
		int wallKnockX, wallKnockY;
		myWidth = width;
		myHeight = height;
		
		initializeMapWithBlocks(map);
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(i % 2 != 0 && j % 2 != 0)
					map[i][j] = MapChunk.NONE;
			}
		}
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; i < height; i++)
			{
				visitedSquares[i][j] = false;
			}
		}
		System.out.println("Probably gets here");
		map = makeMap(map, 1, 1, visitedSquares);
		System.out.println("Might not get here");
		
		Random r = new Random();
		
		Vector2 startPosition = new Vector2();
		Vector2 endPosition = new Vector2();
		
		startPosition.x = r.nextInt(width);
		startPosition.y = r.nextInt(height);
		System.out.println("OK");
		while(map[(int) startPosition.x][(int) startPosition.y] == MapChunk.BLOCK)
		{
			startPosition.x = r.nextInt(width);
			startPosition.y = r.nextInt(height);
		}
		System.out.println("That");
		endPosition.x = r.nextInt(width-1);
		endPosition.y = r.nextInt(height-1);
		System.out.println("Was");
		
		if(map[(int) endPosition.x][(int) endPosition.y] == MapChunk.BLOCK)
		{
			if(map[(int) endPosition.x][(int) endPosition.y+1] == MapChunk.BLOCK)
			{
				if(map[(int) endPosition.x][(int) endPosition.y-1] == MapChunk.BLOCK)
				{
					if(map[(int) endPosition.x+1][(int) endPosition.y] == MapChunk.BLOCK)
					{
						if(map[(int) endPosition.x-1][(int) endPosition.y] == MapChunk.BLOCK)
						{
							
						}
						else
						{
							endPosition.y = endPosition.x-1;
						}
					}
					else
					{
						endPosition.x = endPosition.x+1;
					}
				}
				else
				{
					endPosition.y = endPosition.y-1;
				}
			}
			else
			{
				endPosition.y = endPosition.y+1;
			}
		}
		System.out.println("Unexpected");
		System.out.println("map generated");
		
		for(int i = 0; i < randomness * (width + height) + 20; i++)
		{
			wallKnockX = GameFactory.generateRandomInteger(1, width - 1);
			wallKnockY = GameFactory.generateRandomInteger(1, height - 1);
			
			if(map[wallKnockX][wallKnockY] == MapChunk.BLOCK)
			{
				map[wallKnockX][wallKnockY] = MapChunk.NONE;
			}
		}
		
		int midX = width / 2;
		int midY = width / 2;
		
		for(int i = -5; i <= 5; i++)
		{
			for(int j = -5; j <= 5; j++)
			{
				map[midX+i][midY+j] = MapChunk.NONE;
			}
		}
		
		return new GameMap(map, startPosition, endPosition, TextureManager.get().getBlockTexture());
	}

	private static void initializeMapWithBlocks(MapChunk[][] map)
	{
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				//start with map made of blocks
				map[i][j] = MapChunk.BLOCK;
			}
		}
	}
	
	private static void flagAsNone(MapChunk[][] map, Vector2 pos)
	{
		map[(int)pos.x][(int)pos.y] = MapChunk.NONE; 
	}
	
	private static boolean isInBounds(MapChunk[][] map, Vector2 pos)
	{
		return (pos.x >=0 && pos.x < map.length) && (pos.y >= 0 && pos.y < map[0].length);
	}
}
