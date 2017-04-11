package net.connorgeorge.rpgproject.gameobjects.collectible;

import net.connorgeorge.rpgproject.gameobjects.displaymessage.DisplayMessage;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.view.StatsRenderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class HpIncrease extends Collectible
{
	private float hpIncreaseAmount;
	
	public HpIncrease(TextureRegion texture, Vector2 position, float hpIncreaseAmount)
	{
		super(texture, position);
		
		this.hpIncreaseAmount = hpIncreaseAmount;
	}

	@Override
	public void execute(Player player)
	{
		player.getStats().changeHp(hpIncreaseAmount);
		DisplayMessage message = new DisplayMessage("             Health increased by " + Float.toString(hpIncreaseAmount), 2f);
		StatsRenderer.get().addMessage(message);
		//play sound here
	}

}
