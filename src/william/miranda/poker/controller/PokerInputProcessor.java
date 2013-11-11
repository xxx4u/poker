package william.miranda.poker.controller;

import com.badlogic.gdx.InputProcessor;

public class PokerInputProcessor implements InputProcessor 
{

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0)
	{
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3)
	{
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2)
	{
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
