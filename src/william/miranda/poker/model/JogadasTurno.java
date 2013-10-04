package william.miranda.poker.model;

import java.util.HashMap;

/* Guarda todas as Jogadas que os jogadores fizeram em um turno */
public class JogadasTurno
{
	private HashMap<Jogador, Jogada> jogadas;
	
	public JogadasTurno()
	{
		jogadas = new HashMap<Jogador, Jogada>();
	}
	
	public void adicionarJogada(Jogador jogador, Jogada jogada)
	{
		jogadas.put(jogador, jogada);
	}
}
