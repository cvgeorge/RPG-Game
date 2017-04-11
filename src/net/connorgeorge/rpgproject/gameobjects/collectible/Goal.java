package net.connorgeorge.rpgproject.gameobjects.collectible;

import net.connorgeorge.rpgproject.gameobjects.GameState;
import net.connorgeorge.rpgproject.gameobjects.displaymessage.DisplayMessage;
import net.connorgeorge.rpgproject.gameobjects.displaymessage.NextLevelDisplayMessage;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.view.StatsRenderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Goal extends Collectible
{
	private GameState gameState;
	
	public Goal(TextureRegion texture, Vector2 position, GameState gameState)
	{
		super(texture, position);
		this.gameState = gameState;
	}

	@Override
	public void execute(Player player)
	{
		DisplayMessage message = new NextLevelDisplayMessage("Level complete, prepare for next stage!", 4f, gameState);
		StatsRenderer.get().addMessage(message);
	}
	
}
