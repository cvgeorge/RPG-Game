package net.connorgeorge.rpgproject.gameobjects.collectible;

import net.connorgeorge.rpgproject.gameobjects.displaymessage.DisplayMessage;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.view.StatsRenderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MpIncrease extends Collectible
{
	private float mpIncreaseAmount;
	
	public MpIncrease(TextureRegion texture, Vector2 position, float mpIncreaseAmount)
	{
		super(texture, position);
		
		this.mpIncreaseAmount = mpIncreaseAmount;
	}

	@Override
	public void execute(Player player)
	{
		player.getStats().changeMp(mpIncreaseAmount);
		DisplayMessage message = new DisplayMessage("             Mana increased by " + Float.toString(mpIncreaseAmount), 2f);
		StatsRenderer.get().addMessage(message);
		//play sound here
	}

}