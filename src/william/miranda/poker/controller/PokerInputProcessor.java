package william.miranda.poker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import william.miranda.poker.model.Baralho;
import william.miranda.poker.model.Carta;
import william.miranda.poker.model.Jogador;
import william.miranda.poker.model.Mao;
import william.miranda.poker.model.Mesa;
import william.miranda.poker.model.Poker;
import william.miranda.poker.model.PokerGame;
import william.miranda.poker.model.Rodada;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
