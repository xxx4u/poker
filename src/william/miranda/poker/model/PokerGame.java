package william.miranda.poker.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe principal, onde o jogo começa 
 *
 */
public class PokerGame
{
	//fazemos um singleton para o jogo
	private static PokerGame instance = null;
	
	//cria a o jogo com jogadores dummy
	private PokerGame()
	{
		mesaFisica = new MesaFisica();
		
		mesaFisica.adicionarJogador(new Jogador("Jogador 1", 1000), 0);
		mesaFisica.adicionarJogador(new Jogador("Jogador 2", 1000), 2);
		mesaFisica.adicionarJogador(new Jogador("Jogador 3", 1000), 3);
		mesaFisica.adicionarJogador(new Jogador("Jogador 4", 1000), 5);
		mesaFisica.adicionarJogador(new Jogador("Jogador 5", 1000), 7);
		mesaFisica.adicionarJogador(new Jogador("Jogador 6", 1000), 8);
		mesaFisica.adicionarJogador(new Jogador("Jogador 7", 1000), 9);
	}
	
	public static PokerGame getInstance()
	{
		if (instance == null)
		{
			instance = new PokerGame();
		}
		
		return instance;
	}
	
	//declara a MesaFisica
	private MesaFisica mesaFisica;
	
	//variaveis que controlarao a rodada da vez
	public static Rodada rodada;
	public static Mesa mesa;
	public static Baralho baralho;
	
	public void iniciarNovaRodada()
	{
		//inicializa o baralho
		baralho = new Baralho();
		
		//inicializa o objeto mesa (para a rodada)
		mesa = new Mesa();
		
		//inicializa a jogada passando os jogadores que estao sentados na mesaFisica
		rodada = new Rodada(mesaFisica.getJogadores());
	}
	
	public void desenhar(SpriteBatch batch)
	{
		mesa.desenhar(batch);
	}
}
