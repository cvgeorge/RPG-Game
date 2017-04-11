package net.connorgeorge.rpgproject.view;

import java.util.LinkedList;
import java.util.List;

import net.connorgeorge.rpgproject.gameobjects.GameState;
import net.connorgeorge.rpgproject.gameobjects.MapChunk;
import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.gameobjects.collectible.Collectible;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.gameobjects.person.Person;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

//Give this class a gameState and it will render it
//will probably have to make more of these that render the battles and such
public class GameStateRenderer
{
	//rendering constants
	private static final float CAMERA_WIDTH = 24f; //Aspect ratio for widescreens
	private static final float CAMERA_HEIGHT = 13.5f;
	private static final int OUT_OF_BOUNDS_DRAW = 3; //blocks to render outside the bounds of the screen
	private static final int BLOCK_SIZE = 1;
	
	//GameState we will be rendering
	private GameState gameState;
	
	//how we view the game
	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;
	
	private Vector2 centroid = null;
	
	public GameStateRenderer(GameState gameState)
	{
		this.gameState = gameState;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT); //how many blocks to display
		this.cam.position.set(CAMERA_WIDTH/2f, CAMERA_HEIGHT/2f, 0);
		this.cam.update();
		
		this.spriteBatch = new SpriteBatch();
	}
	
	public void render(float delta)
	{
		Vector2 cameraCenter;
		
		if(gameState.isFighting())
		{
			if(centroid == null)
			{
				List<Vector2> vertices = new LinkedList<Vector2>();
				List<EnemyPerson> enemies = gameState.getEnemiesInFight();
				for(Person p : enemies)
				{
					vertices.add(p.getActor().getPosition());
				}
				vertices.add(gameState.getPlayer().getActor().getPosition());
				centroid = calculateCentroid(vertices);
			}
			cameraCenter = centroid;
		}
		else
		{
			cameraCenter = gameState.getPlayer().getActor().getPosition();
			centroid = null;
		}
		
		//centers camera and updates camera
		cam.position.set(cameraCenter.x-CAMERA_WIDTH*.1f, cameraCenter.y, 0);
		cam.update();
		
		//display blocks
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
		drawBlocksAndCollectables();
		drawPlayer();
		drawEnemies();
		spriteBatch.end();
		StatsRenderer.get().renderStats(cam, gameState, spriteBatch, delta);
	}
	
	private void drawBlocksAndCollectables()
	{
		Vector2 playerPosition = gameState.getPlayer().getActor().getPosition();
		
		TextureRegion blockTexture = gameState.getGameMap().getBlockTexture();
		
		int drawStartX = (int)(Math.floor(playerPosition.x)-(CAMERA_WIDTH/2+OUT_OF_BOUNDS_DRAW));
		int drawEndX = (int)(Math.floor(playerPosition.x)+(CAMERA_WIDTH/2+OUT_OF_BOUNDS_DRAW));
		int drawStartY = (int)(Math.floor(playerPosition.y)-(CAMERA_HEIGHT/2+OUT_OF_BOUNDS_DRAW));
		int drawEndY = (int)(Math.floor(playerPosition.y)+(CAMERA_HEIGHT/2+OUT_OF_BOUNDS_DRAW));
		
		for(int i = drawStartX; i <= drawEndX; i++)
		{
			for(int j = drawStartY; j <= drawEndY; j++)
			{
				if(gameState.getGameMap().getTile(i, j) == MapChunk.BLOCK)
				{
					spriteBatch.draw(blockTexture, i, j, BLOCK_SIZE, BLOCK_SIZE);
				}
			}
		}
		
		for(Collectible c : gameState.getCollectibles())
		{
			Vector2 pos = c.getPosition();
			if((pos.x > drawStartX && pos.x < drawEndX) && (pos.y > drawStartY && pos.y < drawEndY))
			{
				spriteBatch.draw(c.getTexture(), pos.x, pos.y, BLOCK_SIZE, BLOCK_SIZE);
			}
		}
	}
	
	private void drawPlayer()
	{
		Vector2 pos = gameState.getPlayer().getActor().getPosition();
		spriteBatch.draw(gameState.getPlayer().getActor().getTexture(), pos.x, pos.y, BLOCK_SIZE, BLOCK_SIZE);
	}
	
	private void drawEnemies()
	{
		for(Person p : gameState.getEnemies())
		{
			Vector2 pos = p.getActor().getPosition();
			TextureRegion texture;
			if(p.getStats().getHp() <= 0)
				texture = TextureManager.get().getDeathTexture();
			else
				texture = p.getActor().getTexture();
			spriteBatch.draw(texture, pos.x, pos.y, BLOCK_SIZE, BLOCK_SIZE);
		}
	}
	
	private Vector2 calculateCentroid(List<Vector2> vertices)
	{
		Vector2 centroid = new Vector2(0f, 0f);
		for(Vector2 v : vertices)
		{
			centroid.x += v.x;
			centroid.y += v.y;
		}
		
		centroid.x /= vertices.size();
		centroid.y /= vertices.size();
		
		return centroid;
	}
}
