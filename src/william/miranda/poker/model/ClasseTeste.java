package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

import william.miranda.poker.model.Carta.Naipe;
import william.miranda.poker.controller.Utils;

public class ClasseTeste
{
	/*
	public static void main(String[] args)
	{
		//testarBaralho();
		//testarObterJogos();
		//testarAnalisarJogo();
		//testarDesempateMaos();
		//testarAposta();
		//testarGameLoop();
	}
	*/
	
	public static ArrayList<Jogador> gerarJogadores(int quantidade)
	{
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
		
		for (int i=0 ; i<quantidade ; i++)
		{
			jogadores.add(gerarJogador());
		}
		
		return jogadores;
	}
	
	public static Jogador gerarJogador()
	{
		return new Jogador(UUID.randomUUID().toString().substring(0, 7), 500);
	}
	
	public static void testarGameLoop()
	{
		//inicializa e preenche a rodada
		Rodada r = new Rodada();
		r.setJogadores(gerarJogadores(8));
		r.setValorSmallBlind(10);
		r.setValorBigBlind(20);
				
		GameLoop gl = new GameLoop(r);
		
		new Thread(gl).start();
	}
	
	public static void testarAposta()
	{
		Jogador j = new Jogador("William", 500);
		j.apostar(400);
		
		StringBuilder sb = new StringBuilder();
		sb.append(j.getNome());
		sb.append(" apostou ");
		sb.append(j.getValorApostado());
		sb.append(" - All In = ");
		sb.append(j.isAllIn());
		sb.append(" - Dinheiro = ");
		sb.append(j.getDinheiro());
		
		Utils.Log(sb);
	}
	
	public static void testarDesempateMaos()
	{
		ArrayList<Mao> m = new ArrayList<Mao>();
		ArrayList<Carta> tmp1 = new ArrayList<Carta>();
		tmp1.add(new Carta(10, Naipe.ESPADAS));
		tmp1.add(new Carta(6, Naipe.ESPADAS));
		tmp1.add(new Carta(13, Naipe.ESPADAS));
		tmp1.add(new Carta(9, Naipe.ESPADAS));
		tmp1.add(new Carta(11, Naipe.ESPADAS));
		
		Mao m1 = Poker.analisaJogo(tmp1);
		m.add(m1);
		Utils.Log(m1);
		
		ArrayList<Carta> tmp2 = new ArrayList<Carta>();
		tmp2.add(new Carta(9, Naipe.PAUS));
		tmp2.add(new Carta(5, Naipe.COPAS));
		tmp2.add(new Carta(8, Naipe.ESPADAS));
		tmp2.add(new Carta(8, Naipe.COPAS));
		tmp2.add(new Carta(8, Naipe.OUROS));
		
		Mao m2 = Poker.analisaJogo(tmp2);
		m.add(m2);
		Utils.Log(m2);
		
		ArrayList<Carta> tmp3 = new ArrayList<Carta>();
		tmp3.add(new Carta(1, Naipe.PAUS));
		tmp3.add(new Carta(1, Naipe.COPAS));
		tmp3.add(new Carta(8, Naipe.ESPADAS));
		tmp3.add(new Carta(1, Naipe.COPAS));
		tmp3.add(new Carta(7, Naipe.OUROS));
		
		Mao m3 = Poker.analisaJogo(tmp3);
		m.add(m3);
		Utils.Log(m3);
		
		ArrayList<Carta> tmp4 = new ArrayList<Carta>();
		tmp4.add(new Carta(13, Naipe.PAUS));
		tmp4.add(new Carta(3, Naipe.COPAS));
		tmp4.add(new Carta(1, Naipe.ESPADAS));
		tmp4.add(new Carta(7, Naipe.COPAS));
		tmp4.add(new Carta(9, Naipe.OUROS));
		
		Mao m4 = Poker.analisaJogo(tmp4);
		m.add(m4);
		Utils.Log(m4);
		
		ArrayList<Carta> tmp5 = new ArrayList<Carta>();
		tmp5.add(new Carta(6, Naipe.PAUS));
		tmp5.add(new Carta(10, Naipe.PAUS));
		tmp5.add(new Carta(11, Naipe.PAUS));
		tmp5.add(new Carta(9, Naipe.PAUS));
		tmp5.add(new Carta(13, Naipe.PAUS));
		
		Mao m5 = Poker.analisaJogo(tmp5);
		m.add(m5);
		Utils.Log(m5);
		
		Utils.Log("------");
		
		Utils.Log(Mao.comparar(m));
	}
	
	public static void testarAnalisarJogo()
	{
		ArrayList<Carta> tmp = new ArrayList<Carta>();
		tmp.add(new Carta(1, Naipe.PAUS));
		tmp.add(new Carta(13, Naipe.COPAS));
		tmp.add(new Carta(8, Naipe.ESPADAS));
		tmp.add(new Carta(11, Naipe.COPAS));
		tmp.add(new Carta(7, Naipe.OUROS));
		
		Mao m = Poker.analisaJogo(tmp);
		Utils.Log(m);
	}
	
	public static void testarBaralho()
	{
		Baralho b = new Baralho();
		Vector<String> s = new Vector<String>();
		
		for (int i=0 ; i<Baralho.MAX_CARTAS ; i++)
		{
			//sorteia uma carta
			Carta c = b.sortearCarta();
			
			//se ja existe (duplicada)
			if (s.contains(c.toString()))
			{
				Utils.Log("DUPLICADO!!!");
				break;
			}
			else//se nao existe, adiciona
			{
				s.add(c.toString());
			}
		}
	}
	
	
	public static void testarObterJogos()
	{
		Jogador j = new Jogador("aaa", 0);
		Mesa m = new Mesa();
		Baralho b = new Baralho();
		
		
		j.addCarta(b.sortearCarta());
		j.addCarta(b.sortearCarta());

		m.addCarta(b.sortearCarta());
		m.addCarta(b.sortearCarta());
		m.addCarta(b.sortearCarta());
		m.addCarta(b.sortearCarta());
		m.addCarta(b.sortearCarta());
		
		Poker.obtemJogos(j, m);
	}
}
