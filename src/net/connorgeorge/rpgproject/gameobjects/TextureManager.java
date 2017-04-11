package net.connorgeorge.rpgproject.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureManager
{
	private static TextureManager manager = null;
	private static final String packString = "images/textures/textures.pack";
	
	private TextureRegion playerTexture;
	private TextureRegion blockTexture;
	private TextureRegion enemyTexture1;
	private TextureRegion enemyTexture2;
	private TextureRegion enemyTexture3;
	private TextureRegion enemyTexture4;
	private TextureRegion enemyTexture5;
	private TextureRegion weaponTexture1;
	private TextureRegion weaponTexture2;
	private TextureRegion weaponTexture3;
	private TextureRegion weaponTexture4;
	private TextureRegion deathTexture;
	private TextureRegion endTexture;
	private TextureRegion hpTexture;
	private TextureRegion mpTexture;
	
	
	private TextureManager()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(packString));
		this.playerTexture = atlas.findRegion("hero");
		this.blockTexture = atlas.findRegion("wall");
		this.enemyTexture1 = atlas.findRegion("enemy1");
		this.enemyTexture2 = atlas.findRegion("enemy2");
		this.enemyTexture3 = atlas.findRegion("enemy3");
		this.enemyTexture4 = atlas.findRegion("enemy4");
		this.enemyTexture5 = atlas.findRegion("enemy5");
		this.weaponTexture1 = atlas.findRegion("weapon1");
		this.weaponTexture2 = atlas.findRegion("weapon2");
		this.weaponTexture3 = atlas.findRegion("weapon3");
		this.weaponTexture4 = atlas.findRegion("weapon4");
		this.deathTexture = atlas.findRegion("death");
		this.endTexture = atlas.findRegion("goal");
		this.hpTexture = atlas.findRegion("healthPowerup");
		this.mpTexture = atlas.findRegion("manaPowerup");
	}
	
	public static TextureManager get()
	{
		if(manager == null)
		{
			manager = new TextureManager();
		}
		
		return manager;
	}

	public TextureRegion getPlayerTexture()
	{
		return playerTexture;
	}

	public TextureRegion getBlockTexture()
	{
		return blockTexture;
	}

	public TextureRegion getEnemyTexture1()
	{
		return enemyTexture1;
	}

	public TextureRegion getEnemyTexture2()
	{
		return enemyTexture2;
	}

	public TextureRegion getEnemyTexture3()
	{
		return enemyTexture3;
	}

	public TextureRegion getEnemyTexture4()
	{
		return enemyTexture4;
	}

	public TextureRegion getEnemyTexture5()
	{
		return enemyTexture5;
	}

	public TextureRegion getWeaponTexture1()
	{
		return weaponTexture1;
	}

	public TextureRegion getWeaponTexture2()
	{
		return weaponTexture2;
	}

	public TextureRegion getWeaponTexture3()
	{
		return weaponTexture3;
	}

	public TextureRegion getWeaponTexture4()
	{
		return weaponTexture4;
	}
	
	public TextureRegion getDeathTexture()
	{
		return deathTexture;
	}
	
	public TextureRegion getEndTexture()
	{
		return endTexture;
	}
	
	public TextureRegion getHPTexture()
	{
		return hpTexture;
	}
	
	public TextureRegion getMPTexture()
	{
		return mpTexture;
	}
}
