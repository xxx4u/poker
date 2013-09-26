package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.List;

import william.miranda.poker.controller.Utils;

/**
 * Container que guarda os jogadores sentados na mesa em suas respectivas posicoes
 */
public class MesaFisica
{
	public static final int MAX_PLAYERS = 10;
	
	private List<Jogador> cadeiras;
	
	public MesaFisica()
	{
		iniciaMesaVazia();
	}
	
	/* preenche todas as posicoes do vetor com NULL */
	private void iniciaMesaVazia()
	{
		cadeiras = new ArrayList<Jogador>();
		
		for (int i=0 ; i<MAX_PLAYERS ; i++)
		{
			cadeiras.add(null);
		}
	}
	
	//dado um jogador, pega o proximo
	public Jogador proximoJogador(Jogador inicio)
	{
		int posicaoInicio = cadeiras.indexOf(inicio);
		int tmp = posicaoInicio;
		
		do
		{
			tmp++;
			
			//se saiu do vetor, volta para o começo
			if (tmp >= MAX_PLAYERS)
				tmp = 0;
			
		} while (cadeiras.get(tmp) == null);
		
		return cadeiras.get(tmp);
	}
}
