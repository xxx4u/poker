package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Container que guarda os jogadores sentados na mesa em suas respectivas posicoes
 * O Vetor que contem os jogadores sempre terá MAX_PLAYERS posicoes e, as posicoes
 * desocupadas serão NULL
 */
public class MesaFisica
{
	public static final int MAX_PLAYERS = 8;
	
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
	
	//adiciona um jogador a uma determinada posicao na mesa
	public void adicionarJogador(Jogador jogador, int pos)
	{
		cadeiras.set(pos, jogador);
	}
	
	//dado um jogador, pega o proximo
	public Jogador proximoJogador(Jogador inicio)
	{
		int posicaoInicio = cadeiras.indexOf(inicio);
		int tmp = posicaoInicio;
		
		do
		{
			//pega o proximo
			tmp++;
			
			//se saiu do vetor, volta para o começo
			if (tmp >= MAX_PLAYERS)
				tmp = 0;
			
		} while (cadeiras.get(tmp) == null || cadeiras.get(tmp).getIsFold());//se nao tem ninguem no lugar, ou se o jogador ja deu FOLD
		
		return cadeiras.get(tmp);
	}
	
	public Jogador getRandom()
	{
		Random rand = new Random();
		Jogador j = null;

		int pos = rand.nextInt(MAX_PLAYERS);
		
		j = cadeiras.get(pos);
				
		while (j == null)
		{
			pos++;
			
			if (pos >= MAX_PLAYERS)
				pos = 0;
			
			j = cadeiras.get(pos);
		}
		
		return j;
	}
	
	public List<Jogador> getJogadores()
	{
		return this.cadeiras;
	}
}
