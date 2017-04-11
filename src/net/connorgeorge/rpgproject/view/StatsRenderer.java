package net.connorgeorge.rpgproject.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.connorgeorge.rpgproject.SoundManager;
import net.connorgeorge.rpgproject.gameobjects.GameState;
import net.connorgeorge.rpgproject.gameobjects.battle.AttackUnit;
import net.connorgeorge.rpgproject.gameobjects.battle.Battle;
import net.connorgeorge.rpgproject.gameobjects.battle.animations.AttackAnimation;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.Attack;
import net.connorgeorge.rpgproject.gameobjects.battle.attacks.SpecificAttack;
import net.connorgeorge.rpgproject.gameobjects.displaymessage.DisplayMessage;
import net.connorgeorge.rpgproject.gameobjects.person.EnemyPerson;
import net.connorgeorge.rpgproject.gameobjects.person.Player;
import net.connorgeorge.rpgproject.gameobjects.person.stats.Stats;
import net.connorgeorge.rpgproject.input.InputHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class StatsRenderer
{
	private static final float FONT_SCALE = 0.05f;
	private BitmapFont font;
	private ShapeRenderer shapeRenderer;
	private static StatsRenderer sr = null;
	private Color red;
	private Color black;
	private Color white;
	private Color green;
	private Color blue;
	private float attacksBaseDisplay;
	private float enemiesBaseDisplay;
	private float queueBaseDisplay;
	private boolean displayMessage;

	private Queue<DisplayMessage> messages;
	private List<AttackAnimation> attackAnimation;

	private StatsRenderer()
	{
		this.shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		this.red = new Color(1, 0, 0, 1);
		this.black = new Color(0, 0, 0, 1);
		this.white = new Color(1, 1, 1, 1);
		this.green = new Color(0, 1, 0, 1);
		this.blue = new Color(0, 0, 1, 1);

		this.font = new BitmapFont();
		font.setUseIntegerPositions(false);
		font.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.setColor(white);
		this.displayMessage = false;

		this.messages = new LinkedList<DisplayMessage>();
		attackAnimation = new LinkedList<AttackAnimation>();
	}

	public static StatsRenderer get()
	{
		if (sr == null)
		{
			sr = new StatsRenderer();
		}

		return sr;
	}

	public void addMessage(DisplayMessage message)
	{
		messages.add(message);
	}
	
	public void addAttackAnimation(AttackAnimation aa)
	{
		attackAnimation.add(aa);
	}

	public void renderStats(OrthographicCamera cam, GameState gameState,
			SpriteBatch spriteBatch, float delta)
	{
		float camLeftX = cam.position.x - cam.viewportWidth / 2f;
		float camBottomY = cam.position.y - cam.viewportHeight / 2f;
		float width = cam.viewportWidth * .25f;
		float widthLeftOver = cam.viewportWidth - width;
		float height = cam.viewportHeight;
		
		attacksBaseDisplay = camBottomY + height - 12.5f;
		enemiesBaseDisplay = camBottomY + height - 6.8f;
		queueBaseDisplay = camBottomY + height - 2f;

		if (messages.size() > 0)
			displayMessage = true;
		else
			displayMessage = false;
		
		if(displayMessage)
		{
			DisplayMessage m = messages.element();
			m.update(delta);
			if(m.isDone())
			{
				m.callOnComplete();
				messages.remove();
				displayMessage = false;
			}
		}

		// DRAW SHAPES----------------------------------
		drawShapes(cam, gameState, camLeftX, camBottomY, width, height, widthLeftOver, delta);

		Player p = gameState.getPlayer();

		// DRAW HP TEXT---------------------------------
		spriteBatch.begin();
		font.setScale(FONT_SCALE);
		font.draw(spriteBatch, "HP", camLeftX + .25f, camBottomY + height
				- 0.25f);
		font.draw(spriteBatch, "MP", camLeftX + .25f, camBottomY + height
				- 1.25f);
		font.setScale(FONT_SCALE / 1.1f);

		// DRAW ATTACK QUEUE TEXT/IMG---------------------
		if (gameState.isFighting())
		{
			List<AttackUnit> attackQueue = p.getStats().getAttackUnitList();
			List<Integer> indexes = p.getStats().getAttackIndexesList();
			for (int i = 0; i < attackQueue.size() && i < 4; i++)
			{
				int enemyNum = attackQueue.get(i).attacked;
				Attack attack = attackQueue.get(i).attack;
				float y = queueBaseDisplay - .8f * (4 - i);
				spriteBatch.draw(attack.getTexture(indexes.get(i)), camLeftX + 1f, y - .7f,
						.8f, .8f);
				font.draw(spriteBatch, ">", camLeftX + 2f, y);
				Battle b = gameState.getBattle();
				spriteBatch.draw(b.getEnemies().get(enemyNum).getActor()
						.getTexture(), camLeftX + 2.6f, y - .7f, .8f, .8f);
				font.draw(spriteBatch, "(" + Integer.toString(enemyNum + 1)
						+ ")", camLeftX + 3.5f, y);
			}
		}

		
		// DRAW MESSAGE
		if(displayMessage)
		{
			font.draw(spriteBatch, messages.element().getMessage(), camLeftX+width+.8f, camBottomY+height-.9f);
		}
		
		
		// DRAW ATTACKS TEXT/IMG--------------------------
		List<SpecificAttack> attackList = p.getStats().getAvailableAttacks(); 
		
		float multiplier = .8f;
		float imgMultiplier = .7f;
		// This is terrible but the menu will be hardcoded anyway...
		font.draw(spriteBatch, "Actions", camLeftX + 0.5f,
				attacksBaseDisplay + 1.5f);

		// once attack textures are done, we will use those
		spriteBatch.draw(attackList.get(0).getTexture(0), camLeftX + 0.5f,
				attacksBaseDisplay, .8f, .8f);
		spriteBatch.draw(attackList.get(1).getTexture(1), camLeftX + 1.5f,
				attacksBaseDisplay, .8f, .8f);
		spriteBatch.draw(attackList.get(2).getTexture(2), camLeftX + 2.5f,
				attacksBaseDisplay, .8f, .8f);
		spriteBatch.draw(attackList.get(3).getTexture(3), camLeftX + 3.5f,
				attacksBaseDisplay, .8f, .8f);

		font.setScale(FONT_SCALE / 1.5f);

		font.draw(spriteBatch, "Q", camLeftX + 0.6f, attacksBaseDisplay - 0.1f);
		font.draw(spriteBatch, "W", camLeftX + 1.6f, attacksBaseDisplay - 0.1f);
		font.draw(spriteBatch, "E", camLeftX + 2.6f, attacksBaseDisplay - 0.1f);
		font.draw(spriteBatch, "R", camLeftX + 3.6f, attacksBaseDisplay - 0.1f);

		font.setScale(FONT_SCALE / 1.1f);

		// DRAW ENEMIES TEXT/IMG--------------------------
		if (gameState.isFighting())
		{
			Battle b = gameState.getBattle();
			List<EnemyPerson> enemies = b.getEnemies();

			for (int i = 0; i < enemies.size(); i++) // did this instead of for
														// each because we need
														// the index
			{
				EnemyPerson e = enemies.get(i);
				font.draw(spriteBatch, Integer.toString(i + 1),
						camLeftX + 0.5f, enemiesBaseDisplay - multiplier * i);
				spriteBatch.draw(e.getActor().getTexture(), camLeftX + 1f,
						enemiesBaseDisplay - multiplier * (i + imgMultiplier),
						multiplier * imgMultiplier, multiplier * imgMultiplier);
				Vector2 pos = e.getActor().getPosition();
				font.draw(spriteBatch, Integer.toString(i + 1), pos.x, pos.y
						+ e.getActor().getBounds().height + .3f);
			}
		}

		spriteBatch.end();
	}

	private void drawShapes(OrthographicCamera cam, GameState gameState, float camLeftX, float camBottomY, float width, float height, float widthLeftOver, float delta)
	{
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(black);
		shapeRenderer.rect(camLeftX, camBottomY, width, height);
		
		shapeRenderer.setColor(white);
		shapeRenderer.rect(camLeftX+width, camBottomY, .05f, height);
		
		float barBegin = camLeftX + 1.8f;
		float barEnd = camLeftX + width-.5f;
		
		drawStatsBar(shapeRenderer, gameState.getPlayer().getStats(), barBegin, barEnd, camBottomY+height-0.25f, .3f, true);
		drawStatsBar(shapeRenderer, gameState.getPlayer().getStats(), barBegin, barEnd, camBottomY+height-1.25f, .3f, false);

		if(displayMessage)
		{
			shapeRenderer.setColor(white);
			shapeRenderer.rect(camLeftX+width+.4f, camBottomY+height-2.1f, widthLeftOver-.8f, 1.7f);
			shapeRenderer.set(ShapeType.Filled);
			shapeRenderer.setColor(black);
			shapeRenderer.rect(camLeftX+width+.5f, camBottomY+height-2f, widthLeftOver-1, 1.5f);
		}
		
		
//		float topOfScreen = camBottomY+height;
//		drawDivider(.5f, topOfScreen - 2f, width - 1f, shapeRenderer);
		
		renderAttackAnimations(shapeRenderer, delta);
		
		if(gameState.isFighting())
		{	
			Battle b = gameState.getBattle();
			List<EnemyPerson> enemies  = b.getEnemies();
			
			float multiplier = .8f;
			
			int selectedAttack = InputHandler.getInstance().getAttackSelected()-1;
			if(selectedAttack >= 0)
			{
				shapeRenderer.set(ShapeType.Line);
				shapeRenderer.setColor(white);
				shapeRenderer.rect(camLeftX+0.5f+selectedAttack, attacksBaseDisplay, .8f, .8f);
				shapeRenderer.set(ShapeType.Filled);
			}
			
			for(int i = 0; i < enemies.size(); i++) //did this instead of for each because we need the index
			{
				EnemyPerson e = enemies.get(i);
				Vector2 ePos = e.getActor().getPosition();
				drawStatsBar(shapeRenderer, e.getStats(), camLeftX+1.8f, camLeftX+width-.5f, enemiesBaseDisplay-multiplier*i-.05f, .3f, true);
				if(InputHandler.getInstance().getEnemySelected() == i+1)
				{
					shapeRenderer.set(ShapeType.Line);
					shapeRenderer.setColor(white);
					shapeRenderer.rect(camLeftX+0.9f, enemiesBaseDisplay-multiplier*(i+.6f)-.2f, .7f, .7f);
					shapeRenderer.rect(ePos.x, ePos.y, 1f, 1f);
					shapeRenderer.set(ShapeType.Filled);
				}
			}
			shapeRenderer.set(ShapeType.Filled);
		}
		
		shapeRenderer.end();
	}
	
	private void renderAttackAnimations(ShapeRenderer shapeRenderer, float delta)
	{
		Iterator<AttackAnimation> ai = attackAnimation.iterator();
		while(ai.hasNext())
		{
			AttackAnimation a = ai.next();
			a.animate(shapeRenderer, delta);
			if(a.isDone())
			{
				SoundManager.get().playPunch();
				a.executeAttack();
				ai.remove();
			}
		}
	}

	private float getBarWidth(float hp, float maxHp, float drawWidth)
	{
		return drawWidth * (hp / maxHp);
	}

	private void drawStatsBar(ShapeRenderer shapeRenderer, Stats stats,
			float barBeginX, float barEndX, float y, float height,
			boolean isHealth)
	{
		float barWidth = barEndX - barBeginX;
		float yWithOffset = y - 0.4f;

		float greenWidth;
		if (isHealth)
			greenWidth = getBarWidth(stats.getHp(), stats.getMaxHp(), barWidth);
		else
			greenWidth = getBarWidth(stats.getMp(), stats.getMaxMp(), barWidth);

		float redWidth = barWidth - greenWidth;
		shapeRenderer.setColor(red);
		shapeRenderer.rect(barBeginX, yWithOffset, redWidth, height);

		if (isHealth)
			shapeRenderer.setColor(green);
		else
			shapeRenderer.setColor(blue);

		shapeRenderer.rect(barBeginX + redWidth, yWithOffset, greenWidth,
				height);
	}

	// private void drawDivider(float x, float y, float width, ShapeRenderer
	// shapeRenderer)
	// {
	// shapeRenderer.rect(x, y, x + width, y+width);
	//
	// }
}
