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
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3)
	{
		Mesa mesa = PokerGame.mesa;
		Baralho baralho = PokerGame.baralho;
		Rodada rodada = PokerGame.rodada;
		
		if (mesa.getCartas() == null || mesa.getCartas().size() < 5)
		{
			mesa.addCarta(baralho.sortearCarta());
		}
		else
		{
			Map<Jogador, Mao> melhoresMaosTodos = new HashMap<Jogador, Mao>();
			
			for (Jogador j : rodada.getJogadores())
			{
				if (j != null)
				{
					//obtem os 21 jogos do Jogador
					List<List<Carta>> jogos = Poker.obtemJogos(j, mesa);
					
					//variavel temporaria para guardar a melhor mao de cada um
					List<Mao> maos = new ArrayList<Mao>();
					
					//analisa cada um dos 21 jogos
					for (List<Carta> l : jogos)
					{
						maos.add(Poker.analisaJogo(l));
					}
					
					//pega a melhor mao do jogador
					List<Mao> melhoresMaos = Mao.comparar(maos);
					melhoresMaosTodos.put(j, melhoresMaos.get(0));
				}
			}
			
			//melhoresMaosTodos guarda a melhor mao de cada jogador
			Iterator<Mao> i = melhoresMaosTodos.values().iterator();
			List<Mao> best = new ArrayList<Mao>();
			
			while (i.hasNext())
			{
				best.add(i.next());
			}
			
			for (Mao m : Mao.comparar(best))
			{
				Iterator<Entry<Jogador, Mao>> k = melhoresMaosTodos.entrySet().iterator();
				
				while (k.hasNext())
				{
					Entry<Jogador, Mao> e = k.next();
					
					if (e.getValue().equals(m))
					{
						Utils.Log(e.getKey());
						Utils.Log(e.getValue());
					}
				}
			}
			
			
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
