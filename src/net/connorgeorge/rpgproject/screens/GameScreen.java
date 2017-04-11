package net.connorgeorge.rpgproject.screens;

import net.connorgeorge.rpgproject.SoundManager;
import net.connorgeorge.rpgproject.gameobjects.GameState;
import net.connorgeorge.rpgproject.gameobjects.TextureManager;
import net.connorgeorge.rpgproject.gameobjects.collectible.Goal;
import net.connorgeorge.rpgproject.gameobjects.collectible.HpIncrease;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.gameobjects.person.actor.FollowerAi;
import net.connorgeorge.rpgproject.gameobjects.person.actor.RandomAi;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;
import net.connorgeorge.rpgproject.input.InputListener;
import net.connorgeorge.rpgproject.utils.MapGenerator;
import net.connorgeorge.rpgproject.view.GameStateRenderer;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

//Class that handles what to show on the screen, not how it is displayed however
public class GameScreen implements Screen
{
	
	private GameState gameState;
	private GameStateRenderer gsRenderer;

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta)
	{
		//clear the screen, then tell the renderer to render the gameState
		clearScreen();
		if(delta > .03)
			System.out.println(delta);
		gameState.update(delta);
		gsRenderer.render(delta);
		
		if(gameState.isComplete())
		{
			//save player, put them in new position
			Player savedPlayer = gameState.getPlayer();
			savedPlayer.foundGem();
			savedPlayer.getActor().stopMoving();
			GameFactory gf = new GameFactory(2, 8, 3, 6, 10, 8, 15);  //Demons, Followers, Zombies, Ghouls, Skulls, Health potions, Mana Potions

			this.gameState = new GameState(MapGenerator.generateMap(41, 41, 5));  //Modify the 3rd arguement to change maze-ness

			this.gameState = gf.generateEnemies(this.gameState);

			this.gameState = gf.generateConsumables(this.gameState);

			this.gameState = gf.generateGoal(gameState);
			
			savedPlayer.getActor().setPosition(gameState.getPlayer().getActor().getPosition());
			savedPlayer.getActor().setGameMap(gameState.getGameMap()); //also need to reset the gameMap for the player
			//give the gameState the player with the same stats
			gameState.setPlayer(savedPlayer);
			
			gsRenderer = new GameStateRenderer(gameState);
			Gdx.input.setInputProcessor(new InputListener());
			
		}
	}

	private void clearScreen()
	{
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//will want to refactor this into making a better way of generating maps and such
		//probably pass it in from RPGProject
		
		SoundManager.get().loopMusic();
		
		GameFactory gf = new GameFactory(2, 8, 3, 6, 10, 8, 15);  //Demons, Followers, Zombies, Ghouls, Skulls, Health potions, Mana Potions

		this.gameState = new GameState(MapGenerator.generateMap(41, 41, 5));  //Modify the 3rd arguement to change maze-ness

		this.gameState = gf.generateEnemies(this.gameState);

		this.gameState = gf.generateConsumables(this.gameState);

		this.gameState = gf.generateGoal(gameState);
		
	
		
		gsRenderer = new GameStateRenderer(gameState);
		Gdx.input.setInputProcessor(new InputListener());
	}
}
