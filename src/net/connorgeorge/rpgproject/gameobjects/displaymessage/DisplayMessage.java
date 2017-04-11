package net.connorgeorge.rpgproject.gameobjects.displaymessage;

public class DisplayMessage
{
	private String message;
	private float timeLeft;
	
	public DisplayMessage(String message, float timeout)
	{
		this.message = message;
		this.timeLeft = timeout;
	}
	
	public void update(float delta)
	{
		timeLeft -= delta;
	}
	
	public boolean isDone()
	{
		return timeLeft < 0;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	//you can make subclasses that do different stuff with this
	public void callOnComplete()
	{
		//do nothing
	}
}
