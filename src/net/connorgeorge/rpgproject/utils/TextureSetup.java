package net.connorgeorge.rpgproject.utils;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

//just used for development purposes, turns image resources into a texture pack which is more efficient to display with openGL
public class TextureSetup
{
	public static void main(String[] args)
	{
		TexturePacker.process("C:\\Users\\myles\\git\\rpgproject\\images", "C:\\Users\\myles\\git\\rpgproject\\images", "textures");
	}
}
