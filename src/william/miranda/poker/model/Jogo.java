package william.miranda.poker.model;

import java.util.ArrayList;
import java.util.Random;

/*
 * Classe Global que inicializa Rodada, Mesa e chama o GameLoop.
 * Aqui ficam salvas as variaveis persistentes entre as rodadas: Os Jogadores, posições do Dealer e Blinds
 */
public class Jogo
{
	//variaveis persistentes
	private ArrayList<Jogador> jogadores;
	private Jogador jogadorDealer;
	private Jogador jogadorSmallBlind;
	private Jogador jogadorBigBlind;
	private int valorSmallBlind;
	private int valorBigBlind;
	
	//construtor do jogo
	public Jogo()
	{
		inicializarJogadores();
		jogarRodada();
	}
	
	
	public void jogarRodada()
	{
		for (int i=0 ; i<2 ; i++)
		{
			System.out.println("INICIANDO O JOGO [" + i + "]");
			System.out.println("--------------------------------");
			
			//inicializa e preenche a rodada
			Rodada r = prepararRodada();
			GameLoop gl = new GameLoop(r);

			//inicializa a thread
			//new Thread(gl).start();
			gl.run();
		}
	}
	
	//metodo que prepara o objeto Rodada e chama o GameLoop
	private Rodada prepararRodada()
	{
		setDealerAndBlinds();
		
		Rodada r = new Rodada();
		r.setJogadores(jogadores);
		r.setValorSmallBlind(valorSmallBlind);
		r.setValorBigBlind(valorBigBlind);
		
		r.setDealer(jogadorDealer);
		r.setJogadorSmallBlind(jogadorSmallBlind);
		r.setJogadorBigBlind(jogadorBigBlind);
		
		return r;
	}
	
	/* define o Dealer da Rodada
	 * Se nao foi definido, sorteia, senao passa para o proximo
	 */
	private void setDealerAndBlinds()
	{
		//se ainda nao ha Dealer especificado
		if (jogadorDealer == null)
		{
			//sorteia uma posicao aleatoria no vetor
			Random rand = new Random();
			int pos = rand.nextInt(jogadores.size());
			jogadorDealer = jogadores.get(pos);
		}
		
		//pega o proximo elemento para ser o Dealer
		jogadorDealer = getProximoJogador(jogadores, jogadorDealer);
		
		//Define os Jogadores que pagarao as Blinds
		jogadorSmallBlind = getProximoJogador(jogadores, jogadorDealer);
		jogadorBigBlind = getProximoJogador(jogadores, jogadorSmallBlind);
	}
	
	/* 
	 * metodo que pega o proximo jogador de um dado vetor
	 */
	public static Jogador getProximoJogador(ArrayList<Jogador> vetorJogadores, Jogador jogadorDaVez)
	{
		int indice = vetorJogadores.indexOf(jogadorDaVez);
		Jogador j;
		
		//pega o proximo elemento do vetor... se der excecao de limite, pega o primeiro 
		try
		{
			j = vetorJogadores.get(indice+1);
		}
		catch (IndexOutOfBoundsException e)
		{
			j = vetorJogadores.get(0);
		}			
		
		return j;
	}
	
	//inicializa o vetor de jogadores que estao sentados à mesa
	private void inicializarJogadores()
	{
		if (jogadores == null)
		{
			jogadores = new ArrayList<Jogador>();
		}
		
		jogadores.clear();
		
		jogadores.add(new Jogador("William", 1000));
		jogadores.add(new Jogador("Patricia", 1000));
		jogadores.add(new Jogador("Jose", 1000));
		jogadores.add(new Jogador("Antonio", 1000));
		jogadores.add(new Jogador("Rute", 1000));
		jogadores.add(new Jogador("Pedro", 1000));
		jogadores.add(new Jogador("Maria", 1000));
		jogadores.add(new Jogador("Vitor", 1000));
	}
}
