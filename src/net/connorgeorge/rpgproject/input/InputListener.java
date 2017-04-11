package net.connorgeorge.rpgproject.input;

import com.badlogic.gdx.InputProcessor;

//Basically listens for key presses and releases and sends that data
//over to the input handler. It could listed to other input in the future
public class InputListener implements InputProcessor
{
	private InputHandler inputHandler;
	
	public InputListener()
	{
		this.inputHandler = InputHandler.getInstance();
	}
	
	@Override
	public boolean keyDown(int key)
	{
		inputHandler.sendInputOn(key);
		return true;
	}

	@Override
	public boolean keyUp(int key)
	{
		inputHandler.sendInputOff(key);
		return true;
	}
	
	
	//These aren't used yet.
	@Override
	public boolean keyTyped(char arg0) { return false; }

	@Override
	public boolean mouseMoved(int arg0, int arg1) { return false; }

	@Override
	public boolean scrolled(int arg0) { return false; }

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) { return false; }

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) { return false; }

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) { return false; }
}
