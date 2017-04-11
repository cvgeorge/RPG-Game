package net.connorgeorge.rpgproject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundManager
{
	private static SoundManager manager = null;
	
	private SoundManager(){}
	
	public static SoundManager get()
	{
		if(manager == null)
		{
			manager = new SoundManager();
		}
		
		return manager;
	}
	
	public void loopMusic()
	{
		playLooping("sounds/background.mid");
	}
	
	public void playEnter()
	{
		play("sounds/enter.wav");
	}
	
	public void playGet()
	{
		play("sounds/get.wav");
	}
	
	public void playPunch()
	{
		play("sounds/punch.wav");
	}
	
	public void playLaser()
	{
		play("sounds/laser.wav");
	}
	
	public void playMagic()
	{
		play("sounds/magic.wav");
	}
	
	public void playBubbles()
	{
		play("sounds/bubbles.wav");
	}
	
	public void playGun()
	{
		play("sounds/gun.wav");
	}
	
	public void playLooping(String filepath)
	{
		File file = new File(filepath);
		playLooping(file);
	}
	
	private void playLooping(File file) 
	{
	    try
	    {
	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

	        clip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                {
	                    clip.close();
	                    playLooping(file);
	                }
	            }
	        });

	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	public void play(String filepath)
	{
		File file = new File(filepath);
		
		try
	    {
	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

	        clip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                {
	                    clip.close();
	                }
	            }
	        });

	        clip.open(AudioSystem.getAudioInputStream(file));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
}
